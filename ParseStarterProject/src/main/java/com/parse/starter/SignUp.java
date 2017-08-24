package com.parse.starter;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    RelativeLayout background;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("NanoGram");
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        logo = (ImageView) findViewById(R.id.logo);
        background = (RelativeLayout) findViewById(R.id.background2);

        logo.setOnClickListener(this);
        background.setOnClickListener(this);

    }

    public void createAccount(View view){

        ParseUser user = new ParseUser();
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());
        final Intent intent = new Intent(this, MainActivity.class);
        Log.i("SignUp", "Got here");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.i("SignUp", "Successful");
                    Toast.makeText(SignUp.this, "Successful SignUp!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else{
                    Log.i("SignUp", "Unsuccessful " + e.toString());
                    Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.logo || view.getId() == R.id.background2){
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
