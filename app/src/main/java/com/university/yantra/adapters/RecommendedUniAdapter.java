package com.university.yantra.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.university.yantra.R;
import com.university.yantra.common.MyApplication;
import com.university.yantra.models.University;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Rishabh on 2/18/2018.
 */

public class RecommendedUniAdapter extends RecyclerView.Adapter<RecommendedUniAdapter.RecyclerViewHolder> {

    private Context context;
    Random r;
    private LayoutInflater inflater;
    List<University> universityDatas = new ArrayList<>();
    DisplayMetrics displayMetrics;
    public RecommendedUniAdapter( Context context, List<University> universityDatas, DisplayMetrics displayMetrics){


        this.context = context;
        this.universityDatas = universityDatas;
        this.displayMetrics=displayMetrics;
        r = new Random();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){

    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.universities_card_full,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        final University universityData = universityDatas.get(position);

//        Double height = displayMetrics.heightPixels/(3.0);
//        holder.linearLayout.getLayoutParams().height = height.intValue();
//        holder.linearLayout.getLayoutParams().width = displayMetrics.widthPixels;

        Picasso.with(context)
                .load(universityData.getFeatureImage())
                .fit()
                .into(holder.collegeImage);
        Picasso.with(context)
                .load(universityData.getLogo())
                .fit()
                .into(holder.icon);

        holder.favorite.setFavorite(universityData.getUserLiked());
        holder.collegeName.setText(universityData.getName());
        Log.d("imageurl",universityData.getFeatureImage()+"");
        holder.location.setText(universityData.getLocation().getStreetAddress()+","+universityData.getLocation().getCity());
        holder.rating.setText(universityData.getRating()+"");
        holder.views.setText((r.nextInt(1000-400) + 400)+" views");
        if(universityData.getOwnedType().equals("PUB"))
            holder.type.setText("Public");
        else
            holder.type.setText("Private");
        if(universityData.getAdmissionProcedure().equals("M"))
            holder.merit.setText("Merit");
        else
            holder.merit.setText("Entrance");
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+universityData.getContact()));
                context.startActivity(intent);
            }
        });
        holder.enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(universityData.getWebsiteUrl()));
                context.startActivity(browserIntent);
            }
        });
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.getInstance().prefManager.isLoggedIn()) {
                    if (holder.favorite.isFavorite())
                        holder.favorite.setFavorite(false);
                    else
                        holder.favorite.setFavorite(true);

                    OnLiked(universityData.getId());
                }
                else
                    Toast.makeText(context,"Login To Add To Wishlist",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return universityDatas.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{


        View parent;
        TextView collegeName;
        TextView location;
        ImageView icon;
        TextView merit;
        TextView type;
        TextView rating;
        ImageView collegeImage;
        TextView views;
        MaterialFavoriteButton favorite;
        RelativeLayout call;
        LinearLayout enroll, linearLayout;

        public RecyclerViewHolder(View view){
            super(view);


            favorite = view.findViewById(R.id.likeButton);
            call = view.findViewById(R.id.cal_btn);
            icon = view.findViewById(R.id.imageView2);
            merit = view.findViewById(R.id.tv_KM_tab4);
            type = view.findViewById(R.id.type);
            views = view.findViewById(R.id.views);
            rating = view.findViewById(R.id.ratingtext);
            location = view.findViewById(R.id.location);
            collegeName = view.findViewById(R.id.textView);
            collegeImage = view.findViewById(R.id.imageView);
            enroll = view.findViewById(R.id.enrollLayout);
            linearLayout=view.findViewById(R.id.uni_linear_layout);
            this.parent = view;

        }
    }


}
