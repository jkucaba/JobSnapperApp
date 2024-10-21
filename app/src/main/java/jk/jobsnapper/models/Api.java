package jk.jobsnapper.models;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @POST("/api/login")
    Call<ResponseBody> login(@Body LoginRequest loginRequest);

    @POST("/employer/joboffers")
    Call<ResponseBody> createJobOffer(@Header("Authorization") String token, @Body JobOffer jobOffer);

    @GET("/employer/joboffers")
    Call<ResponseBody> getJobOffers(@Header("Authorization") String token);

    @GET("/employer/joboffers/my/{id}")
    Call<ResponseBody> getJobOffersById(@Header("Authorization") String token, @Path("id") Long id);

    @GET("/admin/users")
    Call<ResponseBody> getUsers(@Header("Authorization") String token);
}
