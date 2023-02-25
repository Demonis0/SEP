package me.demonis.sep.console;

import me.demonis.sep.entities.Room;
import me.demonis.sep.utils.Constants;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class AdminOptions {

    public static Scanner sc = Constants.sc;

    public static void createRoom() {
        try {
            int nb_room = EntryBuilder.askForInt("Room number:");
            Double price = EntryBuilder.askForDouble("Price per night:");
            Double surface = EntryBuilder.askForDouble("Surface:");
            int capacity = EntryBuilder.askForInt("Capacity:");
            Room room = Constants.roomDao.saveRoom(nb_room, price, capacity, surface);
            System.out.println("Room has been successfully created.");
            Navigation.navigation();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void availableRooms() {
            System.out.println("Available rooms:");
            LocalDate ld = EntryBuilder.askForDate();
            System.out.println(Constants.roomDao.getAvailableRooms(ld).toString());
            Navigation.navigation();
    }
}
