package org.example.quizweb.service;

import jakarta.servlet.ServletException;
import org.example.quizweb.dao.UserDao;
import org.example.quizweb.dto.UserDto;
import org.example.quizweb.model.User;
import org.example.quizweb.util.UserMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();

    public boolean login(String username, String password) throws ServletException, IOException {
        return userDao.login(username, password);
    }

    public boolean register(User user) throws ServletException, IOException {
        return userDao.register(user);
    }
}
