package com.example.vaccination_service.controller;

import com.example.vaccination_service.entity.VaccinationCenter;
import com.example.vaccination_service.model.Citizen;
import com.example.vaccination_service.model.RequiredResponse;
import com.example.vaccination_service.repo.VaccinationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/vaccination")
@RefreshScope
public class VaccinationController {

    @Autowired
    VaccinationRepo repo;

    @Value("${my.greeting.example}")
    private String message;



    @Autowired
    RestTemplate restTemplate;

    @PostMapping()
    public ResponseEntity<VaccinationCenter> addCitizen(@RequestBody VaccinationCenter vaccCenter){
        VaccinationCenter center = repo.save(vaccCenter);
        return new ResponseEntity<>(center, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequiredResponse>getAllData(@PathVariable Integer id){
        System.out.println("message printed from vaccination center: "+message);
       VaccinationCenter center =  repo.findById(id).get();
        RequiredResponse response = new RequiredResponse();
        response.setCenter(center);
        List<Citizen> citizens = restTemplate.getForObject("http://Citizen-service/citizen/id/"+center.getId(), List.class);
        response.setCitizens(citizens);
        return new ResponseEntity<>(response,HttpStatus.OK);


    }
}
