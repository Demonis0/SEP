package me.demonis.sep.entities;

import java.time.LocalDate;

public class Reservation {
    private int id, numPersons, room_id;
    private String firstName, lastName;
    private LocalDate startDate,endDate;
    private double totalPrice;

    public Reservation(int id, String firstName, String lastName, int numPersons, LocalDate startDate, LocalDate endDate, double totalPrice, int room_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numPersons = numPersons;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.room_id = room_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumPersons() {
        return numPersons;
    }

    public void setNumPersons(int numPersons) {
        this.numPersons = numPersons;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getRoom_id() {return room_id;}

    public void setRoom_id(int room_id) {this.room_id = room_id;}

    @Override
    public String toString() {
        return "Reservation:{" +
                "id:" + id +
                ", numPersons:" + numPersons +
                ", room_id:" + room_id +
                ", firstName:'" + firstName + '\'' +
                ", lastName:'" + lastName + '\'' +
                ", startDate:" + startDate +
                ", endDate:" + endDate +
                ", totalPrice:" + totalPrice +
                '}';
    }
}
