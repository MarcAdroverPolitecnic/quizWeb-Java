package org.example.quizweb.dao;

import org.example.quizweb.model.User;
import org.example.quizweb.util.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static final Connection connection;

    static {
        try {
            connection = JdbcConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);

            try (ResultSet result = pst.executeQuery()) {
                return result.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean register(User user) {
        String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            int affected = pst.executeUpdate();
            if (affected == 0) {
                throw new SQLException("No se insertó ninguna fila.");
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(Long id) {
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM user WHERE id = " + id)) {
            ResultSet result = pst.executeQuery();

            if (result.next()) {
                return new  User(result.getLong("id"),
                        result.getString("username"),
                        result.getString("password"));
            } else {
                return null;
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getUserId(String username) {
        String sql = "SELECT id FROM user WHERE username = ?";

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, username);
            ResultSet result = pst.executeQuery();

            if (result.next()) {
                return result.getLong("id");
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}