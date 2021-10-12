package com.github.korenevaS.lesson5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`user`")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    @ManyToOne
    @Column(name = "school_id")
    private School school;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> friends;

    public User(Integer id, String firstName, String lastName, int age, String sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
    }
}
