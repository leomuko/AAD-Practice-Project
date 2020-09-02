package com.example.gadsprojects.Main.UI.Skills;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.gadsprojects.Models.SkillModel;
import com.example.gadsprojects.Services.ApiClient;
import com.example.gadsprojects.Services.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillsFragmentViewModel extends AndroidViewModel {

    MutableLiveData<List<SkillModel>> skillsList = new MutableLiveData<>();
    private static final String TAG = "SkillsFragmentViewModel";

    public SkillsFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchDataFromApi(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SkillModel>> call = apiService.getSkills();
        call.enqueue(new Callback<List<SkillModel>>() {
            @Override
            public void onResponse(Call<List<SkillModel>> call, Response<List<SkillModel>> response) {
                List<SkillModel> modelList = response.body();
                skillsList.setValue(modelList);
            }

            @Override
            public void onFailure(Call<List<SkillModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
            }
        });
    }
}
