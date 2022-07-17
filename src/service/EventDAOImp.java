/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Datasource;

/**
 *
 * @author user
 */
public class EventDAOImp implements EventDAO {

    public static Event Event;

    // CRUD - Retrieve
    @Override
    public Event get(int id) {
        Connection con = Datasource.getConnection();
        Event event = null;

        String sql = "SELECT * FROM evenement WHERE event_id  = ?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int eventId = rs.getInt("event_id");
                String eventName = rs.getString("event_name");
                Date eventStartDate = rs.getDate("event_start_date");
                Date eventEndDate = rs.getDate("event_end_date");
                int eventAgeRange = rs.getInt("event_age_range");
                String eventAddress = rs.getString("event_address");
                int eventMaxNumberParticipant = rs.getInt("event_max_number_participant");
                int eventNumberReservation = rs.getInt("event_number_reservation");
                String eventDescription = rs.getString("event_description");
                int userId = rs.getInt("user_id");

                event = new Event.EventBuilder().eventId(eventId).eventName(eventName).eventStartDate(eventStartDate).eventEndDate(eventEndDate).eventAgeRange(eventAgeRange).eventAddress(eventAddress)
                        .eventMaxNumberParticipant(eventMaxNumberParticipant).eventNumberReservation(eventNumberReservation).eventDescription(eventDescription).userId(userId).build();
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return event;
    }

    // CRUD - Retrieve All
    @Override
    public List<Event> getAll() throws SQLException {

        Connection con = Datasource.getConnection();
        String sql = "SELECT * FROM evenement";

        List<Event> events = new ArrayList<>();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        Event event;
        while (rs.next()) {
            int eventId = rs.getInt("event_id");
            String eventName = rs.getString("event_name");
            Date eventStartDate = rs.getDate("event_start_date");
            Date eventEndDate = rs.getDate("event_end_date");
            int eventAgeRange = rs.getInt("event_age_range");
            String eventAddress = rs.getString("event_address");
            int eventMaxNumberParticipant = rs.getInt("event_max_number_participant");
            int eventNumberReservation = rs.getInt("event_number_reservation");
            String eventDescription = rs.getString("event_description");
            int userId = rs.getInt("user_id");

            event = new Event.EventBuilder().eventId(eventId).eventName(eventName).eventStartDate(eventStartDate).eventEndDate(eventEndDate).eventAgeRange(eventAgeRange).eventAddress(eventAddress)
                    .eventMaxNumberParticipant(eventMaxNumberParticipant).eventNumberReservation(eventNumberReservation).eventDescription(eventDescription).userId(userId).build();

            events.add(event);
        }

        return events;
    }

    // CRUD - Create or Update
    @Override
    public int save(Event event) {
        return 0;
    }

    // CRUD - Create
    @Override
    public int insert(Event event) throws SQLException {
        Connection connection = Datasource.getConnection();
        int result = -1;
        if (connection != null) {
            String insertQuery = "INSERT INTO evenement (event_name, event_start_date, 	event_end_date, event_age_range,event_address, event_max_number_participant, event_number_reservation,event_description,user_id) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement insertps = connection.prepareStatement(insertQuery);

            insertps.setString(1, event.getEventName());
            insertps.setDate(2, (java.sql.Date) event.getEventStartDate());
            insertps.setDate(3, (java.sql.Date) event.getEventEndDate());
            insertps.setInt(4, event.getEventAgeRange());
            insertps.setString(5, event.getEventAddress());
            insertps.setInt(6, event.getEventMaxNumberParticipant());
            insertps.setInt(7, event.getEventNumberReservation());
            insertps.setString(8, event.getEventDescription());
            insertps.setInt(9, event.getUserId());

            result = insertps.executeUpdate();

            Datasource.closePreparedStatement(insertps);
        }

        return result;
    }

    // CRUD - Update
    @Override
    public int update(Event event) throws SQLException {
        Connection connection = Datasource.getConnection();
        int result = -1;
        if (connection != null) {
            System.out.println(event);
            String updateQuery = " UPDATE evenement "
                    + " SET event_name = ?, event_start_date = ?, event_end_date = ?, event_age_range = ?, event_address = ?, event_max_number_participant = ?, event_number_reservation = ?, event_description = ?, user_id = ? "
                    + " WHERE event_id = ?";
            PreparedStatement updateps = connection.prepareStatement(updateQuery);

            updateps.setString(1, event.getEventName());
            updateps.setDate(2, (java.sql.Date) event.getEventStartDate());
            updateps.setDate(3, (java.sql.Date) event.getEventEndDate());
            updateps.setInt(4, event.getEventAgeRange());
            updateps.setString(5, event.getEventAddress());
            updateps.setInt(6, event.getEventMaxNumberParticipant());
            updateps.setInt(7, event.getEventNumberReservation());
            updateps.setString(8, event.getEventDescription());
            updateps.setInt(9, event.getUserId());
            updateps.setInt(10, event.getEventId());

            result = updateps.executeUpdate();
            Datasource.closePreparedStatement(updateps);
        }
        System.out.println("query succeeded" + event.getEventId());
        return result;
    }

    // CRUD - Delete
    @Override
    public int delete(Event event) throws SQLException {
        Connection connection = Datasource.getConnection();

        String sql = "DELETE FROM evenement WHERE event_id = ?";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, event.getEventId());

        int result = ps.executeUpdate();

        Datasource.closePreparedStatement(ps);

        return result;
    }

    @Override
  
      public List<Event> getAllByUserConncet(int id) throws SQLException {

        Connection con = Datasource.getConnection();
        String sql = "SELECT * FROM evenement where user_id=2";

        List<Event> events = new ArrayList<>();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        Event event;
        while (rs.next()) {
            int eventId = rs.getInt("event_id");
            String eventName = rs.getString("event_name");
            Date eventStartDate = rs.getDate("event_start_date");
            Date eventEndDate = rs.getDate("event_end_date");
            int eventAgeRange = rs.getInt("event_age_range");
            String eventAddress = rs.getString("event_address");
            int eventMaxNumberParticipant = rs.getInt("event_max_number_participant");
            int eventNumberReservation = rs.getInt("event_number_reservation");
            String eventDescription = rs.getString("event_description");
            int userId = rs.getInt("user_id");

            event = new Event.EventBuilder().eventId(eventId).eventName(eventName).eventStartDate(eventStartDate).eventEndDate(eventEndDate).eventAgeRange(eventAgeRange).eventAddress(eventAddress)
                    .eventMaxNumberParticipant(eventMaxNumberParticipant).eventNumberReservation(eventNumberReservation).eventDescription(eventDescription).userId(userId).build();

            events.add(event);
        }

        return events;
    }
}
