package com.ddddemo.demo.services;



import java.util.Optional;

import com.ddddemo.demo.models.InventoryRecord;
import com.jayway.jsonpath.Option;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryService {
    @Value("${inventorymanager.baseUrl}")
    private String baseUrl;
    
    RestTemplate restTemplate=new RestTemplate();

    public Optional<InventoryRecord> getInventoryRecord(Long id){


        try {
            return Optional.of(restTemplate.getForObject(baseUrl+"/"+id, InventoryRecord.class));
        } catch (HttpClientErrorException e) {
            return Optional.empty();
        }
        
    }
}
