package com.brainacad.studyproject.data.dao.impl;

import com.brainacad.studyproject.data.core.ConnectionManager;
import com.brainacad.studyproject.data.dao.UserDao;
import com.brainacad.studyproject.data.domain.Role;
import com.brainacad.studyproject.data.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import static com.brainacad.studyproject.data.domain.Role.ADMIN;
import static com.brainacad.studyproject.data.domain.Role.USER;

/**
 * Created by User on 11/5/2016.
 */
public class JdbcUserDao implements UserDao {

    public static final String SELECT_FROM_USERS = "SELECT * FROM users";
    public static final String WHERE_USERNAME_LIKE = SELECT_FROM_USERS + " WHERE username LIKE ?";

    private ConnectionManager connectionManager
            = ConnectionManager.getInstance();

    @Override
    public User getUserByName(String username) {
        Connection connection = connectionManager.getConnection();
        User user = null;
        try {
            PreparedStatement statement =
                    connection.prepareStatement(WHERE_USERNAME_LIKE);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                user = new User();
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getInt("role_id") == 1 ? ADMIN : USER);
                }
            }
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public int add(User entity) {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public Collection<User> getAll() {
        Connection connection = connectionManager.getConnection();
        Collection<User> users = new HashSet();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(SELECT_FROM_USERS);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getInt("role_id") == 1 ? ADMIN : USER);
                    users.add(user);
                }
            }
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
