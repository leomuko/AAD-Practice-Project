package com.example.gadsprojects.Submit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gadsprojects.Main.MainActivity;
import com.example.gadsprojects.Models.PostModel;
import com.example.gadsprojects.R;

public class SubmitActivity extends AppCompatActivity {

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mGithub;
    private Button mSubmitButton;
    private SubmitActivityViewModel mSubmitActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar1));

        mFirstName = findViewById(R.id.firstNameInput);
        mLastName = findViewById(R.id.lastNameInput);
        mEmail = findViewById(R.id.emailInput);
        mGithub = findViewById(R.id.linkInput);
        mSubmitButton = findViewById(R.id.submitButton);

        initialiseViewModel();
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = mEmail.getText().toString();
                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String link = mGithub.getText().toString();
                if(firstName.isEmpty()){
                    Toast.makeText(SubmitActivity.this, "Please enter FirstName", Toast.LENGTH_SHORT).show();
                }else if(lastName.isEmpty()){
                    Toast.makeText(SubmitActivity.this, "Please enter LastName", Toast.LENGTH_SHORT).show();
                }else if(Email.isEmpty()){
                    Toast.makeText(SubmitActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                }
                else if(link.isEmpty()){
                    Toast.makeText(SubmitActivity.this, "Please enter github link", Toast.LENGTH_SHORT).show();
                }else {
                    View dialogView = getLayoutInflater().inflate(R.layout.submit_dialog, null);
                    Button confirmButton = dialogView.findViewById(R.id.confirmButton);
                    ImageView cancelButton = dialogView.findViewById(R.id.cancelButton);
                    AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
                    builder.setView(dialogView);

                    AlertDialog dialog = builder.show();
                    confirmButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            sendPostData(Email, firstName, lastName, link);
                            dialog.cancel();
                        }
                    });
                    cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initialiseViewModel() {
        mSubmitActivityViewModel = new ViewModelProvider(this).get(SubmitActivityViewModel.class);
    }

    private void sendPostData(String email, String firstName, String lastName, String link) {
        PostModel data = new PostModel();
        data.setEmail(email);
        data.setFirstName(firstName);
        data.setLastName(lastName);
        data.setLink(link);
        mSubmitActivityViewModel.sendData(data);
        mSubmitActivityViewModel.postSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                View dialogView = getLayoutInflater().inflate(R.layout.success_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
                builder.setView(dialogView);

                AlertDialog dialog = builder.show();

                 new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.cancel();
                    }
                }, 2000);

            }
        });
        mSubmitActivityViewModel.postError.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                View dialogView = getLayoutInflater().inflate(R.layout.error_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmitActivity.this);
                builder.setView(dialogView);

                AlertDialog dialog = builder.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.cancel();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
