package com.example.register_form2.dto;

import javax.persistence.*;

@Entity
@Table(name = "beer_table")
public class BeerDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "automatic_id")
    private Long automatic_id;

    private Long id;
    private String name;
    private Long userId;

    public BeerDTO(Long id, String name, Long userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public BeerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}