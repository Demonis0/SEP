package me.demonis.sep.utils;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.TreeMap;

public class Utils {
    private static final Random rnd = new Random();
    private static final DecimalFormat moneyFormat = new DecimalFormat("#,##0.00");

    public static final String doubleRegex = "^(-)?\\d+(\\.\\d{1,2})?$";


    /**
     * Returns a formatted string representation of a money value.
     * @param money the money value to format
     * @return a formatted string representation of the money value
     */
    public static String getMoneyString(double money) {
        return moneyFormat.format(money) + "$";
    }
}
