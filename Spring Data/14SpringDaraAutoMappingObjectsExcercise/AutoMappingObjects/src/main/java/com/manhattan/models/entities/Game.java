package com.manhattan.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(name="trailer")
    private String trailerUrl;

    @Column(name="image_thumbnail")
    String imageThumbnailUrl;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    BigDecimal price;

    @Column(columnDefinition = "TEXT")
    String description ;

    @Column(name = "release_date")
    LocalDate releaseDate;

    @ManyToMany(mappedBy = "games", targetEntity = User.class)
    private Set<User> users;

    @ManyToMany(mappedBy = "games", targetEntity = Order.class)
    private Set<Order> orders;

    public Game() {
        this.users = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getImageThumbnailUrl() {
        return imageThumbnailUrl;
    }

    public void setImageThumbnailUrl(String imageThumbnailUrl) {
        this.imageThumbnailUrl = imageThumbnailUrl;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
