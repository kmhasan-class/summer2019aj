/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.hibernatedemo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author kmhasan
 */
public interface CrudDao<T, I> {
    void create(T t);
    List<T> retrieve();
    T retrieve(I id);
    // TODO add all other relevant methods
    // implement those methods for the Student type and also for another type of your choice
}
