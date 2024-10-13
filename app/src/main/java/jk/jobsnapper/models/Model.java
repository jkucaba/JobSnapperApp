package jk.jobsnapper.models;

import android.content.Context;

import java.io.IOException;
import java.sql.SQLException;

public class Model {
    private static Model model;
    private ApiClient apiClient;
    private User user;

    private JobOffer currentJobOffer;
    private String token;

    private Model(Context context) throws SQLException {
        this.apiClient = new ApiClient();
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public static synchronized Model getInstance(Context context) throws SQLException {
        if (model == null) {
            model = new Model(context);
        }
        return model;
    }

    public static synchronized Model getInstanceWC() throws SQLException {
        return model;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public void createJobOffer(JobOffer jobOffer) throws IOException {
        apiClient.createJobOffer(jobOffer, token);
    }

    public JobOffer getCurrentJobOffer() {
        return currentJobOffer;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setCurrentJobOffer(JobOffer currentJobOffer) {
        this.currentJobOffer = currentJobOffer;
    }

}
