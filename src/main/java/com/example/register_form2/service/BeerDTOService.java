package com.example.register_form2.service;

import com.example.register_form2.dto.BeerDTO;
import com.example.register_form2.repository.BeerDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerDTOService {

    private final BeerDTORepository beerDTORepository;

    @Autowired
    public BeerDTOService(BeerDTORepository beerDTORepository) {
        this.beerDTORepository = beerDTORepository;
    }

    public List<BeerDTO> selectAllBeers() {
        return beerDTORepository.findAll();
    }

    public void save(BeerDTO beerDTO) {
        beerDTORepository.save(beerDTO);
    }



}

