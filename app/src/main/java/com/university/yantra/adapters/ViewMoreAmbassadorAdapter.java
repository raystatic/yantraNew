package com.university.yantra.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.university.yantra.R;
import com.university.yantra.common.MyApplication;
import com.university.yantra.models.AmbassdorsData;
import com.university.yantra.models.Campaigners;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 6/3/18.
 */

public class ViewMoreAmbassadorAdapter extends RecyclerView.Adapter<ViewMoreAmbassadorAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Campaigners> ambassdorsDatas;

    public ViewMoreAmbassadorAdapter(Context context, List<Campaigners> ambassdorsDatas) {
        this.context = context;
        this.ambassdorsDatas = ambassdorsDatas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){}

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.viewmore_ambass_adapter_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Campaigners ambassdorsData = ambassdorsDatas.get(position);

        holder.name.setText(ambassdorsData.getFirst_name()+" "+ambassdorsData.getLast_name());
//        Log.d("ambass",ambassdorsData.getFirstName());
        holder.baca.setText(ambassdorsData.getCampaignerType());
        holder.location.setText(ambassdorsData.getUniversity().getLocation().getStreetAddress()+","+ambassdorsData.getUniversity().getLocation().getCity());
        holder.university.setText(ambassdorsData.getUniversity().getName());
        holder.linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ambassdorsData.getLinkedInProfile()));
                context.startActivity(browserIntent);
            }
        });
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(ambassdorsData.getEmail()));
            }
        });
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(ambassdorsData.getContact()));
                context.startActivity(intent);
            }
        });
        if(position>=6){
            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.square_button_pink_back));
        }
        else{
            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.square_button_background));
        }

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyApplication.getInstance().prefManager.isLoggedIn()) {
                    if (holder.favorite.isFavorite())
                        holder.favorite.setFavorite(false);
                    else
                        holder.favorite.setFavorite(true);

                    OnLiked(ambassdorsData.getId());
                }
                else{
                    Toast.makeText(context,"Login To Add To Wishlist",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ambassdorsDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        View parent;
        TextView baca;
        TextView name;
        TextView location;
        TextView university;
        TextView ambassdorName;
        MaterialFavoriteButton favorite;
        ImageView linkedIn;
        LinearLayout email;
        RelativeLayout call;
        LinearLayout linearLayout;


        public MyViewHolder(View view) {
            super(view);

            linearLayout = view.findViewById(R.id.linearLayout);
            linkedIn = view.findViewById(R.id.linkedin);
            location = view.findViewById(R.id.txt3);
            university = view.findViewById(R.id.txt2);
            favorite = view.findViewById(R.id.likeButton);
            name = view.findViewById(R.id.ambassdorName);
            baca = view.findViewById(R.id.baca);
            email = view.findViewById(R.id.email);
            call = view.findViewById(R.id.cal_btn);
            this.parent = view;

        }
    }

}
