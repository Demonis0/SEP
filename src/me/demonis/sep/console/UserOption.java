package me.demonis.sep.console;

import me.demonis.sep.entities.Reservation;
import me.demonis.sep.entities.Room;
import me.demonis.sep.utils.Constants;

import java.sql.SQLException;
import java.time.LocalDate;

public class UserOption {

    public static void createReservation() {
        int num_people = EntryBuilder.askForInt("How many people:");
        LocalDate endDate, startDate;
        while (true) {
            startDate = EntryBuilder.askForDate("Arriving date: (AAAA-MM-JJ)");
            endDate = EntryBuilder.askForDate("Leaving date: (AAAA-MM-JJ)");
            if (startDate.isBefore(endDate)) break;
            else System.out.println("Please enter valide dates");
        }
        int c = 0;
        LocalDate temp = LocalDate.of(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth());
        while (temp.getDayOfYear() == endDate.getDayOfYear() && temp.getYear() == endDate.getYear()) {
            System.out.println("+1");
            temp.plusDays(1);
            c++;
        }
        Room room = null;
        for (Room r : Constants.roomDao.getAvailableRooms(startDate, endDate)) {
            if (r.getCapacity() >= num_people) {
                room = r;
                break;
            }
        }

        if (room == null) {
            System.out.println("No room available, please try again with different parameter.");
        } else {
            double price = c * room.getPricePerNight();
            try {
                Reservation reservation = Constants.resdao.saveReservation(Constants.user.getFirst_name(), Constants.user.getLast_name(),num_people, startDate, endDate, price, room.getId());
                Constants.usersReservions.add(reservation);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Reservation saved.");
        }
        Navigation.navigation();
    }

    public static void seeReservations() {
        for (Reservation res : Constants.usersReservions) {
            System.out.println(res.getId() + " ; " + res.getStartDate() + " to " + res.getEndDate());
        }
        Navigation.navigation();
    }
}
