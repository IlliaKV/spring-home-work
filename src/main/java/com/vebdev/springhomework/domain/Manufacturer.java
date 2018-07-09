package com.vebdev.springhomework.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Manufacturer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "manufacturerName")
    private String manufacturerName;

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.EAGER)
    private List<Product> products;

    public void setId(long id) {
        this.id = id;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public List<Product> getProducts() {
        return products;
    }

}
