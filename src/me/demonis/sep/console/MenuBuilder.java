package me.demonis.sep.console;

import me.demonis.sep.utils.Constants;

public class MenuBuilder {

    private static int maxChooser = 0;

    public static int getMaxChooser() {
        return maxChooser;
    }


    public static void displayChooserMenu(String menuName, String[] options) {
        String message = "========== " + menuName + " ==========\n";
        int i = 1;

        for (String option : options) {
            message += String.format("%-2d | %s\n", i, option);
            i++;
        }

        message += "======================" + "=".repeat(menuName.length());
        System.out.println();
        System.out.println(message);
        maxChooser = i - 1;
    }


    public static void displayChooserMenu(String[] options) {
        displayChooserMenu("Menu", options);
    }


    public static int optionChooser(String message) {
        int choosenOption = 0;

        while (choosenOption <= 0 || choosenOption > maxChooser) {
            try {
                System.out.print(message);
                choosenOption = Constants.sc.nextInt();

                if (choosenOption > maxChooser || choosenOption <= 0)
                    throw new IllegalArgumentException();
            } catch (Exception e) {
                System.out.print("Vous devez entrer un nombre entier compris entre 1 et " + maxChooser + ".\n");
                choosenOption = 0;
            }
            Constants.sc.nextLine();
        }
        return choosenOption;
    }


    public static int optionChooser() {
        return optionChooser("Choisissez une option : ");
    }
}
