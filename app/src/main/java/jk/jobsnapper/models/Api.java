package jk.jobsnapper.models;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {
    @POST("/api/login")
    Call<ResponseBody> login(@Body LoginRequest loginRequest);

    @POST("/employer/joboffers")
    Call<ResponseBody> createJobOffer(@Header("Authorization") String token, @Body JobOffer jobOffer);
}
