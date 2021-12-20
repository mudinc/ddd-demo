package com.ddddemo.demo.models;

import java.io.Serializable;


public class InventoryRecord implements Serializable {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String sKU) {
        SKU = sKU;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private Long id;
    private Integer quantity;
    private String SKU;
    private String productName;
    private String category;
    
    public InventoryRecord(){

    }

    public InventoryRecord(Long id, Integer quantity, String sku, String productName, String category){
        this.id=id;
        this.quantity=quantity;
        this.SKU=sku;
        this.productName=productName;
        this.category=category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((SKU == null) ? 0 : SKU.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        InventoryRecord other = (InventoryRecord) obj;
        if (SKU == null) {
            if (other.SKU != null)
                return false;
        } else if (!SKU.equals(other.SKU))
            return false;
        return true;
    }

    
}
