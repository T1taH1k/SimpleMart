package com.stepanenko.DAO;

import com.stepanenko.logic.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDAOImpl implements UserDAO {

    @Autowired
    DriverManagerDataSource dataSource;


    @Override
    public void addUser(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ? ,? ,?, 1)");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getLastname());
        preparedStatement.setString(4, user.getFirstname());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setString(6, user.getPhone());
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("INSERT INTO user_roles (username, ROLE) VALUES (?, 'ROLE_USER')");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.executeUpdate();
        connection.close();

    }

    @Override
    public List<User> search(String searchString) throws SQLException {

        ArrayList<User> list = new ArrayList<User>();
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username like ? or phone like ?");
        preparedStatement.setString(1, '%' + searchString + '%');
        preparedStatement.setString(2, '%' + searchString + '%');
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("lastname"), resultSet.getString("firstname"), resultSet.getString("email"), resultSet.getString("phone"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return list;
    }


    @Override
    public List<User> getAllUsers() throws SQLException {
        ArrayList<User> list = new ArrayList<User>();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        try {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("lastname"), resultSet.getString("firstname"), resultSet.getString("email"), resultSet.getString("phone"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        return list;
    }

    @Override
    public void addAdmin(User user) throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ? ,? ,?, 1)");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getLastname());
        preparedStatement.setString(4, user.getFirstname());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setString(6, user.getPhone());
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("INSERT INTO user_roles (username, ROLE) VALUES (?, 'ROLE_USER'), (?, 'ROLE_ADMIN')");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.executeUpdate();
        connection.close();

    }

    @Override
    public void deleteUser(String username) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE from user_roles WHERE username = ?");
        preparedStatement.setString(1, username);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("DELETE FROM users WHERE username = ?");
        preparedStatement.setString(1, username);
        preparedStatement.executeUpdate();
        connection.close();

    }

    @Override
    public void updateUser(User user) throws SQLException {
        deleteUser(user.getUsername());
        addUser(user);
    }

}
