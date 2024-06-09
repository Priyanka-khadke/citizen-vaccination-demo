package com.example.citizen.demo.repositories;

import com.example.citizen.demo.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitizenRepo extends JpaRepository<Citizen,Integer> {

    public List<Citizen> findByVaccinationCenterId(Integer id);
}
