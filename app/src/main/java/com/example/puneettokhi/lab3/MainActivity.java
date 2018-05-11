package com.example.puneettokhi.lab3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


/**
 * Created by puneettokhi on 5/4/18.
 */

// creating MainActivity class
public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{


    // required variables
    private SignInButton SignIn;
    private Button SignOut;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignIn = (SignInButton)findViewById(R.id.sign_in_button);   // get signinbutton's id


        SignIn.setOnClickListener(this);  // setting onClickListener to sign in button

        // initialzing googleSignInOption
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();


        // initializing googleApiClient
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

    }

    // when user clicks the button
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;

        }

    }

    // required method for interface
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }


    // signs in the user
    private void signIn(){

        // directs user to Sign In screen
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }

    // method dealing with successful sign in or failure
    private void handleResult(GoogleSignInResult result){

        // if sign in is successful
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();

            // if successfully signed, take user to next screen
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            startActivity(intent);  // redirect user to next screen
        }

        else{
            updateUI(false);
        }

    }

    // update user's UI
    private void updateUI(boolean isLogin){

        if(isLogin){

            SignIn.setVisibility(View.GONE);   // if logged in, no view
        }
        else{

            SignIn.setVisibility(View.VISIBLE);  // if logged out, show view
        }
    }

// this method checks if request code is equal to REQ_CODE and calls handleResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE ){

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);  // calling handleResult method
        }
    }
}
