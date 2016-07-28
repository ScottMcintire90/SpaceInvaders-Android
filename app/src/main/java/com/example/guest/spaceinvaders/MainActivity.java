package com.example.guest.spaceinvaders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.leaderBoardButton) Button mLeaderBoardButton;
    @Bind(R.id.newGameButton) Button mNewGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAboutButton.setOnClickListener(this);
        mLeaderBoardButton.setOnClickListener(this);
        mNewGameButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mAboutButton){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        if(v == mLeaderBoardButton){
            Intent goToLeaderBoard = new Intent(MainActivity.this, LeaderboardActivity.class);
            startActivity(goToLeaderBoard);
        }
        if(v == mNewGameButton){
            Intent goToNewGame = new Intent(MainActivity.this,SpaceInvadersActivity.class);
            startActivity(goToNewGame);
        }
    }
}

