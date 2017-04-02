package com.fkulic.guessthenumber;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Filip on 2.4.2017..
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    ArrayList<User> mUsers;

    public ScoreAdapter(ArrayList<User> users) {
        mUsers = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_score, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.tvOrdinalNumber.setText(String.valueOf(position+1));
        holder.tvUserName.setText(user.getUsername());
        holder.tvUserScore.setText(String.valueOf(user.getScore()));
    }

    @Override
    public int getItemCount() {
        return this.mUsers.size();
    }

    void loadData(ArrayList<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrdinalNumber;
        TextView tvUserName;
        TextView tvUserScore;

        ViewHolder(View view) {
            super(view);
            this.tvOrdinalNumber = (TextView) view.findViewById(R.id.tvOrdinalNumber);
            this.tvUserName = (TextView) view.findViewById(R.id.tvUserName);
            this.tvUserScore = (TextView) view.findViewById(R.id.tvUserScore);
        }


    }
}
