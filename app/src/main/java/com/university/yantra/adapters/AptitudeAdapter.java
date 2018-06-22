package com.university.yantra.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.university.yantra.Activities.AptitudeActivity;
import com.university.yantra.R;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 18/3/18.
 */

public class AptitudeAdapter extends RecyclerView.Adapter<AptitudeAdapter.MyViewHolder>{

    private PrefManager prefManager;
    private Context context;
    private LayoutInflater inflater;
    List<Course> shortTermCoursesDatas = new ArrayList<>();

    public AptitudeAdapter(PrefManager prefManager, Context context, List<Course> shortTermCoursesDatas) {
        this.prefManager = prefManager;
        this.context = context;
        this.shortTermCoursesDatas = shortTermCoursesDatas;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.aptitude_adapter1_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        final Course shortTermCoursesData = shortTermCoursesDatas.get(i);

        Picasso.with(context)
                .load(shortTermCoursesData.getImage())
                .fit()
                .into(myViewHolder.imageView);
        myViewHolder.course.setText(shortTermCoursesData.getName());
    }

    @Override
    public int getItemCount()
    {
        return shortTermCoursesDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView course;
        View parent;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgAptAdapter1);
            course=itemView.findViewById(R.id.tvCourseAptAdapter1);
            parent=this.itemView;
        }
    }

}
