package com.example.gadsprojects.Main.UI.Skills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsprojects.Models.SkillModel;
import com.example.gadsprojects.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.Viewholder> {
    private Context mContext;
    private List<SkillModel> mSkillModels;
    private LayoutInflater mLayoutInflater;

    public SkillsAdapter(Context context, List<SkillModel> skillModelList){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mSkillModels = skillModelList;
    }


    @NonNull
    @Override
    public SkillsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.recycler_item, parent, false);
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsAdapter.Viewholder holder, int position) {
        SkillModel model = mSkillModels.get(position);
        holder.name.setText(model.getName());
        holder.content.setText(model.getScore() +" skill IQ score, "+ model.getCountry());
        Picasso.get().load(model.getBadgeUrl()).into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return mSkillModels.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView content;
        public ImageView userImage;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            content = itemView.findViewById(R.id.item_otherText);
            userImage = itemView.findViewById(R.id.item_image);
        }
    }
}
