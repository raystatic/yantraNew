package com.university.yantra.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;
import com.university.yantra.Activities.LoginActivity;
import com.university.yantra.R;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.AmbassdorsData;
import com.university.yantra.models.Campaigners;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishabh on 2/21/2018.
 */

public class AmbassdorsRecyclerViewAdapter extends RecyclerView.Adapter<AmbassdorsRecyclerViewAdapter.RecyclerViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    List<Campaigners> ambassdorsDatas;
    DisplayMetrics displayMetrics;
    public AmbassdorsRecyclerViewAdapter(Context context, List<Campaigners> ambassdorsDatas, DisplayMetrics displayMetrics){

        this.context = context;
        this.ambassdorsDatas = ambassdorsDatas;
        this.displayMetrics=displayMetrics;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public AmbassdorsRecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ambassdors_card_layout,parent,false);
        AmbassdorsRecyclerViewAdapter.RecyclerViewHolder recyclerViewHolder=new AmbassdorsRecyclerViewAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){

    }
    @Override
    public void onBindViewHolder(final AmbassdorsRecyclerViewAdapter.RecyclerViewHolder holder, int position) {

//        Double height = displayMetrics.heightPixels/(4.0);
//        Double width = displayMetrics.widthPixels/(2.0);
//        holder.cardView.getLayoutParams().height = height.intValue();
//        holder.cardView.getLayoutParams().width = width.intValue();

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
        holder.favorite.setFavorite(ambassdorsData.getUserLIked());
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
        if(position>=6){
            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.square_button_pink_back));
        }
        else{
            holder.linearLayout.setBackground(context.getResources().getDrawable(R.drawable.square_button_background));
        }


    }

    @Override
    public int getItemCount() {
        return ambassdorsDatas.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        View parent;
        TextView baca;
        TextView name;
        TextView location;
        TextView university;
        TextView ambassdorName;
        ImageView linkedIn;
        LinearLayout email;
        RelativeLayout call, relativeLayout;
        LinearLayout linearLayout;
        MaterialFavoriteButton favorite;
        CardView cardView;


        public RecyclerViewHolder(View view){
            super(view);

            cardView=view.findViewById(R.id.card_ambassador);
            favorite = view.findViewById(R.id.likeButton);
            linearLayout = view.findViewById(R.id.linearLayout);
            linkedIn = view.findViewById(R.id.linkedin);
            location = view.findViewById(R.id.txt3);
            university = view.findViewById(R.id.txt2);
            name = view.findViewById(R.id.ambassdorName);
            baca = view.findViewById(R.id.baca);
            email = view.findViewById(R.id.email);
            call = view.findViewById(R.id.cal_btn);
            relativeLayout=view.findViewById(R.id.ambassador_relative_layout);
            this.parent = view;

        }
    }
}
