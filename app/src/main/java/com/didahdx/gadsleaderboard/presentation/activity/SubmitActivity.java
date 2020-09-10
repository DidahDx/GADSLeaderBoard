package com.didahdx.gadsleaderboard.presentation.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

            // Create custom dialog object
            final Dialog dialog = new Dialog(this);
            // Include dialog.xml file
            dialog.setContentView(R.layout.dialog_box);
            dialog.setCancelable(true);
            ImageButton close=(ImageButton) dialog.findViewById(R.id.button_cancel);
            Button submit=(Button) dialog.findViewById(R.id.button_yes);
            ConstraintLayout first=dialog.findViewById(R.id.constrain_confirm);
            ConstraintLayout success=dialog.findViewById(R.id.constrain_success);
            ConstraintLayout fail=dialog.findViewById(R.id.constrain_fail);

            submit.setOnClickListener(view1 -> {
                IOExecutor.getInstance().execute(() -> {
                    GoogleForm.submit.submitProjects(
                            email.getText().toString(),
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            link.getText().toString()
                    ).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){
                                first.setVisibility(View.GONE);
                                success.setVisibility(View.VISIBLE);
                                fail.setVisibility(View.GONE);
                            }else{
                                first.setVisibility(View.GONE);
                                success.setVisibility(View.GONE);
                                fail.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            first.setVisibility(View.GONE);
                            success.setVisibility(View.GONE);
                            fail.setVisibility(View.VISIBLE);
                        }
                    });

                });
            });

            close.setOnClickListener(view12 -> {
                dialog.dismiss();
            });

            dialog.show();
        }
    }
}