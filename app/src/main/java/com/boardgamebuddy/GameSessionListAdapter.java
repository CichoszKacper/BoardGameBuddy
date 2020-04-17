package com.boardgamebuddy;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class GameSessionListAdapter extends RecyclerView.Adapter<GameSessionListAdapter.GameSessionViewHolder> {

    class GameSessionViewHolder extends RecyclerView.ViewHolder {
        private final TextView sessionItemView;

        private GameSessionViewHolder(View itemView) {
            super(itemView);
            sessionItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<GameSession> mAllSessions; // Cached copy of sessions

    GameSessionListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public GameSessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false); //LAYOUT REFERENCE HERE
        return new GameSessionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameSessionViewHolder holder, int position) {
        if (mAllSessions != null) {
            GameSession current = mAllSessions.get(position);
            holder.sessionItemView.setText(current.getSession());
        } else {
            // Covers the case of data not being ready yet.
            holder.sessionItemView.setText("No game sessions available");
        }
    }

    void setSessions(List<GameSession> sessions){
        mAllSessions = sessions;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAllSessions != null)
            return mAllSessions.size();
        else return 0;
    }

}
