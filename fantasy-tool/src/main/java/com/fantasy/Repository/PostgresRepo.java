package com.fantasy.Repository;

import com.Logger.GlobalLogger;
import java.sql.*;

public class PostgresRepo implements IRepository {
    private static final String Postgre_URL = "jdbc:postgresql://localhost:5432/expensesdb";
    private static final String Postgre_User = "postgres";
    private static final String Postgre_PW = "mysecretpassword";
    private Connection connection;

    public PostgresRepo() {
        try {
            connection = DriverManager.getConnection(Postgre_URL, Postgre_User, Postgre_PW);
            try {
                createTables();
            } catch (SQLException e) {
                GlobalLogger.getLog().error("Could not create tables", e);
            }
        } catch (SQLException e) {
            GlobalLogger.getLog().error("Could not connect to database", e);
        }
    }

    /**
     * Initializes the tables necessary for the application if they are not already
     * created Must have
     * already created a connection to the database
     *
     * @throws SQLException
     */
    private void createTables() throws SQLException {
        
        Statement st = this.connection.createStatement();

        String createTablesString = "TODO: make tables string";
        st.execute(createTablesString);
    }
}
