package com.fantasy.Service;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.Logger.GlobalLogger;
import com.fantasy.Exception.HttpConnectionException;
import com.fantasy.Model.*;
import com.fantasy.Repository.*;
import com.fantasy.Request.SleeperRequestHandler;
import com.fantasy.Request.RequestModels.*;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class FantasyToolService implements Closeable {
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
        GlobalLogger.debug("UserId: " + userId);
        // clearScreen();
        // get leagues based on userId (assumes the current year)
        List<LeagueResponse> leagues = this.getLeaguesFromUserId(userId); 
        GlobalLogger.debug("Leagues gotten from sleeper: "+ leagues.toString());
        // process leagues into db format and insert, returning list of league ids
        List<Long> leagueIds = this.processLeagueInfo(leagues);

        // query database for leagues with league ids
        List<League> dbLeagues = this.repo.getLeaguesById(leagueIds);
        GlobalLogger.debug("Leagues gotten from database: "+ dbLeagues.toString());

        // ask user to choose a league from options
        clearScreen();
        long chosenLeagueId = this.chooseLeague(dbLeagues);
        League chosenLeague = this.repo.getLeagueById(chosenLeagueId);
        GlobalLogger.debug("League chosen: " + chosenLeague.getLeagueName());
        

        // add users from chosen league into database (if not there already)
        // List<UsernameResponse> users = this.getUsersFromLeague(chosenLeagueId);


        // get players from sleeper


        // add players into database (if not there already)

        // get rosters from sleeper

        // compute stats from rosters information (lots of logic here)

        // show stats, and choose to [q] quit, [c] choose a different league
        // maybe later, add option to choose a different username


        System.out.println("Closing application");
        

        // get league info
    }

    /**
     * Process leagues into database format and insert. Also inputs draft skeleton
     * into database
     * @param leagues the response from sleeper
     * @return
     */
    private List<Long> processLeagueInfo(List<LeagueResponse> leagues) { 
        List<Long> leagueIds = new ArrayList<>();
        // put leagues and draft into database
        for (LeagueResponse leagueResponse : leagues) {
            // check if league already in database
            if ( this.repo.getLeagueById(leagueResponse.getLeagueId()) == null) {
                League league = new League(
                leagueResponse.getLeagueId(),
                leagueResponse.getNumTeams(), 
                leagueResponse.getName(), 
                leagueResponse.getSeasonYear());
                this.repo.save(league);
                GlobalLogger.debug("League added: " + league.toString());
            }
            // check if draft already in database
            if (this.repo.getDraftById(leagueResponse.getDraftId()) == null) {
                Draft draft = new Draft(leagueResponse.getDraftId(), leagueResponse.getLeagueId());
                this.repo.save(draft);
                GlobalLogger.debug("Draft added: " + draft.toString());
            }
            leagueIds.add(leagueResponse.getLeagueId());
        }
        return leagueIds;
    }
    
    /**
     * Get userId from sleeper based on input username from user
     * @return userId
     */
    private long getUserId(){
        // prompt user for username until valid httpresponse is returned
        do {
            System.out.println("Please enter your Sleeper username");
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
            } catch (HttpConnectionException e) {
                GlobalLogger.debug("Could not find user", e);
                System.out.println("Invalid username");
            }
        } while (true);
    }

    /**
     * Get leagues for the current year from sleeper based on userId
     * @param userId the user_id of leagues to look for
     * @return
     */
    private List<LeagueResponse> getLeaguesFromUserId(long userId) {
        // get the current year from time clock
        int year = LocalDate.now().getYear();

        try {
            HttpResponse<String> response = SleeperRequestHandler.getLeaguesFromUserIDAndSeason(userId, year);
            if (response.statusCode() == 200) {
                List<LeagueResponse> resp = om.readValue(response.body(), new TypeReference<List<LeagueResponse>>(){});
                return resp;
            }
        } catch (HttpConnectionException e) {
            GlobalLogger.debug(String.format("Could not get leagues from user_id '%s'", userId), e);
            System.out.println("No leagues found");
        }
        return List.of();
    } 

    public Long chooseLeague(List<League> leagues) {
        // prompt user to choose a league

        do {
            System.out.println("Please choose a league");
            for (int i = 0; i < leagues.size(); i++) {
                System.out.printf("[%d] %s\n\n", i+1, leagues.get(i).getLeagueName());
            }
            String input = this.scan.nextLine();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= leagues.size()) { // 1-indexed for user
                    return leagues.get(choice-1).getLeagueId();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        } while (true);
        // return the id of the chosen league
    }

    // public List<UserResponse> getUsersFromLeague(long leagueId) {
    //     try {
    //         HttpResponse<String> response = SleeperRequestHandler.getUsersFromLeague(leagueId);
    //         if (response.statusCode() == 200) {
                
    //         }
    //     } catch (HttpConnectionException e) {
    //         GlobalLogger.debug(String.format("Could not get users from league_id '%s'", leagueId), e);
    //         System.out.println("No users found");
    //     }
    // }

    /**
     * Clears the screen. Utility method for cleaning the terminal
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void close() throws IOException {
        this.repo.close();
    }


    
}
