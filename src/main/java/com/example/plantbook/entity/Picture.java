package com.example.plantbook.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "picture")
@AllArgsConstructor
@NoArgsConstructor
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", length = 1000)
    private String url;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    @JsonBackReference
    private Plant plant;

    @OneToOne
    @JoinColumn(name="post_id")
    @JsonBackReference
    private Post post;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


    public Picture(String url, Plant plant, Post post, User user) {
        this.url = url;
        this.plant = plant;
        this.post = post;
        this.user = user;
    }
}
