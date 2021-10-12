package com.github.korenevaS.lesson5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.*;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "school")
    private School school;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> friends;

    public User(Integer id, String firstName, String lastName, int age, String sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
    }
}
