package com.ddddemo.demo.models;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name="snackmachines")
public class SnackMachine extends AuditModel{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String machine_description;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String machine_name;
    private String version;

    public String getMachine_description() {
        return machine_description;
    }


    public void setMachine_description(String machine_description) {
        this.machine_description = machine_description;
    }


    public String getMachine_name() {
        return machine_name;
    }


    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

  
    
       

    
  

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((machine_name == null) ? 0 : machine_name.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SnackMachine other = (SnackMachine) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (machine_name == null) {
            if (other.machine_name != null)
                return false;
        } else if (!machine_name.equals(other.machine_name))
            return false;
        return true;
    }







    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }


    public SnackMachine(){}
}
