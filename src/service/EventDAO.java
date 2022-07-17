/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;

import entity.Event;


/**
 *
 * @author user
 */
public interface EventDAO extends DAO<Event>{
	List<Event> getAllByUserConncet(int id) throws SQLException;
}
