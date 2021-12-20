package com.ddddemo.demo.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.ddddemo.demo.models.Snack;


@Repository
public interface SnackRepository extends JpaRepository<Snack,Long> {
    List<Snack> findByMachineId(Long machineId);
}
