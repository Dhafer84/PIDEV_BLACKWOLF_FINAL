/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import service.EventDAOImp;

/**
 *
 * @author user
 */
public class Event {
    // Properties

    private int eventId;

    private String eventName;
    private Date eventStartDate;
    private Date eventEndDate;


    private int eventAgeRange;
    private String eventAddress;
    private int eventMaxNumberParticipant;
    private int eventNumberReservation;
    private String eventDescription;

    private int userId;

    public Event(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    // Constructors
    public Event() {

    }

    public Event(int eventId, String eventName, Date eventStartDate, Date eventEndDate, int eventAgeRange, String eventAddress, int eventMaxNumberParticipant, int eventNumberReservation, String eventDescription, int userId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventAgeRange = eventAgeRange;
        this.eventAddress = eventAddress;
        this.eventMaxNumberParticipant = eventMaxNumberParticipant;
        this.eventNumberReservation = eventNumberReservation;
        this.eventDescription = eventDescription;
        this.userId = userId;
    }

    public Event(EventBuilder eventBuilder) {

        this.eventId = eventBuilder.eventId;
        this.eventName = eventBuilder.eventName;
        this.eventStartDate = eventBuilder.eventStartDate;
        this.eventEndDate = eventBuilder.eventEndDate;
        this.eventAgeRange = eventBuilder.eventAgeRange;
        this.eventAddress = eventBuilder.eventAddress;
        this.eventMaxNumberParticipant = eventBuilder.eventMaxNumberParticipant;
        this.eventNumberReservation = eventBuilder.eventNumberReservation;
        this.eventDescription = eventBuilder.eventDescription;
        this.userId = eventBuilder.userId;
    }

    // Getters and setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public int getEventAgeRange() {
        return eventAgeRange;
    }

    public void setEventAgeRange(int eventAgeRange) {
        this.eventAgeRange = eventAgeRange;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public int getEventMaxNumberParticipant() {
        return eventMaxNumberParticipant;
    }

    public void setEventMaxNumberParticipant(int eventMaxNumberParticipant) {
        this.eventMaxNumberParticipant = eventMaxNumberParticipant;
    }

    public int getEventNumberReservation() {
        return eventNumberReservation;
    }

    public void setEventNumberReservation(int eventNumberReservation) {
        this.eventNumberReservation = eventNumberReservation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Methods
    @Override
    public String toString() {
        return "Event{" + "eventId=" + eventId + ", eventName=" + eventName + ", eventStartDate=" + eventStartDate + ", eventEndDate=" + eventEndDate + ", eventAgeRange=" + eventAgeRange + ", eventAddress=" + eventAddress + ", eventMaxNumberParticipant=" + eventMaxNumberParticipant + ", eventNumberReservation=" + eventNumberReservation + ", eventDescription=" + eventDescription + ", userId=" + userId + '}';
    }
    // Event builder

    public void delete(EventDAOImp e2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class EventBuilder {

        private int eventId;

        private String eventName;
        private Date eventStartDate;
        private Date eventEndDate;

        private int eventAgeRange;
        private String eventAddress;
        public int eventMaxNumberParticipant;
        private int eventNumberReservation;
        private String eventDescription;
        private int userId;

        public EventBuilder() {
            super();
        }

        public EventBuilder(String text, String text0, String text1, String text2, String text3, java.sql.Date valueOf) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public EventBuilder eventId(int eventId) {
            this.eventId = eventId;
            return this;
        }

        public EventBuilder eventName(String eventName) {
            this.eventName = eventName;
            return this;
        }

        public EventBuilder eventStartDate(Date eventStartDate) {
            this.eventStartDate = eventStartDate;
            return this;
        }

        public EventBuilder eventEndDate(Date eventEndDate) {
            this.eventEndDate = eventEndDate;
            return this;
        }

        public EventBuilder eventAgeRange(int eventAgeRange) {
            this.eventAgeRange = eventAgeRange;
            return this;
        }

        public EventBuilder eventAddress(String eventAddress) {
            this.eventAddress = eventAddress;
            return this;
        }

        public EventBuilder eventMaxNumberParticipant(int eventMaxNumberParticipant) {
            this.eventMaxNumberParticipant = eventMaxNumberParticipant;
            return this;
        }

        public EventBuilder eventNumberReservation(int eventNumberReservation) {
            this.eventNumberReservation = eventNumberReservation;
            return this;
        }

        public EventBuilder eventDescription(String eventDescription) {
            this.eventDescription = eventDescription;
            return this;
        }

        public EventBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        // Return the finally constructed User object
        public Event build() {
            Event event = new Event(this);
            validateUserObject(event);
            return event;
        }

        private void validateUserObject(Event event) {
            // Do some basic validations to check
            // if user object does not break any assumption of system
        }
    }

}
