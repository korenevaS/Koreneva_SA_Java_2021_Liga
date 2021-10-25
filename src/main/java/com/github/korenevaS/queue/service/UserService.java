package com.github.korenevaS.queue.service;

import com.github.korenevaS.queue.controller.model.RequestUser;
import com.github.korenevaS.queue.repository.model.Receipt;
import com.github.korenevaS.queue.repository.model.User;

import java.util.List;

public interface UserService {
    User saveUser(RequestUser user);

    User getUser(String username);

    List<User> getUsers();

    List<Receipt> getReceipt(Integer userId);
}
