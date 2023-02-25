package me.demonis.sep.dao;

import me.demonis.sep.console.EntryBuilder;
import me.demonis.sep.entities.Reservation;
import me.demonis.sep.entities.Room;
import me.demonis.sep.utils.Constants;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoomDAO {


    public RoomDAO() {}

    /** Save Room in the database **/
    public Room saveRoom(int roomNumber, double pricePerNight, int capacity, double surface) throws SQLException {
        String sql = "INSERT INTO rooms (room_number, price_per_night, capacity, surface) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = Constants.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, roomNumber);
        statement.setDouble(2, pricePerNight);
        statement.setInt(3, capacity);
        statement.setDouble(4, surface);
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        Room room = null;
        if (generatedKeys.next()) {
            room = new Room(generatedKeys.getInt(1), roomNumber, pricePerNight, capacity, surface);
        }

        return room;
    }

    /** Update a Room in the database **/
    public void updateRoom(Room room) throws SQLException {
        String sql = "UPDATE rooms SET room_number=?, price_per_night=?, capacity=?, surface=? WHERE id=?";
        try (PreparedStatement statement = Constants.connection.prepareStatement(sql)) {
            statement.setInt(1, room.getRoomNumber());
            statement.setDouble(2, room.getPricePerNight());
            statement.setInt(3, room.getCapacity());
            statement.setDouble(4, room.getSurface());
            statement.setInt(5, room.getId());
            statement.executeUpdate();
        }
    }

    /** Delete a Room in the database **/
    public void deleteRoom(Room room) throws SQLException {
        String sql = "DELETE FROM rooms WHERE id=?";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        statement.setInt(1, room.getId());
        statement.executeUpdate();
    }

    /** Get all Rooms **/
    public List<Room> getAllRooms() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int roomNumber = resultSet.getInt("room_number");
            double pricePerNight = resultSet.getDouble("price_per_night");
            int capacity = resultSet.getInt("capacity");
            double surface = resultSet.getDouble("surface");
            Room room = new Room(id, roomNumber, pricePerNight, capacity, surface);
            rooms.add(room);
        }
        return rooms;
    }

    /** Get a Room by its id **/
    public Room getRoomById(int id) throws SQLException {
        String sql = "SELECT * FROM rooms WHERE id=?";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int roomNumber = resultSet.getInt("room_number");
            double pricePerNight = resultSet.getDouble("price_per_night");
            int capacity = resultSet.getInt("capacity");
            double surface = resultSet.getDouble("surface");
            return new Room(id, roomNumber, pricePerNight, capacity, surface);
        }

        return null;
    }

    /** Check if a Room is available at a date **/
    public boolean isAvailableRoom(Room room, LocalDate date) throws SQLException {
        List<Reservation> reservationList = Constants.resdao.getReservationByRoomNumber(room.getRoomNumber());
        for (Reservation res : reservationList) {
            if (res.getStartDate().compareTo(date) >= 0 || res.getEndDate().compareTo(date) < 0) return false;
        }
        return true;
    }

    /** Get all available Rooms at a date **/
    public List<Integer> getAvailableRooms(LocalDate date) {
        List<Integer> l = new ArrayList<Integer>();

        try {
            for (Room room : Constants.roomDao.getAllRooms()) {
                if (Constants.roomDao.isAvailableRoom(room, date)) {
                    System.out.print(room.getRoomNumber());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    /** Get all available Rooms from date a to date b **/
    public List<Room> getAvailableRooms(LocalDate datea, LocalDate dateb) {
        List<Room> l = new ArrayList<Room>();
        try {
            boolean t;
            for (Room room : Constants.roomDao.getAllRooms()) {
                t = false;
                for (Reservation res : Constants.resdao.getReservationByRoomNumber(room.getRoomNumber())) {
                    if (!datea.isAfter(res.getEndDate()) || !dateb.isBefore(res.getStartDate())) {
                        t = true;
                        break;
                    }
                }
                if (!t)  l.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return l;
    }
}
