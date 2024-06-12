package com.example.vaccination_service.repo;

import com.example.vaccination_service.entity.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VaccinationRepo extends JpaRepository<VaccinationCenter,Integer> {

    public Optional<VaccinationCenter> findById(Integer id);
}
