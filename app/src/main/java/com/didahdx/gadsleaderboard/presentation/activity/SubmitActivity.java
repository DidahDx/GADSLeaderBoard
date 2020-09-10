package com.didahdx.gadsleaderboard.presentation.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.didahdx.gadsleaderboard.R;
import com.didahdx.gadsleaderboard.data.network.GoogleForm;
import com.didahdx.gadsleaderboard.util.IOExecutor;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity implements View.OnClickListener {
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText link;
    Button submit;
    ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        collectIds();

    }

    private void collectIds() {
        buttonBack = findViewById(R.id.buttonBack);
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        email = findViewById(R.id.editTextTextEmailAddress);
        link = findViewById(R.id.editTextLink);
        submit = findViewById(R.id.btnSubmitRe);
        submit.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == buttonBack) {
            finish();
        } else if (view == submit) {
            if (TextUtils.isEmpty(firstName.getText())) {
                firstName.setError("Can not be empty");
                return;
            }
            if (TextUtils.isEmpty(lastName.getText())) {
                lastName.setError("Can not be empty");
                return;
            }
            if (TextUtils.isEmpty(email.getText())) {
                email.setError("Can not be empty");
                return;
            }
            if (TextUtils.isEmpty(link.getText())) {
                link.setError("Can not be empty");
                return;
            }

            IOExecutor.getInstance().execute(() -> {
                GoogleForm.submit.submitProjects(
                        email.getText().toString(),
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        link.getText().toString()
                ).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            });
        }
    }
}