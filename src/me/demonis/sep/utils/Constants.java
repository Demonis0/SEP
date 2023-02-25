package me.demonis.sep.utils;

import me.demonis.sep.dao.ReservationDAO;
import me.demonis.sep.dao.RoomDAO;
import me.demonis.sep.dao.UserDAO;
import me.demonis.sep.entities.Reservation;
import me.demonis.sep.entities.User;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Constants {

    public static final String url = "jdbc:mysql://localhost:3306/hotel";
    public static final String username = "root";

    public static Scanner sc;
    public static Connection connection;


    public static RoomDAO roomDao;
    public static ReservationDAO resdao;
    public static UserDAO udao;



    public static User user;
    public static List<Reservation> usersReservions;
}
