package com.university.yantra.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
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
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.University;
import com.university.yantra.models.UniversityData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rahul on 6/3/18.
 */

public class ViewMoreAdapter extends RecyclerView.Adapter<ViewMoreAdapter.MyViewHolder> {

    private PrefManager prefManager;
    private Context context;
    Random r;
    private LayoutInflater inflater;
    List<University> universityDatas = new ArrayList<>();

    public ViewMoreAdapter(PrefManager prefManager, Context context, List<University> universityDatas) {
        this.prefManager = prefManager;
        this.context = context;
        this.universityDatas = universityDatas;

        r = new Random();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void OnLiked(int id){
        like(id);
    }
    public void like(int id) {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.viewmore_adapter_layout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final University universityData = universityDatas.get(position);

        Picasso.with(context)
                .load(universityData.getFeatureImage())
                .fit()
                .into(holder.collegeImage);
        Picasso.with(context)
                .load(universityData.getLogo())
                .fit()
                .into(holder.icon);

        holder.collegeName.setText(universityData.getName());
        Log.d("imageurl",universityData.getFeatureImage()+"");
        holder.location.setText(universityData.getLocation().getStreetAddress()+","+universityData.getLocation().getCity());
        holder.rating.setText(universityData.getRating()+"");
        holder.type.setText(universityData.getOwnedType());
        holder.merit.setText(universityData.getAdmissionProcedure());
        if(universityData.getOwnedType().equals("PUB"))
            holder.type.setText("Public");
        else
            holder.type.setText("Private");
        if(universityData.getAdmissionProcedure().equals("M"))
            holder.merit.setText("Merit");
        else
            holder.merit.setText("Entrance");
        holder.views.setText((r.nextInt(1000-400) + 400)+" views");
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

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
//        View parent;
//        TextView collegeName;
//        TextView location;
//        ImageView icon;
//        TextView merit;
//        TextView type;
//        TextView rating;
//        ImageView collegeImage;
//        RelativeLayout call;
//        LinearLayout enroll;

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

        public MyViewHolder(View itemView) {
            super(itemView);

            favorite = itemView.findViewById(R.id.likeButton);
            call = itemView.findViewById(R.id.cal_btn);
            icon = itemView.findViewById(R.id.imageView2);
            merit = itemView.findViewById(R.id.tv_KM_tab4);
            type = itemView.findViewById(R.id.type);
            views = itemView.findViewById(R.id.views);
            rating = itemView.findViewById(R.id.ratingtext);
            location = itemView.findViewById(R.id.location);
            collegeName = itemView.findViewById(R.id.textView);
            collegeImage = itemView.findViewById(R.id.imageView);
            enroll = itemView.findViewById(R.id.enrollLayout);
            linearLayout=itemView.findViewById(R.id.uni_linear_layout);
            this.parent = itemView;

        }
    }

}
