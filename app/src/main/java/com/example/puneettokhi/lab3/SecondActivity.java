package com.example.puneettokhi.lab3;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.ArrayList;


/**
 * Created by puneettokhi on 5/8/18.
 */

// creating second activity which extends AppCompatActivity and implements Google's interface
public class SecondActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener  {


    // required variables
    private Button Logout;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient googleApiClient;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initializeRecyclerView();  // initializes the recycler view
        showRecyclerView();   // shows the recycler view to the user

        // initializing GoogleSignInOption
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

// googleApiClient initialized to get SignOut Working correctly
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(SecondActivity.this,this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

        Logout = (Button) findViewById(R.id.logout);  // logout button

        // listener for logout button
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();   // calling signOut method

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);

                startActivity(intent);  // redirect user to next screen

            }
        });

    }

    // when user presses back button, a toast message is displayed
    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Toast.makeText(SecondActivity.this, "To Sign Out please use Logout button",
                Toast.LENGTH_LONG).show();
    }

    // signs out from Gmail and goes back to main screen
    private void signOut() {

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
    }

    // required method for interface
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // this method initializes the recycler view
    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);  // finds recycler view by id
        recyclerView.setHasFixedSize(true);

        // create a new layout using LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    // this method shows the recycler view to the user
    private void showRecyclerView() {

        // Create an array list of Videos by calling the populate video method
        final ArrayList<Videos> videosList = populateVideos();

        RecyclerAdapter adapter = new RecyclerAdapter(this, videosList);
        recyclerView.setAdapter(adapter);   // sets adapter based on videoList

        //set click event when recycler view item is clicked
        recyclerView.addOnItemTouchListener(new RecyclerViewOnClickListener(this, new RecyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //start youtube player activity by passing selected video id using intent
        startActivity(new Intent(SecondActivity.this, YoutubeActivity.class)
                .putExtra("video_id", videosList.get(position).getVideoId()));  // using video id from Strings.Xml file

            }
        }));
    }

    // this method uses Videos class and Strings.xml file to get the videos, store in array and display to the user
    private ArrayList<Videos> populateVideos() {
        ArrayList<Videos> videoList = new ArrayList<>();

        //get the video id array, title array and duration array from strings.xml

        String[] videoID_Array = getResources().getStringArray(R.array.video_id_array);  // video id array from Strings.xml file
        String[] videoTitle_Array = getResources().getStringArray(R.array.video_title_array);  // video title array from Strings.xml file
        String[] videoTime_Array = getResources().getStringArray(R.array.video_time_array);  // video time array from Strings.xml file

        //loop through all the items and add them to the arraylist
        for (int i = 0; i < videoID_Array.length; i++) {

            Videos myVideos = new Videos();  // creating instance of Videos class

            myVideos.setVideoId(videoID_Array[i]);
            myVideos.setTitle(videoTitle_Array[i]);
            myVideos.setVideoDuration(videoTime_Array[i]);

            videoList.add(myVideos);   // adds myVideos to the array

        }

        return videoList;   // returns the videoList
    }


}





