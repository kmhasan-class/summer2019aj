/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.hibernatedemo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author kmhasan
 */
public class StudentDao implements CrudDao<Student, Long> {

    @Override
    public void create(Student student) {
        Session session = null;
        Transaction transaction = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        } finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public List<Student> retrieve() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Student> query = session.createQuery("from Student", Student.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }

    @Override
    public Student retrieve(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Student> query = session.createQuery("from Student where id = :id", Student.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }    
}
