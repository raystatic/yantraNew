package com.university.yantra.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.university.yantra.R;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.ShortTermCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 18/3/18.
 */

public class AptitudeAdapter2 extends RecyclerView.Adapter<AptitudeAdapter2.MyViewHolder>{

    private PrefManager prefManager;
    private Context context;
    private LayoutInflater inflater;
    List<ShortTermCourse> urls = new ArrayList<>();

    public AptitudeAdapter2(PrefManager prefManager, Context context, List<ShortTermCourse> urls) {
        this.prefManager = prefManager;
        this.context = context;
        this.urls = urls;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.aptitude_adapter2_layout,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        final ShortTermCourse shortTermCoursesData = urls.get(i);
        Picasso.with(context)
                .load(shortTermCoursesData.getImage())
                .fit()
                .into(myViewHolder.imageView);
        myViewHolder.courses.setText(shortTermCoursesData.getName());

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView courses;
        View parent;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imgAptAdapter2);
            courses=itemView.findViewById(R.id.tvCourseAptAdapter2);
            parent=this.itemView;

        }
    }

}
