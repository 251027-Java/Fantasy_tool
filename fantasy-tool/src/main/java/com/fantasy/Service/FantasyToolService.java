package com.fantasy.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.Logger.GlobalLogger;
import com.fantasy.Model.League;
import com.fantasy.Repository.*;
import com.fantasy.Request.SleeperRequestHandler;
import com.fantasy.Request.RequestModels.LeagueResponse;
import com.fantasy.Request.RequestModels.UsernameResponse;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class FantasyToolService {
    private final IRepository repo;
    private final Scanner scan;
    private static final ObjectMapper om = new ObjectMapper();

    public FantasyToolService(IRepository repo) {
        this.repo = repo;
        this.scan = new Scanner(System.in);
    }

    public void start() {
        // get userId
        clearScreen();
        System.out.println("Welcome to Fantasy Tool!");
        long userId = this.getUserId();
        GlobalLogger.info("UserId: " + userId);
        // clearScreen();
        // get leagues based on userId (assumes the current year)
        List<LeagueResponse> leagues = this.getLeaguesFromUserId(userId); 
        // process leagues into db format and 

        // ask user to choose a league from options

        // add users from chosen league into database (if not there already)

        // show stats, and choose to [q] quit, [c] choose a different league
        // maybe later, add option to choose a different username


        System.out.println("Closing application");
        

        // get league info
    }

    /**
     *  Take league info from sleeper and input into database
     */
    public void getLeagueInfo()  {

    }
    
    /**
     * Get userId from sleeper based on input username from user
     * @return userId
     */
    private long getUserId(){
        // prompt user for username until valid httpresponse is returned
        do {
            System.out.println("Please enter your sleeper username");
            String username = this.scan.nextLine();
            try {
                HttpResponse<String> response = SleeperRequestHandler.getUserFromUsername(username);

                // had a valid username, now need to get userId from user_id
                // attribute in response
                if (response.statusCode() == 200) {
                    
                    UsernameResponse resp = om.readValue(response.body(), UsernameResponse.class);
                    long userId = resp.getUser_id();
                    return userId;
                }
            } catch (IOException |InterruptedException e) {
                GlobalLogger.debug("Could not get players", e);
                System.out.println("Invalid username");
            }
        } while (true);
    }

    private List<LeagueResponse> getLeaguesFromUserId(long userId) {
        // get the current year from time clock
        int year = LocalDate.now().getYear();
        try {
            HttpResponse<String> response = SleeperRequestHandler.getLeaguesFromUserIDAndSeason(userId, year);
            if (response.statusCode() == 200) {
                List<LeagueResponse> resp = om.readValue(response.body(), new TypeReference<List<LeagueResponse>>(){});
                return resp;
            }
        } catch (IOException | InterruptedException e) {
            GlobalLogger.debug("Could not get players", e);
            System.out.println("Invalid username");
        }
        return null;
    } 

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    
}
