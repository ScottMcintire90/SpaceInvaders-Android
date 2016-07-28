package com.example.guest.spaceinvaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LeaderboardActivity extends AppCompatActivity {
    private ScoreListAdapter mAdapter;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private ArrayList<Score> scores = new ArrayList<>();

    private Firebase mFirebaseRef;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Firebase.setAndroidContext(this);

        ButterKnife.bind(this);
        getScores();
    }


    public void getScores() {
        mRef = FirebaseDatabase.getInstance().getReference("scores");
        Query q = mRef.orderByKey();

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Score score = ds.getValue(Score.class);
                    score.setScore(ds.getValue(Score.class).getScore());
                    score.setUser(ds.getValue(Score.class).getUser());
                    scores.add(score);

                        for (int i = 0; i < scores.size(); i++) {
                            int highScore1 = Integer.parseInt(scores.get(i).getScore());

                            for (int k = i + 1; k < scores.size(); k++) {
                                int highScore2 = Integer.parseInt(scores.get(k).getScore());
                                if (highScore1 < highScore2) {
                                    Score lowerScore = scores.get(i);
                                    scores.remove(i);
                                    scores.add(lowerScore);

                                }
                            }
                        }
                    }
                renderScore(scores);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void renderScore(ArrayList array) {
        LeaderboardActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter = new ScoreListAdapter(getApplicationContext(), scores);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LeaderboardActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);
            }
        });
    }
}


