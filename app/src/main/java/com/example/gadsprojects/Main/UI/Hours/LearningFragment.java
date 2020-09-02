package com.example.gadsprojects.Main.UI.Hours;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gadsprojects.Models.HourModel;
import com.example.gadsprojects.R;
import com.example.gadsprojects.Services.ApiClient;
import com.example.gadsprojects.Services.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LearningFragment extends Fragment {


    private RecyclerView mRecycler;
    HourRecyclerAdapter mHourRecyclerAdapter;
    private static final String TAG = "LearningFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_learning, container, false);
        mRecycler = rootView.findViewById(R.id.hour_recycler);

        fetchData();
        return rootView;
    }

    private void fetchData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<HourModel>> call = apiService.getHours();
        call.enqueue(new Callback<List<HourModel>>() {
            @Override
            public void onResponse(Call<List<HourModel>> call, Response<List<HourModel>> response) {
                List<HourModel> hourModelList = response.body();
                setUpRecyclerView(hourModelList);
            }

            @Override
            public void onFailure(Call<List<HourModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
            }
        });
    }

    private void setUpRecyclerView(List<HourModel> hourModelList) {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mHourRecyclerAdapter = new HourRecyclerAdapter(getContext(), hourModelList);
        mRecycler.setAdapter(mHourRecyclerAdapter);
    }
}