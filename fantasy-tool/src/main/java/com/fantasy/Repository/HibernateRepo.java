package com.fantasy.Repository;

import com.fantasy.Model.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateRepo implements IRepository {

    public void save(Player player) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(player);
            tx.commit();
        }
    }

    public void save(League league) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(league);
            tx.commit();
        }
    }

    public void save(Draft draft) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(draft);
            tx.commit();
        }
    }

    public void save(User dbUser) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(dbUser);
            tx.commit();
        }
    }

    public void save(SystemMetadata systemMetadata) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(systemMetadata);
            tx.commit();
        }
    }

    // search for league by id 
    public League getLeagueById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(League.class, id);
        }
    }

    public Draft getDraftById(long draftId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Draft.class, draftId);
        }
    }

    public User getUserById(long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(User.class, userId);
        }
    }

    public List<League> getLeaguesById(List<Long> leagueIds) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from League where id in :ids", League.class)
                .setParameter("ids", leagueIds)
                .getResultList();
        }
    }

    public List<User> getUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).getResultList();
        }
    } 

    public SystemMetadata getSystemMetadata(String key) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(SystemMetadata.class, key);
        }
    }

    public String getLastUpdatedPlayers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(SystemMetadata.class, "last_updated_players").getValue();
        }
    }

    public void updateSystemMetadata(String key, String value) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            SystemMetadata systemMetadata = session.find(SystemMetadata.class, key);
            systemMetadata.setValue(value);
            tx.commit();
        }
    }



    public void close() {
        HibernateUtil.shutdown();
    }

}
