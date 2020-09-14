package com.example.register_form2.controller;

import com.example.register_form2.dto.BeerDTO;
import com.example.register_form2.model.Beer;
import com.example.register_form2.service.BeerDTOService;
import com.example.register_form2.service.MailService;
import com.example.register_form2.service.SecurityContextHolderService;
import com.example.register_form2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    static final String URL_BEERS = "https://api.punkapi.com/v2/beers";
    static String PAGE_NUMBER = "1";
//https://api.punkapi.com/v2/beers?page=2&per_page=80

    private final RestTemplate restTemplate;
    private final BeerDTOService beerDTOService;
    private final UserServiceImpl userService;
    private final MailService mailService;
    private final SecurityContextHolderService securityContextHolderService;


    @Autowired
    public MainController(RestTemplate restTemplate,
                          BeerDTOService beerDTOService,
                          UserServiceImpl userService,
                          MailService mailService,
                          SecurityContextHolderService securityContextHolderService) {
        this.restTemplate = restTemplate;
        this.beerDTOService = beerDTOService;
        this.userService = userService;
        this.mailService = mailService;
        this.securityContextHolderService = securityContextHolderService;
    }

    @GetMapping
    public String getBeers(Model model) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<Beer[]> response = restTemplate.exchange(
                    URL_BEERS + "?page=1&per_page=5",
                    HttpMethod.GET, entity, Beer[].class);
            model.addAttribute("beers", response.getBody());
            model.addAttribute("currentPage", 1);
            return "index";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "{pageNo}")
    public String getBeers(@PathVariable int pageNo,
                           Model model) {


        pageNo = pageNo == 0 ? 65 : pageNo == 66 ? 1 : pageNo;
        PAGE_NUMBER = pageNo + "";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<Beer[]> response = restTemplate.exchange(
                    URL_BEERS + "?page=" + pageNo + "&per_page=5",
                    HttpMethod.GET, entity, Beer[].class);
            model.addAttribute("beers", response.getBody());
            model.addAttribute("currentPage", pageNo);
            return "index";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @GetMapping("beers/{id}")
    public String getBeerById(@PathVariable("id") int id, Model model) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            String login = securityContextHolderService.getLoginOfLoggedUser();

            Long userId = userService.getUserIdByUserLogin(login);

            ResponseEntity<Beer[]> response = restTemplate.exchange(URL_BEERS + "/" + id, HttpMethod.GET, entity, Beer[].class);
            model.addAttribute("beers", response.getBody());
            Long id1 = response.getBody()[0].getId();
            String name = response.getBody()[0].getName();
            beerDTOService.save(new BeerDTO(id1, name, userId));
            return "redirect:/" + PAGE_NUMBER;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("send")
    public String sendMail() throws MessagingException {

        String login = securityContextHolderService.getLoginOfLoggedUser();

        Long userId = userService.getUserIdByUserLogin(login);

        String userEmail = userService.getUserEmailByUserLogin(login);

        mailService.sendMail(userEmail,
                "Favourite list",
                beerDTOService.selectAllBeers().stream()
                        .filter(beerDTO -> beerDTO.getUserId() == userId)
                        .map(BeerDTO::toString)
                        .distinct()
                        .collect(Collectors.toList())
                        .toString(),
                true);
        return "redirect:/" + PAGE_NUMBER;
    }
}
