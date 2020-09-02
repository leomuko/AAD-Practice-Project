package com.example.gadsprojects.Main.UI.Hours;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsprojects.Models.HourModel;
import com.example.gadsprojects.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HourRecyclerAdapter extends RecyclerView.Adapter<HourRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final List<HourModel> mHourModels;

    public HourRecyclerAdapter(Context context, List<HourModel> learners){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mHourModels = learners;
    }

    @NonNull
    @Override
    public HourRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HourRecyclerAdapter.ViewHolder holder, int position) {
        HourModel myLearner = mHourModels.get(position);
        holder.name.setText(myLearner.getName());
        holder.content.setText(myLearner.getHours() +" learning hours, "+ myLearner.getCountry());
        Picasso.get().load(myLearner.getBadgeUrl()).into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return mHourModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView content;
        public ImageView userImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            content = itemView.findViewById(R.id.item_otherText);
            userImage = itemView.findViewById(R.id.item_image);
        }
    }
}
