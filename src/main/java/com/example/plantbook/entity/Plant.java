package com.example.plantbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "plant")
@AllArgsConstructor
@NoArgsConstructor
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("plant_id")
    private List<Picture> pictures;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("plants")
    private User user;

    @Column(name = "price")
    private Double price;

}
