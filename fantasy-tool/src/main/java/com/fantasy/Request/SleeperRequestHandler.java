package com.fantasy.Request;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.Logger.GlobalLogger;

public class SleeperRequestHandler {

    private static String baseUrl = "https://api.sleeper.app/v1";

    public static String getBaseUrl() {
        return baseUrl;
    }
    /**
     * Get all the players from api. Read as a InputStream because it is a very large 
     * response (multiple megabytes).
     * @return The response from sleeper api
     * @throws ExceptionInInitializerError if an I/O error occurs when 
     * sending or receiving or if the request is interrupted
     */
    public static HttpResponse<InputStream> getPlayers()
            throws ExceptionInInitializerError {
        HttpClient client = HttpClient.newHttpClient();

        // build request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/players/nfl"))
                .GET()
                .build();

        // send the request and get the response
        try {
            HttpResponse<InputStream> response = client.send(request, BodyHandlers.ofInputStream());
            return response;
        } catch (Exception e) {
            GlobalLogger.error("Could not get players", e);
            throw new ExceptionInInitializerError(e);
        }
    }

   
    /**
     * Get sleeper user from username
     * @param username
     * @return The response from sleeper api
     * @throws IOException if an I/O error occurs when sending or receiving
     * @throws InterruptedException if the request is interrupted
     */
    public static HttpResponse<String> getUserFromUsername(String username)
            throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        // build an HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/user/" + username + "/"))
                .GET()
                .build();

        // send the request and get the response
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return response;
        } catch (Exception e) {
            GlobalLogger.error("Could not get players", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    /** Get all the leagues from a sleeper user in a given year from api
     * 
     * @param user_id the user_id from sleeper
     * @param season the year to get the leagues
     * @return The response from sleeper api
     * @throws IOException  if an I/O error occurs when sending or receiving
     * @throws InterruptedException if the request is interrupted
     */
    public static HttpResponse<String> getLeaguesFromUserIDAndSeason(long user_id, int season)
            throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        // build an HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/players/nfl"))
                .GET()
                .build();

        // send the request and get the response
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return response;
        } catch (Exception e) {
            GlobalLogger.error("Could not get players", e);
            throw new ExceptionInInitializerError(e);
        }
    }
}
