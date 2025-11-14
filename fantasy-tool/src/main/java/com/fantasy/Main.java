package com.fantasy;

import com.Logger.GlobalLogger;
import com.fantasy.Repository.*;
import com.fantasy.Service.FantasyToolService;


public class Main {
    public static void main(String[] args) {
    
        GlobalLogger.info("Starting application");

        IRepository repo = new PostgresRepo();
        FantasyToolService service = new FantasyToolService(repo);
        service.start();
    

        GlobalLogger.info("Closing application");

        
    }
}