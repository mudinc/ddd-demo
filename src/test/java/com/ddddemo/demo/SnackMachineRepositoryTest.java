package com.ddddemo.demo;




import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.ddddemo.demo.models.Snack;
import com.ddddemo.demo.models.SnackMachine;
import com.ddddemo.demo.repositories.SnackMachineRepository;
import com.ddddemo.demo.repositories.SnackRepository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.database.rider.core.api.connection.ConnectionHolder;

import com.github.database.rider.junit5.DBUnitExtension;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;


import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.Date;


@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest
@ActiveProfiles("test")
@RunWith(JUnit4.class)
//@DBUnit(url = "jdbc:h2:mem:demodb;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:/schema.sql'", driver = "org.h2.Driver", user = "sa")
public class SnackMachineRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SnackMachineRepository repository;

    @Autowired
    private SnackRepository snackRepository;

    
    public ConnectionHolder getConnectionHolder() {
        // Return a function that retrieves a connection from our data source
        return () -> dataSource.getConnection();
    }

    @Test
    @DataSet(value = "datasets/machines.yml",strategy = SeedStrategy.CLEAN_INSERT)
    void testFindAll() {
        List<SnackMachine> machines = repository.findAll();
        Assertions.assertEquals(2, machines.size(), "We should have 2 machines in our database");
    }
    @Test
    @DataSet(value = "datasets/machinesandsnacks.yml",strategy = SeedStrategy.CLEAN_INSERT)
    void testFindAllSnacks() {
        List<Snack> snacks = snackRepository.findAll();
        Assertions.assertEquals(2, snacks.size(), "We should have 2 snacks in our database");
    }
    @Test
    @DataSet(value = "datasets/machinesandsnacks.yml",strategy = SeedStrategy.CLEAN_INSERT)
    void testFindSnacksForMachine() {
        List<Snack> snacks = snackRepository.findByMachineId(Long.valueOf(1));

        Assertions.assertEquals(2, snacks.size(), "We should have 2 snacks in our database");
    }

    @Test
    @DataSet(value = "datasets/machinesandsnacks.yml",strategy = SeedStrategy.CLEAN_INSERT)
    void saveSnackMachine(){
        SnackMachine machine=new SnackMachine();

        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        machine.setCreatedAt(out);
        machine.setUpdatedAt(out);
        machine.setId(Long.valueOf(3));
        machine.setMachine_name("machine 3");
        machine.setMachine_description("machine_description");

        repository.save(machine);

        List<SnackMachine> machines = repository.findAll();
        Assertions.assertEquals(3, machines.size(), "We should have 2 machines in our database");

        Optional<SnackMachine> addedMachine=repository.findById(Long.valueOf(3));

        Assertions.assertTrue(addedMachine.get().getMachine_name().equals("machine 3"));


    }

}
