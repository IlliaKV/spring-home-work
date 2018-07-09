package com.vebdev.springhomework.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nameProduct")
    private String nameProduct;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "manufacturerOfManufacturer")
    private String manufacturerOfManufacturer;

    @JoinColumn(name = "manufacturerId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Manufacturer manufacturer;

    public void setId(long id) {
        this.id = id;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setManufacturerOfManufacturer(String manufacturerOfManufacturer) {
        this.manufacturerOfManufacturer = manufacturerOfManufacturer;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public long getId() {
        return id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getManufacturerOfManufacturer() {
        return manufacturerOfManufacturer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                ", manufacturerOfManufacturer='" + manufacturerOfManufacturer + '\'' +
                ", manufacturer=" + manufacturer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
