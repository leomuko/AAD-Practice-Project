package com.example.gadsprojects.Main.UI.Hours;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LearningFragment extends Fragment {


    private RecyclerView mRecycler;
    HourRecyclerAdapter mHourRecyclerAdapter;
    private static final String TAG = "LearningFragment";
    private LearningFragmentViewModel mLearningFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_learning, container, false);
        mRecycler = rootView.findViewById(R.id.hour_recycler);

        initialiseViewModel();
        fetchData();
        return rootView;
    }

    private void initialiseViewModel() {
        mLearningFragmentViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity()))
                .get(LearningFragmentViewModel.class);
    }

    private void fetchData() {
        mLearningFragmentViewModel.fetchDataFromApi();
        mLearningFragmentViewModel.hoursList
                .observe(Objects.requireNonNull(getActivity()), new Observer<List<HourModel>>() {
            @Override
            public void onChanged(List<HourModel> hourModels) {
                setUpRecyclerView(hourModels);
            }
        });
    }

    private void setUpRecyclerView(List<HourModel> hourModelList) {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mHourRecyclerAdapter = new HourRecyclerAdapter(getContext(), hourModelList);
        mRecycler.setAdapter(mHourRecyclerAdapter);
    }
}