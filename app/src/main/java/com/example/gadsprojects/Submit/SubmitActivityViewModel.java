package com.example.gadsprojects.Submit;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gadsprojects.Models.PostModel;
import com.example.gadsprojects.Services.PostApiClient;
import com.example.gadsprojects.Services.PostApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivityViewModel extends AndroidViewModel {
    MutableLiveData<Boolean> postSuccess = new MutableLiveData<>();
    MutableLiveData<Boolean> postError = new MutableLiveData<>();
    private static final String TAG = "SubmitActivityViewModel";


    public SubmitActivityViewModel(@NonNull Application application) {
        super(application);
    }


    public void sendData(PostModel data) {
        PostApiInterface apiInterface = PostApiClient.getClient().create(PostApiInterface.class);
//        Call<PostModel> call = apiInterface.submitData(data.getEmail(), data.getFirstName(),
//                data.getLastName(), data.getLink());
//        call.enqueue(new Callback<PostModel>() {
//            @Override
//            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
//                if(!response.isSuccessful()){
//                   // postError.postValue(false);
//                    Log.d(TAG, "onResponse: "+ response.code());
//                    Log.d(TAG, "onResponse: "+ response.errorBody().toString());
//                    return;
//                }
//                PostModel postResponse = response.body();
//                Log.d(TAG, "onResponse: "+postResponse.getEmail());
//                if(postResponse.equals(data)){
//                    postSuccess.postValue(true);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PostModel> call, Throwable t) {
//                Log.d(TAG, "onFailure: "+ t.getMessage());
//                postError.postValue(false);
//            }
//        });
        Call<Void> call = apiInterface.submitData(data.getEmail(), data.getFirstName()
        ,data.getLastName(), data.getLink());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 400){
                    postError.postValue(false);
                    Log.d(TAG, "onResponse: "+ response.code());
                    Log.d(TAG, "onResponse: "+ response.errorBody().toString());
                    if(response.code() == 400){
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().toString());
                            String userMessage = jsonObject.getString("userMessage");
                            String internalMessage = jsonObject.getString("internalMessage");
                            String errorCode = jsonObject.getString("errorCode");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }else {
                    postSuccess.postValue(true);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
