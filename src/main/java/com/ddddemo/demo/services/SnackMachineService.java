package com.ddddemo.demo.services;

import java.util.Optional;

import com.ddddemo.demo.models.SnackMachine;
import com.ddddemo.demo.repositories.SnackMachineRepository;

import org.springframework.stereotype.Service;

@Service
public class SnackMachineService {

    private SnackMachineRepository    snackMachineRepository;
    
    public SnackMachineService(SnackMachineRepository repo){
        this.snackMachineRepository=repo;
    }

   
    public Optional<SnackMachine> findById(Long id){
        return snackMachineRepository.findById(id);
    }

    public SnackMachine save(SnackMachine machine){
        
        return this.snackMachineRepository.save(machine);
    }

    public void delete(Long id){
        this.snackMachineRepository.deleteById(id);
    }

}
