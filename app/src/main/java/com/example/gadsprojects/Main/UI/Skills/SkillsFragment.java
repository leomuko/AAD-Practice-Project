package com.example.gadsprojects.Main.UI.Skills;

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

import com.example.gadsprojects.Models.SkillModel;
import com.example.gadsprojects.R;
import com.example.gadsprojects.Services.ApiClient;
import com.example.gadsprojects.Services.ApiInterface;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillsFragment extends Fragment {


    private RecyclerView mSkillRecycler;
    private SkillsAdapter mSkillsAdapter;
    private static final String TAG = "SkillsFragment";
    private SkillsFragmentViewModel mSkillsFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_skills, container, false);
        mSkillRecycler = rootView.findViewById(R.id.skillRecyclerView);

        initialiseViewModel();
        fetchData();
        return rootView;
    }

    private void initialiseViewModel() {
        mSkillsFragmentViewModel = new ViewModelProvider(getActivity()).get(SkillsFragmentViewModel.class);
    }

    private void fetchData() {
        mSkillsFragmentViewModel.fetchDataFromApi();
        mSkillsFragmentViewModel.skillsList.observe(Objects.requireNonNull(getActivity()), new Observer<List<SkillModel>>() {
            @Override
            public void onChanged(List<SkillModel> skillModelList) {
                setUpRecyclerView(skillModelList);
            }
        });
    }

    private void setUpRecyclerView(List<SkillModel> modelList) {
        mSkillRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mSkillsAdapter = new SkillsAdapter(getContext(), modelList);
        mSkillRecycler.setAdapter(mSkillsAdapter);
    }
}