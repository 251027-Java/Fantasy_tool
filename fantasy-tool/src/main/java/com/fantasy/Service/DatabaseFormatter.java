package com.fantasy.Service;

import java.util.ArrayList;
import java.util.List;

import com.Logger.GlobalLogger;
import com.fantasy.Model.Draft;
import com.fantasy.Model.League;
import com.fantasy.Model.Matchup;
import com.fantasy.Model.Player;
import com.fantasy.Model.PlayerPosition;
import com.fantasy.Model.PlayerPositionId;
import com.fantasy.Model.RosterUser;
import com.fantasy.Model.User;
import com.fantasy.Repository.IRepository;
import com.fantasy.Request.RequestModels.LeagueResponse;
import com.fantasy.Request.RequestModels.MatchupResponse;
import com.fantasy.Request.RequestModels.PlayerResponse;
import com.fantasy.Request.RequestModels.RosterUserResponse;
import com.fantasy.Request.RequestModels.UserResponse;

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
     */
    public void processRosterUser(List<RosterUserResponse> rosterResponses) {
        for (RosterUserResponse roster : rosterResponses) {
            // check if roster user mapping already in database
            // (done by checking for user_id and league_id)
            if (this.repo.getRosterUserByUserIdAndLeagueId(roster.getUserId(), roster.getLeagueId()) == null) {
                // insert new roster user mapping
                RosterUser dbRoster = new RosterUser(roster.getRosterId(), roster.getUserId(), roster.getLeagueId());
                this.repo.save(dbRoster);
                GlobalLogger.debug("RosterUser added: " + dbRoster.toString());
            }
        }
    }

    public void processMatchups(List<MatchupResponse> matchups, long leagueId, int weekNum) {
        for (MatchupResponse matchup : matchups) {
            // check if matchup already in database by league_id, roster_id, week
            if (this.repo.getRosterByLeagueIdAndRosterIdAndWeek(leagueId, matchup.getRosterId(), weekNum) == null) {
                Matchup dbMatchup = new Matchup(matchup.getMatchupId(), matchup.getRosterId(), matchup.getPoints());
                this.repo.save(dbMatchup);
                GlobalLogger.debug("Matchup added: " + dbMatchup.toString());
            }
        }
    }
    
}
