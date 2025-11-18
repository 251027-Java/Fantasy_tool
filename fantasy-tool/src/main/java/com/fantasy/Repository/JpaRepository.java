package com.fantasy.Repository;

import com.fantasy.Model.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class JpaRepository implements IRepository {

    public void saveOrUpdate(Player player) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Player dbPlayer = em.find(Player.class, player.getPlayerId());
            if (dbPlayer == null) {
                em.persist(player);
            } else {
                dbPlayer.setFullName(player.getFullName());
                dbPlayer.setTeam(player.getTeam());
                dbPlayer.setRotoworldId(player.getRotoworldId());
                dbPlayer.setStatsId(player.getStatsId());
                dbPlayer.setFantasyDataId(player.getFantasyDataId());
            }

            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive())
                tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    private Player getPlayerByPlayerId(String playerId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Player.class, playerId);
        } finally {
            em.close();
        }
    }

    public void save(League league) {
        persistEntity(league);
    }

    public void save(Draft draft) {
        persistEntity(draft);
    }

    public void save(User user) {
        persistEntity(user);
    }

    public void save(SystemMetadata meta) {
        persistEntity(meta);
    }

    public void save(RosterUser rosterUser) {
        persistEntity(rosterUser);
    }

    private void persistEntity(Object entity) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(entity);
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive())
                tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }


    public void saveOrUpdate(List<PlayerPosition> playerPositions) {
        if (playerPositions.isEmpty())
            return;

        String playerId = playerPositions.get(0).getId().getPlayerId();

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            List<PlayerPosition> existing = getPlayerPositionsByPlayerIdInternal(em, playerId);

            if (existing.isEmpty()) {
                for (PlayerPosition p : playerPositions)
                    em.persist(p);
            } else {
                // Insert new ones
                for (PlayerPosition p : playerPositions) {
                    if (!existing.contains(p)) {
                        em.persist(p);
                    }
                }

                // Remove ones no longer present
                List<PlayerPosition> toRemove = new ArrayList<>();
                for (PlayerPosition ep : existing) {
                    if (!playerPositions.contains(ep)) {
                        toRemove.add(ep);
                    }
                }
                for (PlayerPosition ep : toRemove)
                    em.remove(ep);
            }

            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive())
                tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    private List<PlayerPosition> getPlayerPositionsByPlayerIdInternal(EntityManager em, String playerId) {
        return em.createQuery(
                "from PlayerPosition p where p.id.playerId = :playerId",
                PlayerPosition.class)
            .setParameter("playerId", playerId)
            .getResultList();
    }


    public List<PlayerPosition> getPlayerPositionsByPlayerId(String playerId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return getPlayerPositionsByPlayerIdInternal(em, playerId);
        } finally {
            em.close();
        }
    }

    public League getLeagueById(long id) {
        return findById(League.class, id);
    }

    public Draft getDraftById(long id) {
        return findById(Draft.class, id);
    }

    public User getUserById(long id) {
        return findById(User.class, id);
    }

    public SystemMetadata getSystemMetadata(String key) {
        return findById(SystemMetadata.class, key);
    }

    private <T> T findById(Class<T> cls, Object id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(cls, id);
        } finally {
            em.close();
        }
    }

    public RosterUser getRosterUserByUserIdAndLeagueId(long userId, long leagueId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "from RosterUser where userId = :userId and leagueId = :leagueId",
                    RosterUser.class)
                .setParameter("userId", userId)
                .setParameter("leagueId", leagueId)
                .getSingleResultOrNull();
        } finally {
            em.close();
        }
    }


    public List<League> getLeaguesById(List<Long> leagueIds) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "from League where id in :ids",
                    League.class).setParameter("ids", leagueIds).getResultList();
        } finally {
            em.close();
        }
    }

    public List<User> getUsers() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("from User", User.class).getResultList();
        } finally {
            em.close();
        }
    }

    public String getLastUpdatedPlayers() {
        SystemMetadata meta = getSystemMetadata("last_updated_players");
        return meta != null ? meta.getValue() : null;
    }

    public void updateSystemMetadata(String key, String value) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            SystemMetadata meta = em.find(SystemMetadata.class, key);
            if (meta != null) {
                meta.setValue(value);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx.isActive())
                tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public void close() {
        JPAUtil.shutdown();
    }
}
