package com.example.store.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class IamPortUtil {

    private final Retrofit retrofit;
    private final IamportClient iamportClient;

    @Value("${iamport.api.key}")
    public static final String apiKey = "8186311043120653";

    @Value("${iamport.api.secret}")
    public static final String apiSecret = "NSVJ3wfmIToVeiaMnpd8HUqW4UqhRJYk7DWfWvZlQto0dd5OFsuYuo6tIkgensAH6OoQBERrnHBc9dUc";

//    public void setApiKey(String apiKey) {
//        this.apiKey = apiKey;
//    }

    public IamPortUtil() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.iamport.kr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.iamportClient = retrofit.create(IamportClient.class);
    }

    public String getToken() throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("imp_key", apiKey);
        json.addProperty("imp_secret", apiSecret);

        Call<JsonElement> call = iamportClient.getToken(json);
        Response<JsonElement> response = call.execute();

        if (response.isSuccessful()) {
            JsonElement body = response.body();
            return body.getAsJsonObject().get("response").getAsJsonObject().get("access_token").getAsString();
        } else {
            throw new IOException("Failed to get Iamport token");
        }
    }

    private interface IamportClient {
        @retrofit2.http.POST("users/getToken")
        Call<JsonElement> getToken(@retrofit2.http.Body JsonObject body);
    }
}
