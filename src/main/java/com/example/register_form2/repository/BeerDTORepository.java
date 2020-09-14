package com.example.register_form2.repository;

import com.example.register_form2.dto.BeerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerDTORepository extends JpaRepository<BeerDTO, Long> {

}
