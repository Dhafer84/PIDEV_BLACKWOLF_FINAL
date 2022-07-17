/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author user
 */
public class reservation {


    private int reservation_id;
    private User user_id;
    
   private Event  event_id;

 

    @Override
    public String toString() {
        return "reservation{" + "reservation_id=" + reservation_id + ", user_id=" + user_id + ", event_id=" + event_id + '}';
    }

    public reservation(int reservation_id, User user_id, Event event_id) {
        this.reservation_id = reservation_id;
        this.user_id = user_id;
        this.event_id = event_id;
    }

    public reservation() {
    }

    public reservation(User user_id, Event event_id) {
        this.user_id = user_id;
        this.event_id = event_id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Event getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Event event_id) {
        this.event_id = event_id;
    }

   
}
