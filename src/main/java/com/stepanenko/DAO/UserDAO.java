package com.stepanenko.DAO;

import com.stepanenko.logic.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    public void addUser(User user) throws SQLException;

    public void addAdmin(User user) throws SQLException;

    public List<User> search(String searchString) throws SQLException;

    public List<User> getAllUsers() throws SQLException;

    public void deleteUser(String username) throws SQLException;

    public void updateUser(User user) throws SQLException;
}
