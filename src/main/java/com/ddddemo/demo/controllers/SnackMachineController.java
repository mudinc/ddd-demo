package com.ddddemo.demo.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import com.ddddemo.demo.services.SnackMachineService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SnackMachineController { 
    private static final Logger logger=LogManager.getLogger(SnackMachineController.class);
    private final SnackMachineService snackMachineService;

    public SnackMachineController(SnackMachineService service){
        this.snackMachineService=service;
    }

   
    @GetMapping("/snackmachines/{id}")
    public ResponseEntity<?> getMachine(@PathVariable Long id){
        logger.info("getMachine:{}",id);
        return snackMachineService.findById(id)
            .map(machine->{
                try{
                    return ResponseEntity
                        .ok()
                        .eTag(machine.getVersion())
                        .location(new URI("/snackmachines/"+machine.getId()))
                        .body(machine);

                }catch(URISyntaxException e){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }

            })
            .orElse(ResponseEntity.notFound().build());
    }
}
