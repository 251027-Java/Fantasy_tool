package com.fantasy.Service;

import java.util.ArrayList;
import java.util.List;

import com.Logger.GlobalLogger;
import com.fantasy.Model.*;
import com.fantasy.Repository.IRepository;
import com.fantasy.Request.RequestModels.*;

/**
 * DatabaseFormatter is a class used to format the POJOs from sleeper into
 * the database format
 */
public class DatabaseFormatter {

    private IRepository repo;

    DatabaseFormatter(IRepository repo) {
        this.repo = repo;
    }

    public void processPlayers(List<PlayerResponse> players) {
        
        for (PlayerResponse playerResp : players) {
            // add player, overwriting if already exists
            Player player = new Player(
                playerResp.getPlayerId(),
                playerResp.getFullName(),
                playerResp.getTeam(),
                playerResp.getRotoworldId(),
                playerResp.getStatsId(),
                playerResp.getFantasyDataId()
            );
            // add player 
            this.repo.saveOrUpdate(player);

            // convert player positions (in string) to list of player position objects
            if (playerResp.getFantasyPositions() == null) {
                GlobalLogger.debug("No player positions found for player: " + player.getFullName());
                continue;
            }
            List<PlayerPosition> playerPositions = new ArrayList<>();
            for (String positionString : playerResp.getFantasyPositions()) {
                PlayerPositionId playerPositionId = new PlayerPositionId(player.getPlayerId(), positionString);
                PlayerPosition playerPosition = new PlayerPosition(playerPositionId);
                playerPositions.add(playerPosition);
            }
            // add positions
            this.repo.saveOrUpdate(playerPositions);

        }
        GlobalLogger.debug("Players added/updated in database");
    }

    /**
     * Process leagues into database format and insert. Also inputs draft skeleton
     * into database
     * @param leagues the response from sleeper
     * @return list of league ids
     */
    public List<Long> processLeagueInfo(List<LeagueResponse> leagues) { 
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

    public  void processUsers(List<UserResponse> users) { 
        for (UserResponse user : users) {
            // check if user already in database
            if (this.repo.getUserById(user.getUserId()) == null) {
                User dbUser = new User(user.getUserId(), user.getDisplayName());
                this.repo.save(dbUser);
                GlobalLogger.debug("User added: " + dbUser.toString());
            }
        }
    }

    /**
     * Process rosters user mapping into database
     * @param rosterResponses the response from sleeper
     * @return 
     */
    public List<Long> processRosterUser(List<RosterUserResponse> rosterResponses) {
        List<Long> rosterUserIds = new ArrayList<>();
        for (RosterUserResponse roster : rosterResponses) {
            // check if roster user mapping already in database
            // (done by checking for user_id and league_id)
            RosterUser dbRosterUser = this.repo.getRosterUserByUserIdAndLeagueId(roster.getUserId(), roster.getLeagueId());
            RosterUser newRosterUser;
            if (dbRosterUser == null) {
                // insert new roster user mapping
                newRosterUser = new RosterUser(roster.getRosterId(), roster.getUserId(), roster.getLeagueId());
                this.repo.save(newRosterUser);
                GlobalLogger.debug("RosterUser added: " + newRosterUser.toString());
            } else {
                newRosterUser = dbRosterUser;
            }
            rosterUserIds.add(newRosterUser.getRosterUserId());
        }
        return rosterUserIds;
    }
        

    public void processMatchups(List<MatchupResponse> matchups, long leagueId, int weekNum) {
        for (MatchupResponse matchup : matchups) {
            // check if week score already in database
            RosterUser rosterUser = this.repo.getRosterUserByRosterIdAndLeagueId(matchup.getRosterId(), leagueId);
            WeekScoreId weekNumId = new WeekScoreId(rosterUser.getRosterUserId(), weekNum);
            if (this.repo.getWeekScoreById(weekNumId) == null) {
                // get roster user mapping by user_id and league_id
                
                WeekScore weekScore = new WeekScore(weekNumId, matchup.getPoints());
                this.repo.save(weekScore);
                GlobalLogger.debug("WeekScore added: " + weekScore.toString());

                // TODO: add player score data per week to database for advanced stat calculation
                
            }
        }
    }
    
}
