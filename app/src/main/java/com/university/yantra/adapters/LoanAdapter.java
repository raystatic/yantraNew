package com.university.yantra.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.university.yantra.R;
import com.university.yantra.models.Tag;

import java.util.ArrayList;

/**
 * Created by rahul on 3/5/18.
 */

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> urls, tags, views;
    DisplayMetrics displayMetrics;

    public LoanAdapter(Context context, ArrayList<String> urls, ArrayList<String> tags, ArrayList<String> views, DisplayMetrics displayMetrics) {
        this.context = context;
        this.urls = urls;
        this.tags = tags;
        this.views = views;
        this.displayMetrics=displayMetrics;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.loan_card_layout,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        //Tag tag = tags.get(i);
        //final String url = tag.getImage();

        Double height=displayMetrics.heightPixels/(3.0);
        myViewHolder.relativeLayout.getLayoutParams().height=height.intValue();
        myViewHolder.relativeLayout.getLayoutParams().width=displayMetrics.widthPixels;

        Picasso.with(context)
                .load(urls.get(i))
                .fit()
                .into(myViewHolder.collegeImage);
        myViewHolder.textView_tags.setText(tags.get(i));
        myViewHolder.textView_views.setText(views.get(i));
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        View parent;
        //        TextView streaks;
        // ImageView soldout;
        TextView textView_tags, textView_views;

        ImageView collegeImage;
        RelativeLayout relativeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView_tags=(TextView)itemView.findViewById(R.id.tv_tags_explore);
            textView_views=(TextView)itemView.findViewById(R.id.tv_views_explore);
            relativeLayout=itemView.findViewById(R.id.loan_relative_layout);
            collegeImage = itemView.findViewById(R.id.imageView);
            this.parent = itemView;

        }
    }

}
