package com.manhattan.models.carDealer.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long quantity;

    @ManyToOne()
    @JoinColumn(name = "supplier_id")
    Supplier supplier;

    @ManyToMany()
    private Set<Car> cars;

    public Part() {
        this.cars = new HashSet<>();
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
