package me.demonis.sep.auth;

import me.demonis.sep.utils.Constants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Authentication {


    public Authentication() {}

    public boolean authenticate(String username, String password) throws SQLException, NoSuchAlgorithmException {
        String sql = "SELECT password FROM user WHERE username = ?";
        PreparedStatement stmt = Constants.connection.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String storedPassword = rs.getString("password");
            String hashedPassword = hashPassword(password);
            return storedPassword.equals(hashedPassword);
        } else {
            return false;
        }
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
