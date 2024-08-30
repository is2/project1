package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.example.dao.UserDAO;
import org.example.dao.UserDAOImpl;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.example.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection connection =  Util.getConnection()) {
            UserDAO userDao = new UserDAOImpl(connection);
            UserService userService = new UserServiceImpl(userDao);

            userService.createUsersTable();

            userService.saveUser("A", "A", (byte) 30);
            userService.saveUser("B", "B", (byte) 25);
            userService.saveUser("C", "C", (byte) 35);
            userService.saveUser("D", "D", (byte) 40);

            List<User> users = userService.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }

            userService.cleanUsersTable();
            userService.dropUsersTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
