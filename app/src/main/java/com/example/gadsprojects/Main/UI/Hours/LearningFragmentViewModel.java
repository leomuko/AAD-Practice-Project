package com.example.gadsprojects.Main.UI.Hours;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.gadsprojects.Models.HourModel;
import com.example.gadsprojects.Services.ApiClient;
import com.example.gadsprojects.Services.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningFragmentViewModel extends AndroidViewModel {
    MutableLiveData<List<HourModel>> hoursList = new MutableLiveData<>();
    private static final String TAG = "LearningFragmentViewModel";

    public LearningFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchDataFromApi(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<HourModel>> call = apiService.getHours();
        call.enqueue(new Callback<List<HourModel>>() {
            @Override
            public void onResponse(Call<List<HourModel>> call, Response<List<HourModel>> response) {
                List<HourModel> hourModelList = response.body();
                hoursList.setValue(hourModelList);
            }

            @Override
            public void onFailure(Call<List<HourModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
            }
        });
    }
}
