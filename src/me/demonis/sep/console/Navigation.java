package me.demonis.sep.console;

import me.demonis.sep.entities.Room;
import me.demonis.sep.utils.Constants;

import java.sql.SQLException;

public class Navigation {

    /** Main menu and navigation **/
    public static void navigation() {
        if (Constants.user.isAdmin()) {
            MenuBuilder.displayChooserMenu("Actions", new String[]{"Add Room", "Free Rooms", "Quitter"});
            switch (MenuBuilder.optionChooser("Choose an option :")) {
                case 1:
                    AdminOptions.createRoom();
                    break;
                case 2:
                    AdminOptions.availableRooms();
                    break;
                default:
                    break;
            }
        } else {
            MenuBuilder.displayChooserMenu("Actions", new String[]{"Reserve", "Reservations", "Quitter"});
            switch (MenuBuilder.optionChooser("Choose an option :")) {
                case 1:
                    UserOption.createReservation();
                    break;
                case 2:
                    UserOption.seeReservations();
                    break;
                default:
                    break;
            }
        }
    }
}
