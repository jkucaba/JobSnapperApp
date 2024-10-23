package jk.jobsnapper.models;


import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @DELETE("admin/users/{id}")
    Call<ResponseBody> deleteUser(@Header("Authorization") String token, @Path("id") Long id);

    @Multipart
    @POST("/admin/users/{id}/profile-image")
    Call<ResponseBody> uploadProfileImage(@Path("id") Long userId, @Part MultipartBody.Part file, @Header("Authorization") String token);
    @GET("/admin/users/{id}/profile-image")
    Call<ResponseBody> getProfileImage(@Path("id") Long userId, @Header("Authorization") String token);
}
