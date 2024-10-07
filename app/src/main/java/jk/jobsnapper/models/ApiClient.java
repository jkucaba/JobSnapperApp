package jk.jobsnapper.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import android.util.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private String token;
    private String publicKey;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
        Request newRequest  = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .build();
        return chain.proceed(newRequest);
    }).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    Api api = retrofit.create(Api.class);

    public void setToken(String token) {
        this.token = token;
    }

    public <type> ArrayList<type> getArrayData(Type listType, String jsonString) throws IOException, InterruptedException {

        Gson gson = new Gson();

        ArrayList<type> list = gson.fromJson(jsonString, listType);

        return list;
    }

    public String login(LoginRequest loginRequest) throws IOException, InterruptedException {
        Call<ResponseBody> call = api.login(loginRequest);

        Response<ResponseBody> response = call.execute();

        if (response.isSuccessful()) {
            assert response.body() != null;
            String jwt = response.body().string();
            System.out.println(response.body().string());
            System.out.println("Zalogowano pomyślnie. JWT: " + jwt);
            return jwt;
        } else {
            throw new IllegalStateException("Błąd podczas logowania. Kod odpowiedzi HTTP: " + response.code());
        }
    }
    public String getPublicKey() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "publicKey")
                .build();

        okhttp3.Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            publicKey = response.body().string();
            return publicKey;
        } else {
            throw new IllegalStateException("Błąd podczas pobierania klucza publicznego. Kod odpowiedzi HTTP: " + response.code());
        }
    }
    public String getUserRoleFromToken(String token) throws Exception {
        byte[] publicBytes = Base64.decode(publicKey, Base64.DEFAULT);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        Claims claims = Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("role", String.class);
    }
}
