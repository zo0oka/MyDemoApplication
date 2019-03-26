package com.zo0okadev.mydemoapplication.api;

import com.zo0okadev.mydemoapplication.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {

    // Call to return the list of categories
    @GET("GetCategories")
    Call<List<Category>> getCategories(@Query("categoryId") int categoryId,
                                       @Query("countryId") int countryId);
}
