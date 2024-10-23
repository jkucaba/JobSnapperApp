package jk.jobsnapper.models;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import okhttp3.MultipartBody;

public class Model {
    private static Model model;
    private ApiClient apiClient;
    private User user;

    private JobOffer currentJobOffer;
    private String token;

    ArrayList<JobOffer> jobOffers;
    ArrayList<User> users;

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
    public ArrayList<JobOffer> getJobOffersL() throws IOException, InterruptedException {
        jobOffers = apiClient.getJobOffers(token);
        return jobOffers;
    }
    public ArrayList<JobOffer> getYourJobOffersL() throws IOException, InterruptedException {
        jobOffers = apiClient.getYourJobOffers(token, user.getIduser());
        return jobOffers;
    }
    public ArrayList<User> getUsersL() throws IOException, InterruptedException {
        users = apiClient.getUsers(token);
        return users;
    }
    public void deleteUserM(Long id) throws IllegalStateException,IOException {
        apiClient.deleteUser(token, id);
    }
    public void uploadProfileImage(Long userId, MultipartBody.Part file) throws IOException {
        apiClient.uploadProfileImage(userId, file, token);
    }
    public byte[] fetchProfileImage(Long userId) {
        try {
            return apiClient.getProfileImage(userId, token);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
