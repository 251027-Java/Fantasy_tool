package com.fantasy.Repository;

import java.io.Closeable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.fantasy.Model.*;

public interface IRepository extends Closeable {
    public void save(Player player);
    public void save(League league);
    public void save(Draft draft);
    public League getLeagueById(long id);
    public Object getDraftById(long draftId);
    public List<League> getLeaguesById(List<Long> leagueIds);
    public Object getUserById(long userId);
    public void save(User dbUser);
    public SystemMetadata getSystemMetadata(String key);
    public void save(SystemMetadata systemMetadata);
    public void updateSystemMetadata(String string, String string2);
    public String getLastUpdatedPlayers();
    
}
