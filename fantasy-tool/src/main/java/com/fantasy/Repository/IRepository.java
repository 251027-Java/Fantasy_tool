package com.fantasy.Repository;

import java.io.Closeable;
import java.sql.SQLException;

import com.fantasy.Model.*;

public interface IRepository extends Closeable {
    public void save(Player player);
    public void save(League league);
    
}
