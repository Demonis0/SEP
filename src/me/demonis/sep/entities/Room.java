package me.demonis.sep.entities;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomNumber,capacity, id;
    private double pricePerNight,surface;

    public Room(int id, int roomNumber, double pricePerNight, int capacity, double surface) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.surface = surface;
        this.capacity = capacity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    @Override
    public String toString() {
        return "Room:{" +
                "roomNumber:" + roomNumber +
                ", capacity:" + capacity +
                ", id:" + id +
                ", pricePerNight:" + pricePerNight +
                ", surface:" + surface +
                '}';
    }
}

