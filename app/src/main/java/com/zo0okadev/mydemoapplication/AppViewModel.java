package com.zo0okadev.mydemoapplication;

import com.zo0okadev.mydemoapplication.api.ApiClient;
import com.zo0okadev.mydemoapplication.api.MyApi;
import com.zo0okadev.mydemoapplication.model.Category;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppViewModel extends ViewModel {

    private final MutableLiveData<List<Category>> categoriesLiveData;
    private final MutableLiveData<String> categoriesErrorLiveData;
    private MyApi api;

    public AppViewModel() {
        api = ApiClient.getInstance();
        categoriesLiveData = new MutableLiveData<>();
        categoriesErrorLiveData = new MutableLiveData<>();
    }

    public void getCategories(int categoryId, int countryId) {
        ApiClient.getCategories(api, categoryId, countryId, new ApiClient.StatusCallback() {
            @Override
            public void onSuccess(List<Category> categories) {
                categoriesLiveData.postValue(categories);
            }

            @Override
            public void onError(String error) {
                categoriesErrorLiveData.postValue(error);
            }
        });
    }

    public LiveData<List<Category>> categories() {
        return categoriesLiveData;
    }

    public LiveData<String> error() {
        return categoriesErrorLiveData;
    }

}
