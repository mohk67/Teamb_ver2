package com.tutlane.todo_myself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText tietUsername, tietPassword;
    MaterialButton mbtnLogin;
    TextView tvLoginErrorMessage;
    String username, password;
    String BASE_URL = "http://royalkeep-v2.eu-4.evennode.com/api/main/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tietUsername = findViewById(R.id.tied_username);
        tietPassword = findViewById(R.id.tiet_password);
        mbtnLogin = findViewById(R.id.mbtn_login);
        tvLoginErrorMessage = findViewById(R.id.tv_login_error_message);

        //setOnClickListener
        mbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = tietUsername.getText().toString();
                password = tietPassword.getText().toString();
                login();
            }
        });

    }

    private void login() {
        //check Username and Password
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MyApi myApi = retrofit.create(MyApi.class);
        JsonObject paramObject = new JsonObject();
        paramObject.addProperty("Username", username);
        paramObject.addProperty("Password", password);

        myApi.getUser(paramObject).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                //loginstatus
                if(user == null){
                    //login failed
                    tvLoginErrorMessage.setVisibility(View.VISIBLE);
                    showToast(0, null);
                }else{
                    //login successful
                    showToast(1, user);
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void showToast(int loginStatus, User user) {
        if (loginStatus == 1) {
            Toast.makeText(this, "Welcome " + user.FirstName + " " + user.getLastName(), Toast.LENGTH_SHORT).show();
        } else if(loginStatus == 0){
            Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
        }
    }
}
