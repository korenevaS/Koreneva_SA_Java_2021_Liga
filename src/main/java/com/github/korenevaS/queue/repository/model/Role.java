package com.github.korenevaS.queue.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.korenevaS.queue.repository.GrantedAuthority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Transient
    @OneToMany(mappedBy = "role",
            fetch = FetchType.LAZY)
    private Set<User> users;

    public String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}