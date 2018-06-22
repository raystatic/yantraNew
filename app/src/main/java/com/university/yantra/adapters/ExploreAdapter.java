package com.university.yantra.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.university.yantra.Activities.MainActivity;
import com.university.yantra.R;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Tag;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;
import static java.security.AccessController.getContext;

/**
 * Created by Rishabh on 2/22/2018.
 */

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.RecyclerViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<Tag> tags;
    DisplayMetrics displayMetrics;
    public ExploreAdapter(Context context,List<Tag> tags,DisplayMetrics displayMetrics){
        this.context = context;
        this.displayMetrics = displayMetrics;
        this.tags=tags;
        inflater = LayoutInflater.from(context);


    }


    @Override
    public ExploreAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.explore_card_layout,parent,false);
        ExploreAdapter.RecyclerViewHolder recyclerViewHolder=new ExploreAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(ExploreAdapter.RecyclerViewHolder holder, int position) {
        Tag tag = tags.get(position);
        final String url = tag.getImage();


        Double height = displayMetrics.heightPixels/(3.0);
        holder.relativeLayout.getLayoutParams().height = height.intValue();
        holder.relativeLayout.getLayoutParams().width = displayMetrics.widthPixels;

        Picasso.with(context)
                .load(url)
                .fit()
                .into(holder.collegeImage);
        holder.textView_tags.setText(tag.getName());
        holder.textView_views.setText(tag.getViews()+"");
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{


        //        TextView discount;
//        TextView discountText;
        View parent;
//        TextView streaks;
        // ImageView soldout;
        TextView textView_tags, textView_views;

        CardView cardView;
        ImageView collegeImage;
        RelativeLayout relativeLayout;

        public RecyclerViewHolder(View view){
            super(view);

            relativeLayout = (RelativeLayout)view.findViewById(R.id.relative_layout);
            textView_tags=(TextView)view.findViewById(R.id.tv_tags_explore);
            textView_views=(TextView)view.findViewById(R.id.tv_views_explore);
            cardView  =(CardView)view.findViewById(R.id.card_view);
            collegeImage = view.findViewById(R.id.imageView);
            this.parent = view;

        }
    }
}
