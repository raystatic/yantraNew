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
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;
import com.university.yantra.Fragments.UniversitiesFragment;
import com.university.yantra.R;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Rishabh on 2/20/2018.
 */

public class RecommendedCourseAdapter extends RecyclerView.Adapter<RecommendedCourseAdapter.RecyclerViewHolder>{
    private PrefManager prefManager;
    private Context context;
    private LayoutInflater inflater;
    Random r;
    private UniversitiesFragment obj;
    List<Course> shortTermCoursesDatas = new ArrayList<>();
    public RecommendedCourseAdapter(PrefManager prefManager, Context context, List<Course> shortTermCoursesDatas){

        this.prefManager = prefManager;
        this.context = context;
        r = new Random();
        this.shortTermCoursesDatas = shortTermCoursesDatas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){

    }

    @Override
    public RecommendedCourseAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.courses_card_full,parent,false);
        RecommendedCourseAdapter.RecyclerViewHolder recyclerViewHolder=new RecommendedCourseAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecommendedCourseAdapter.RecyclerViewHolder holder, int position) {
        final Course shortTermCoursesData = shortTermCoursesDatas.get(position);

        Picasso.with(context)
                .load(shortTermCoursesData.getImage())
                .fit()
                .into(holder.courseImage);
        holder.rating.setText(shortTermCoursesData.getRating()+"");
        if(shortTermCoursesData.getCourseType().equals("PRE"))
            holder.type.setText("Premium");
        else
            holder.type.setText("Professional");

        holder.favorite.setFavorite(shortTermCoursesData.getUserLiked());
        holder.duration.setText(shortTermCoursesData.getDuration()+" Years");
        holder.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(shortTermCoursesData.getWikipediaUrl()));
                context.startActivity(browserIntent);
            }
        });
        holder.views.setText((r.nextInt(1000-400) + 400)+" views");
        holder.courseName.setText(shortTermCoursesData.getName());
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
                else
                    Toast.makeText(context,"Login To Add To Wishlist",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return shortTermCoursesDatas.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{


        //        TextView discount;
//        TextView discountText;
        View parent;
//        TextView streaks;
        // ImageView soldout;

        TextView duration;
        MaterialFavoriteButton favorite;
        ImageView courseImage;
        TextView type;
        LinearLayout checkout;
        TextView rating;
        TextView views;
        TextView courseName;

        public RecyclerViewHolder(View view){
            super(view);

            views = view.findViewById(R.id.views);
            favorite = view.findViewById(R.id.likeButton);
            rating = view.findViewById(R.id.ratingtext);
            type = view.findViewById(R.id.type);
            checkout = view.findViewById(R.id.enrollLayout);
            duration = view.findViewById(R.id.tv_KM_tab4);
            courseName = view.findViewById(R.id.textView);
            courseImage = view.findViewById(R.id.imageView);
            this.parent = view;

        }

    }


}
