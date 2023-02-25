package me.demonis.sep.console;

import me.demonis.sep.auth.Authentication;
import me.demonis.sep.auth.Utils;
import me.demonis.sep.dao.UserDAO;
import me.demonis.sep.entities.User;
import me.demonis.sep.utils.Constants;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthHandler {
    static Scanner sc;
    public static void authHandler() {
        System.out.println("Do you want to log in (a) or register (b) ?");
         sc = Constants.sc;

        String ans = sc.nextLine();

        if (ans.equals("a")) {
            if (login()) {
                System.out.println("You have been successfully logged in.");
                try {
                    Constants.usersReservions = Constants.resdao.getUserReservation(Constants.user);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Navigation.navigation();
            } else {
                System.out.println("An error occurred while logging in. Please try again.");
                authHandler();
            }
        } else if (ans.equals("b")) {
            if (register()) {
                System.out.println("You have been successfully registered.");
                try {
                    Constants.usersReservions = Constants.resdao.getUserReservation(Constants.user);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Navigation.navigation();
            } else {
                System.out.println("An error occurred while registering. Please try again.");
                authHandler();
            }
        } else {
            System.out.printf("We didn't understand your answer.%n%n");
            authHandler();
        }
    }

    public static boolean login() {
        try {
            String username;
            while (true) {
                System.out.println("Enter a username:");
                username = sc.nextLine();
                if (Utils.userAlreadyExists(username)) break;
            }

            String password;
            Authentication auth = new Authentication();
            while (true) {
                System.out.println("Enter a password: (or 'stop' to cancel)");
                password = sc.nextLine();
                if (password.toLowerCase().equals("stop")) return false;
                if (auth.authenticate(username, password)) {
                    Constants.user = Constants.udao.getUserByUsername(username);
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean register() {
        try {
            String username;
            while (true) {
                System.out.println("Enter a username:");
                username = sc.nextLine();
                if (!Utils.userAlreadyExists(username)) break;
            }

            String password;
            while (true) {
                System.out.println("Enter a password:");
                password = sc.nextLine();
                System.out.println("Confirm password:");
                if (password.equals(sc.nextLine())) break;
            }

            System.out.println("Enter your first name:");
            String first_name = sc.nextLine();

            System.out.println("Enter your last name:");
            String last_name = sc.nextLine();

            System.out.println("Admin ?");
            boolean admin = sc.nextBoolean();

            Constants.user = Constants.udao.saveUser(username, first_name, last_name, admin, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
