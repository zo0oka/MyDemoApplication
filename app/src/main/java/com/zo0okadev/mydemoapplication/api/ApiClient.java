package com.zo0okadev.mydemoapplication.api;

import com.zo0okadev.mydemoapplication.model.Category;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://souq.hardtask.co/app/app.asmx/";
    private static MyApi sInstance = null;

    public static MyApi getInstance() {

        if (sInstance == null) {

            // For logging
            HttpLoggingInterceptor loggingInterceptor =
                    new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

            // Building OkHttp client
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            // Retrofit Builder
            Retrofit.Builder builder =
                    new Retrofit
                            .Builder()
                            .baseUrl(BASE_URL)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create());

            sInstance = builder.build().create(MyApi.class);

        }

        return sInstance;
    }

    public static void getCategories(MyApi service, int categoryId, int countryId, final StatusCallback statusCallback) {
        service.getCategories(categoryId, countryId).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    List<Category> categories;
                    if (response.body() != null) {
                        categories = response.body();
                    } else {
                        categories = Collections.emptyList();
                    }
                    statusCallback.onSuccess(categories);
                } else {
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : null;
                        statusCallback.onError(errorBody != null ? errorBody : "Unknown error!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                statusCallback.onError(t.getMessage() != null ? t.getMessage() : "Unknown error!");
            }
        });
    }

    public interface StatusCallback {

        void onSuccess(List<Category> categories);

        void onError(String error);
    }
}