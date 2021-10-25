package com.github.korenevaS.queue.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RequestUser {
    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private Long roleId;

    public RequestUser(String firstName, String lastName, String username, String password, Long roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }
}