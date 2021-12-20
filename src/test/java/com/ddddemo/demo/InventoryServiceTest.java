package com.ddddemo.demo;

import com.ddddemo.demo.models.InventoryRecord;
import com.ddddemo.demo.services.InventoryService;
import com.github.tomakehurst.wiremock.WireMockServer;


import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class InventoryServiceTest {

    @Autowired
    private InventoryService service;

    private WireMockServer wireMockServer;

    @BeforeEach
    void beforeEach(){
        wireMockServer=new WireMockServer(9999);
        wireMockServer.start();

        wireMockServer.stubFor(get(urlEqualTo("/inventory/1"))
            .willReturn(aResponse()
                .withHeader("Content-Type","application/json")
                .withStatus(200)
                .withBodyFile("json/inventory-response.json")));
    }
    
    @AfterEach
    void AfterEach(){
        wireMockServer.stop();
    }

    @Test
    @DisplayName("getInventoryProduct - SUCCESS")
    void testGetInventoryRecordSuccess(){
        Optional<InventoryRecord> record=service.getInventoryRecord(Long.valueOf(1));
        Assertions.assertTrue(record.isPresent(),"record should be present");
        Assertions.assertEquals(300,record.get().getQuantity().intValue(),"quantity should be 300");

    }
}
