package com.github.korenevaS.lesson5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> friends;

}
