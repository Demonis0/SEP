package me.demonis.sep.dao;

import me.demonis.sep.entities.Reservation;
import me.demonis.sep.entities.User;
import me.demonis.sep.utils.Constants;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {



    public ReservationDAO() {}

    /** Add a Reservation to the database **/
    public Reservation saveReservation(String firstName, String lastName, int numPersons, LocalDate startDate, LocalDate endDate, double totalPrice, int room_id) throws SQLException {
        String sql = "INSERT INTO reservations (first_name, last_name, num_persons, start_date, end_date, total_price, room_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = Constants.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        statement.setInt(3, numPersons);
        statement.setDate(4, Date.valueOf(startDate));
        statement.setDate(5, Date.valueOf(endDate));
        statement.setDouble(6, totalPrice);
        statement.setInt(7, room_id);
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        Reservation res = null;
        if (rs.next()) {
            res = new Reservation(rs.getInt(1),firstName, lastName, numPersons, startDate, endDate, totalPrice, room_id);
        }
        rs.close();
        statement.close();
        return res;
    }

    /** Update a Reservation in the database **/
    public void updateReservation(Reservation reservation) throws SQLException {
        String sql = "UPDATE reservations SET first_name=?, last_name=?, num_persons=?, start_date=?, end_date=?, total_price=?, room_id=? WHERE id=?";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        statement.setString(1, reservation.getFirstName());
        statement.setString(2, reservation.getLastName());
        statement.setInt(3, reservation.getNumPersons());
        statement.setDate(4, Date.valueOf(reservation.getStartDate()));
        statement.setDate(5, Date.valueOf(reservation.getEndDate()));
        statement.setDouble(6, reservation.getTotalPrice());
        statement.setDouble(7, reservation.getRoom_id());
        statement.setInt(8, reservation.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** Delete a Reservation **/
    public void deleteReservation(int id) throws SQLException {
        String sql = "DELETE FROM reservations WHERE id=?";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }

    /** Get all Reservations **/
    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        Statement statement = Constants.connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int numPersons = rs.getInt("num_persons");
            Date startDate = rs.getDate("start_date");
            Date endDate = rs.getDate("end_date");
            double totalPrice = rs.getDouble("total_price");
            int room_id = rs.getInt("room_id");
            Reservation reservation = new Reservation(id, firstName, lastName, numPersons, startDate.toLocalDate(), endDate.toLocalDate(), totalPrice, room_id);
            reservations.add(reservation);
        }
        rs.close();
        statement.close();
        return reservations;
    }

    /** Get a Reservation by its id **/
    public Reservation getReservationById(int id) throws SQLException {
        String sql = "SELECT * FROM reservations WHERE id=?";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Reservation reservation = null;
        if (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int numPersons = rs.getInt("num_persons");
            Date startDate = rs.getDate("start_date");
            Date endDate = rs.getDate("end_date");
            double totalPrice = rs.getDouble("total_price");
            int room_id = rs.getInt("room_id");
            reservation = new Reservation(id, firstName, lastName, numPersons, startDate.toLocalDate(), endDate.toLocalDate(), totalPrice, room_id);
        }
        rs.close();
        statement.close();
        return reservation;
    }

    /** Get a Reservation by its room number **/
    public List<Reservation> getReservationByRoomNumber(int nb) throws SQLException {
        String sql = "SELECT reservations.* FROM reservations JOIN rooms ON reservations.room_id = rooms.id WHERE rooms.room_number=?";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        statement.setInt(1, nb);
        ResultSet rs = statement.executeQuery();
        List<Reservation> reservationList = new ArrayList<Reservation>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int numPersons = rs.getInt("num_persons");
            Date startDate = rs.getDate("start_date");
            Date endDate = rs.getDate("end_date");
            double totalPrice = rs.getDouble("total_price");
            int room_id = rs.getInt("room_id");
            reservationList.add(new Reservation(id, firstName, lastName, numPersons, startDate.toLocalDate(), endDate.toLocalDate(), totalPrice, room_id));
        }
        rs.close();
        statement.close();
        return reservationList;
    }

    /** Get all Reservations of a User **/
    public List<Reservation> getUserReservation(User user) throws SQLException {
        String sql = "SELECT * FROM reservations WHERE first_name=? AND last_name=?";
        PreparedStatement statement = Constants.connection.prepareStatement(sql);
        statement.setString(1, user.getFirst_name());
        statement.setString(2, user.getLast_name());
        ResultSet rs = statement.executeQuery();
        List<Reservation> reservationList = new ArrayList<Reservation>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int numPersons = rs.getInt("num_persons");
            Date startDate = rs.getDate("start_date");
            Date endDate = rs.getDate("end_date");
            double totalPrice = rs.getDouble("total_price");
            int room_id = rs.getInt("room_id");
            reservationList.add(new Reservation(id, firstName, lastName, numPersons, startDate.toLocalDate(), endDate.toLocalDate(), totalPrice, room_id));
        }
        rs.close();
        statement.close();
        return reservationList;
    }
}
