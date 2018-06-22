package com.university.yantra.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;
import com.university.yantra.R;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.ShortTermCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Rishabh on 2/20/2018.
 */

public class ShortTermAdapter extends RecyclerView.Adapter<ShortTermAdapter.RecyclerViewHolder> {
    private PrefManager prefManager;
    private Context context;
    private LayoutInflater inflater;
    List<ShortTermCourse> urls = new ArrayList<>();
    public ShortTermAdapter(PrefManager prefManager, Context context, List<ShortTermCourse> urls){

        this.prefManager = prefManager;
        this.context = context;
        this.urls = urls;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ShortTermAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.short_term_card_full,parent,false);
        ShortTermAdapter.RecyclerViewHolder recyclerViewHolder=new ShortTermAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){}

    @Override
    public void onBindViewHolder(final ShortTermAdapter.RecyclerViewHolder holder, int position) {
        final ShortTermCourse shortTermCoursesData = urls.get(position);

        Picasso.with(context)
                .load(shortTermCoursesData.getImage())
                .fit()
                .into(holder.courseImage);
        holder.favorite.setFavorite(shortTermCoursesData.getUserLiked());
        Random r = new Random();
        //holder.views.setText((r.nextInt(1000-400) + 400)+" views");
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MyApplication.getInstance().prefManager.isLoggedIn()) {
                    if (holder.favorite.isFavorite())
                        holder.favorite.setFavorite(false);
                    else
                        holder.favorite.setFavorite(true);

                    OnLiked(shortTermCoursesData.getId());
                }
                else{
                    Toast.makeText(context,"Login To Add To Wishlist",Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.rating.setText(shortTermCoursesData.getRating()+"");
        holder.type.setText(shortTermCoursesData.getBoostPercent()+"% Boost");
        holder.duration.setText(shortTermCoursesData.getDuration()+" Months");
        holder.offer.setText(shortTermCoursesData.getOfferredBy());
        holder.courseName.setText(shortTermCoursesData.getName());
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{


        //        TextView discount;
//        TextView discountText;
        View parent;
//        TextView streaks;
        // ImageView soldout;

        TextView duration;
        ImageView courseImage;
        TextView type;
        TextView rating;
        TextView courseName;
        TextView views;
        MaterialFavoriteButton favorite;
        TextView offer;

        public RecyclerViewHolder(View view){
            super(view);

            views = view.findViewById(R.id.views);
            favorite = view.findViewById(R.id.likeButton);
            rating = view.findViewById(R.id.ratingtext);
            type = view.findViewById(R.id.type);
            duration = view.findViewById(R.id.tv_KM_tab4);
            courseName = view.findViewById(R.id.textView);
            courseImage = view.findViewById(R.id.imageView);
            offer = view.findViewById(R.id.vendor);
            this.parent = view;

        }
    }
}
