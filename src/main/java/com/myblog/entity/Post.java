package com.myblog.entity;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="posts")
@Data

@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private  String title;

    private  String description;

    private  String content;

    //One Post Can have multiple comments
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    private List<Comment> comments;


}
