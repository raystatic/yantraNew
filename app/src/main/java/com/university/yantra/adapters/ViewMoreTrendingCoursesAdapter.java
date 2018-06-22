package com.university.yantra.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
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
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.ShortTermCoursesData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rahul on 6/3/18.
 */

public class ViewMoreTrendingCoursesAdapter extends RecyclerView.Adapter<ViewMoreTrendingCoursesAdapter.MyViewHolder> {

    private PrefManager prefManager;
    private Context context;
    private LayoutInflater inflater;
    Random r ;
    List<Course> shortTermCoursesDatas = new ArrayList<>();
    DisplayMetrics displayMetrics;

    public ViewMoreTrendingCoursesAdapter(PrefManager prefManager, Context context, List<Course> shortTermCoursesDatas, DisplayMetrics displayMetrics) {
        this.prefManager = prefManager;
        this.context = context;
        r = new Random();
        this.shortTermCoursesDatas = shortTermCoursesDatas;
        this.displayMetrics=displayMetrics;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.viewmore_trendingcourses_adapter_layout,parent,false);
        return new MyViewHolder(view);
    }
    public void OnLiked(int id){
        like(id);
    }
    public void like(int id){

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

//        Double height=displayMetrics.heightPixels/(3.0);
//
//        holder.cardView.getLayoutParams().height=height.intValue();
//        holder.cardView.getLayoutParams().width=displayMetrics.widthPixels;

        final Course shortTermCoursesData = shortTermCoursesDatas.get(position);

        Picasso.with(context)
                .load(shortTermCoursesData.getImage())
                .fit()
                .into(holder.courseImage);
        holder.rating.setText(shortTermCoursesData.getRating()+"");
        holder.views.setText((r.nextInt(1000-400) + 400)+" views");
        holder.type.setText(shortTermCoursesData.getCourseType());
        holder.duration.setText(shortTermCoursesData.getDuration()+" Years");

        holder.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(shortTermCoursesData.getWikipediaUrl()));
                context.startActivity(browserIntent);
            }
        });
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

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        View parent;

        TextView duration;
        MaterialFavoriteButton favorite;
        ImageView courseImage;
        TextView type;
        LinearLayout checkout;
        TextView rating;
        TextView views;
        TextView courseName;

        public MyViewHolder(View itemView) {
            super(itemView);

//            relativeLayout=itemView.findViewById(R.id.above_image_viewMore_trendingCourses);
//            cardView=itemView.findViewById(R.id.card_view_viewMore_trendingCourses);
            views = itemView.findViewById(R.id.views);
            favorite = itemView.findViewById(R.id.likeButton);
            rating = itemView.findViewById(R.id.ratingtext);
            type = itemView.findViewById(R.id.type);
            checkout = itemView.findViewById(R.id.enrollLayout);
            duration = itemView.findViewById(R.id.tv_KM_tab4);
            courseName = itemView.findViewById(R.id.textView);
            courseImage = itemView.findViewById(R.id.imageView);
            this.parent = itemView;
        }
    }

}
