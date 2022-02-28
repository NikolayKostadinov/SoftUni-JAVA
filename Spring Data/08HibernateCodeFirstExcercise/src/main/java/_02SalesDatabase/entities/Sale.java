package _02SalesDatabase.entities;

import Common.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "_02_sales")
public class Sale extends BaseEntity<Integer> {

    private Product product;

    private Customer customer;

    private StoreLocation storeLocation;

    private LocalDate date;

    public Sale() {
        this(LocalDate.now());
    }

    public Sale(LocalDate date) {
        this.date = date;
    }

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    @Column(name="date", nullable = false)
    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }
}
