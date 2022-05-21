package com.example.plantbook.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.SELECT)
    @JsonIgnoreProperties("posts")
    private User user;

    @OneToOne(mappedBy = "post",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SELECT)
    private Picture picture;

    @Column(name = "created_at")
    private Date postedAt;

    @Column(name = "updated_time")
    private Time postedTime;
}


