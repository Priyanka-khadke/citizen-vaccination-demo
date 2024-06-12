package com.example.citizen.demo.controller;

import com.example.citizen.demo.entity.Citizen;
import com.example.citizen.demo.repositories.CitizenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/citizen")
@RefreshScope
public class CitizenController {

    @Autowired
    CitizenRepo repo;

    @Value("${my.greeting.example}")
    private String message2;

    @GetMapping
    public String getResponse() {
        return "hello from citizen controller";
    }

    @GetMapping("/id/{id}")
    public List<Citizen>getAllCitizens(@PathVariable Integer id){
        System.out.println("message from controller: "+message2);
        return repo.findByVaccinationCenterId(id);
    }

    @PostMapping()
    public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen citizen){
       repo.save(citizen);

       return new ResponseEntity<>(citizen,HttpStatus.OK);
    }
}
