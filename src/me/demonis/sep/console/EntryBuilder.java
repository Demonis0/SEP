package me.demonis.sep.console;

import me.demonis.sep.utils.Constants;
import me.demonis.sep.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class EntryBuilder {
    public static int askForInt(String message) {
        int res = 0;
        boolean hasAnswer = false;
        System.out.print(message);

        do {
            try {
                res = Constants.sc.nextInt();
                hasAnswer = true;
            }
            catch(Exception exception) {
                hasAnswer = false;
                System.out.print("Please enter a valid number (integer only) : ");
            }
            Constants.sc.nextLine();
        } while(hasAnswer == false);

        return res;
    }

    public static LocalDate askForDate(String message) {
        LocalDate res = null;
        System.out.print(message);

        do {
            try {
                res = LocalDate.parse(Constants.sc.nextLine());
            }
            catch (DateTimeParseException exception) {
                res = null;
                System.out.print("Please enter a valid date with the following format AAAA-MM-JJ : ");
            }
        } while (res == null);

        return res;
    }

    public static LocalDate askForDate() {
        return askForDate("Enter a date with the following format AAAA-MM-JJ : ");
    }

    public static double askForDouble(String message) {
        double res = 0.0;
        System.out.print(message);

        do {
            try {
                String input = Constants.sc.nextLine();

                if (!input.matches(Utils.doubleRegex)) {
                    throw new InputMismatchException();
                }
                res = Double.parseDouble(input);
            } catch (Exception exception) {
                res = 0.0;
                System.out.print("Please enter a value with the following format (##.##) : ");
            }
        } while (res == 0.0);
        return res;
    }

    public static String askForString(String message) {
        String res;
        System.out.print(message);

        do {
            try {
                res = Constants.sc.nextLine();
                if(res.isEmpty() || res.isBlank())
                    throw new InputMismatchException();
            }
            catch(Exception exception) {
                res = null;
                System.out.print("Data cannot be empty or null : ");
            }
        } while(res == null);
        return res;
    }
}