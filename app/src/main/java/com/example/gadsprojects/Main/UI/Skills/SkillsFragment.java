package com.example.gadsprojects.Main.UI.Skills;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gadsprojects.Models.SkillModel;
import com.example.gadsprojects.R;
import com.example.gadsprojects.Services.ApiClient;
import com.example.gadsprojects.Services.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillsFragment extends Fragment {


    private RecyclerView mSkillRecycler;
    private SkillsAdapter mSkillsAdapter;
    private static final String TAG = "SkillsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_skills, container, false);
        mSkillRecycler = rootView.findViewById(R.id.skillRecyclerView);

        fetchData();
        return rootView;
    }

    private void fetchData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SkillModel>> call = apiService.getSkills();
        call.enqueue(new Callback<List<SkillModel>>() {
            @Override
            public void onResponse(Call<List<SkillModel>> call, Response<List<SkillModel>> response) {
                List<SkillModel> modelList = response.body();
                setUpRecyclerView(modelList);
            }

            @Override
            public void onFailure(Call<List<SkillModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
            }
        });
    }

    private void setUpRecyclerView(List<SkillModel> modelList) {
        mSkillRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mSkillsAdapter = new SkillsAdapter(getContext(), modelList);
        mSkillRecycler.setAdapter(mSkillsAdapter);
    }
}