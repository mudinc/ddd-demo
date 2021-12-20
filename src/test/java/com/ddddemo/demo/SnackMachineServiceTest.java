package com.ddddemo.demo;

import com.ddddemo.demo.models.SnackMachine;
import com.ddddemo.demo.repositories.SnackMachineRepository;
import com.ddddemo.demo.services.SnackMachineService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.doReturn;


import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class SnackMachineServiceTest {

    @Autowired
    private SnackMachineService service;

    @MockBean
    private SnackMachineRepository repository;



     
	@Test
    @DisplayName("Find by Id")
	void testFindByIdSuccess(){
        SnackMachine mockMachine=new SnackMachine();
        mockMachine.setMachine_description("test");
        mockMachine.setId(Long.valueOf(1));
        mockMachine.setMachine_name("test");
        mockMachine.setVersion("1.0");

        doReturn(Optional.of(mockMachine)).when(repository).findById(Long.valueOf(1));

        Optional<SnackMachine> machine=service.findById(Long.valueOf(1));

        Assertions.assertTrue(machine.isPresent(),"Machine is not found");
    }
        
        
}
