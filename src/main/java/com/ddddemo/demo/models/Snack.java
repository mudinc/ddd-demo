package com.ddddemo.demo.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="snacks")
public class Snack extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String snack_name;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "machine_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SnackMachine machine;



    

    public SnackMachine getMachine() {
        return machine;
    }


    public void setMachine(SnackMachine machine) {
        this.machine = machine;
    }


    public Long getSnack_id() {
        return this.id;
    }


    public void setSnack_id(Long snack_id) {
        this.id = snack_id;
    }


    public String getSnack_name() {
        return snack_name;
    }


    public void setSnack_name(String snack_name) {
        this.snack_name = snack_name;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public Snack(){}
    
}
