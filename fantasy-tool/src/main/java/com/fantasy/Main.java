package com.fantasy;

import com.Logger.GlobalLogger;
import com.fantasy.Repository.*;


public class Main {
    public static void main(String[] args) {
        //TODO: configure logging pattern
        GlobalLogger.info("Starting application");

        IRepository repo = new PostgresRepo();
    

        GlobalLogger.info("Closing application");
    }
}