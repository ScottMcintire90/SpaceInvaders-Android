package com.example.guest.spaceinvaders;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;


import butterknife.Bind;
import butterknife.ButterKnife;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private String score;
    private String mUserName;
    @Bind(R.id.scoreSubmit) Button mScoreSubmit;
    @Bind(R.id.userName) EditText userName;
    private Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Firebase.setAndroidContext(this);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        score = intent.getStringExtra("score");
        ref = new Firebase("https://pokemon-spaceinvaders.firebaseio.com/");
        Toast toast = Toast.makeText(this, "Your score was " + score, Toast.LENGTH_SHORT);
        toast.show();

        mScoreSubmit.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v == mScoreSubmit) {
            mUserName = userName.getText().toString();
            Firebase scoreRef = ref.child("scores");
            Score highScore = new Score(score, mUserName);
            scoreRef.push().setValue(highScore);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
         }
    }
}
