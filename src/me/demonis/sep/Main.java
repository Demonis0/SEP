package me.demonis.sep;

import me.demonis.sep.console.AuthHandler;
import me.demonis.sep.dao.ReservationDAO;
import me.demonis.sep.dao.RoomDAO;
import me.demonis.sep.dao.UserDAO;
import me.demonis.sep.entities.Reservation;
import me.demonis.sep.entities.Room;
import me.demonis.sep.entities.User;
import me.demonis.sep.utils.Constants;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        try {
            Constants.connection = DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
            System.out.println("Connected to the database");

            Constants.roomDao = new RoomDAO();
            Constants.resdao = new ReservationDAO();
            Constants.udao = new UserDAO();

            Constants.sc = new Scanner(System.in);

            AuthHandler.authHandler();

        } catch (SQLException e) {
            System.out.println("Oops, something went wrong!");
            e.printStackTrace();
        }
        Constants.sc.close();
    }
}
