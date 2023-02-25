package me.demonis.sep.dao;

import me.demonis.sep.auth.Authentication;
import me.demonis.sep.entities.Room;
import me.demonis.sep.entities.User;
import me.demonis.sep.utils.Constants;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public UserDAO() {}

    /** Register a User in the database **/
    public User saveUser(String username, String first_name, String last_name, boolean admin, String password) throws SQLException {
        String sql = "INSERT INTO user (first_name, last_name, username, admin, password) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = Constants.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, first_name);
        statement.setString(2, last_name);
        statement.setString(3, username);
        statement.setBoolean(4, admin);
        try {
            String hashedPassword = new Authentication().hashPassword(password);
            System.out.println("password : " + hashedPassword);
            statement.setString(5, hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        User user = null;
        if (generatedKeys.next()) {
            user = new User(generatedKeys.getInt(1), username, first_name, last_name, admin);
        }

        return user;
    }

    /** Update User's info in database **/
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET username=?, first_name=?, last_name=?, admin=? WHERE id=?";
        try (PreparedStatement statement = Constants.connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFirst_name());
            statement.setString(3, user.getLast_name());
            statement.setBoolean(4, user.isAdmin());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        }
    }

    /** Delete a User from the database **/
    public void deleteUser(Room user) throws SQLException {
        String sql = "DELETE FROM user WHERE id=?";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        statement.setInt(1, user.getId());
        statement.executeUpdate();
    }

    /** Get all Users from database **/
    public List<User> getAllRooms() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            boolean admin = resultSet.getBoolean("admin");
            User user = new User(id, username, first_name, last_name, admin);
            users.add(user);
        }
        return users;
    }

    /** Get User by ID **/
    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id=?";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String username = resultSet.getString("username");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            boolean admin = resultSet.getBoolean("admin");
            return new User(id, username, first_name, last_name, admin);
        }

        return null;
    }

    /** Get User by username **/
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username=?";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            boolean admin = resultSet.getBoolean("admin");
            return new User(id, username, first_name, last_name, admin);
        }

        return null;
    }
}
