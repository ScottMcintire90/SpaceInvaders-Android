package com.example.guest.spaceinvaders;

import java.util.ArrayList;
        import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import org.parceler.Parcels;

        import java.util.ArrayList;

        import butterknife.Bind;
        import butterknife.ButterKnife;


public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.ScoreViewHolder>{
    private ArrayList<Score> mScores = new ArrayList<>();
    private Context mContext;
    @Bind(R.id.score) TextView mScore;
    @Bind(R.id.user) TextView mUser;


    public ScoreListAdapter(Context context, ArrayList<Score> scores){
        mContext = context;
        mScores = scores;
    }
    @Override
    public ScoreViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_list, parent, false);

        ScoreViewHolder viewHolder = new ScoreViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position){
        holder.bindScore(mScores.get(position));
    }
    @Override
    public int getItemCount(){
        return mScores.size();
    }
    public class ScoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.score) TextView mScore;
        @Bind(R.id.user) TextView mUser;


        private Context mContext;

        public ScoreViewHolder(View scoreView){
            super(scoreView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            scoreView.setOnClickListener(this);
        }

        public void bindScore(Score score){
            mScore.setText(score.getScore());
            mUser.setText(score.getUser());
        }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, LeaderboardActivity.class);
            intent.putExtra("position", String.valueOf(itemPosition));
            intent.putExtra("score", Parcels.wrap(mScores));
            mContext.startActivity(intent);
        }

    }
}