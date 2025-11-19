package com.fantasy.Stats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fantasy.Model.WeekScore;
import com.fantasy.Repository.IRepository;
import com.fantasy.Repository.JPAUtil;

import jakarta.persistence.EntityManager;

public class Stats {
    //TODO: add computation of median luck score and all play percentage data
    // potentially through helper classes. 

    // method to compute median luck score for a week (probably)
    public static void computeMedianLuckScore(List<Long> rosterUserIds, int week, IRepository repo) {
        // get 
    }

public static void computeAllPlayLuckScore(List<Long> rosterUserIds, int lastWeekToCompute, IRepository repo) {
        // Map rosterUserId → all-play wins, losses
        Map<Long, Integer> allPlayWins = new HashMap<>();
        Map<Long, Integer> allPlayLosses = new HashMap<>();

        // Initialize counters
        for (Long id : rosterUserIds) {
            allPlayWins.put(id, 0);
            allPlayLosses.put(id, 0);
        }

        

    
        for (int week = 1; week <= lastWeekToCompute; week++) {

            // Get all WeekScores for this week for all requested roster user ids
            List<WeekScore> scores = repo.getWeekScoresByRosterUserIdsAndWeek(rosterUserIds, week);

            // Compare each score with every other score
            for (WeekScore s1 : scores) {
                for (WeekScore s2 : scores) {
                    if (s1.equals(s2)) continue; // skip self

                    if (s1.getScore() > s2.getScore()) {
                        allPlayWins.merge(s1.getId().getRosterUserId(), 1, Integer::sum);
                    } else if (s1.getScore() < s2.getScore()) {
                        allPlayLosses.merge(s1.getId().getRosterUserId(), 1, Integer::sum);
                    }
                }
            }
        }

        // Now compute final results
        for (Long id : rosterUserIds) {
            int wins = allPlayWins.get(id);
            int losses = allPlayLosses.get(id);

            double allPlayPct =
                    (wins + losses) == 0 ? 0 : (double) wins / (wins + losses);

            // TODO: load actual wins + losses from DB
            int actualWins = 0;   // replace with DB
            int actualLosses = 0; // replace with DB

            double actualPct =
                    (actualWins + actualLosses) == 0 ? 0 : (double) actualWins / (actualLosses + actualWins);

            double luckScore = actualPct - allPlayPct;
            // TODO:
            // persist AllPlayLuckScore to db 
            // (rosteruserId, allPlayWins, allPlayLosses, allPlayPercentage, actualWins, actualLosses, actualPercentage, luckScore)

        }

        
    }


    // method to compute total luck score
    public static void computeAllPlayPercentage(List<Long> rosterUserIds) {
        
    }
}
