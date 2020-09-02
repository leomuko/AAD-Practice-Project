package com.example.gadsprojects.Services;

import com.example.gadsprojects.Models.HourModel;
import com.example.gadsprojects.Models.SkillModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api/hours")
    Call<List<HourModel>> getHours();

    @GET("api/skilliq")
    Call<List<SkillModel>> getSkills();
}
