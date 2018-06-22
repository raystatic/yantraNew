package com.university.yantra.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.university.yantra.Activities.VideosActivity;
import com.university.yantra.R;
import com.university.yantra.models.Library;
import com.university.yantra.viewactions.TrendingCoursesViewAction;

import java.util.List;

/**
 * Created by rahul on 19/3/18.
 */

public class AptitudeAdapter3 extends RecyclerView.Adapter<AptitudeAdapter3.MyViewHolder> {

    Context context;
    List<Library> libraries;
    TrendingCoursesViewAction viewAction;

    public AptitudeAdapter3(Context context, TrendingCoursesViewAction viewAction, List<Library> libraries) {
        this.context = context;
        this.libraries = libraries;
        this.viewAction = viewAction;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.aptitude_adapter3_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {

       // Drawable drawable= ContextCompat.getDrawable(context,R.drawable.)

        Library library=libraries.get(i);

        myViewHolder.topic.setText(library.getTopic());
//        myViewHolder.exp.collapse();
//        myViewHolder.cardView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (myViewHolder.exp.isExpanded())
//                {
//                    myViewHolder.imageView.setImageResource(R.drawable.ic_action_down2);
//                }
//                else
//                {
//                    myViewHolder.imageView.setImageResource(R.drawable.ic_action_up2);
//                }
//
//                myViewHolder.exp.toggle();
//                myViewHolder.exp.expand();
//                myViewHolder.exp.moveChild(0);
//                myViewHolder.exp.move(100);
//                myViewHolder.exp.setClosePosition(0);
//            }
//        });
        myViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAction.startVideos();
            }
        });

    }

    @Override
    public int getItemCount() {
        return libraries.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView1, cardView2, cardView3;
        TextView topic, percent;
        View parent;
        ExpandableRelativeLayout exp;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);

           // imageView=itemView.findViewById(R.id.imgDownAptAdapter3);
           // exp=itemView.findViewById(R.id.expLayoutAptAdapter3);
            cardView1=itemView.findViewById(R.id.card1AptAdapter3);
           // cardView2=itemView.findViewById(R.id.card2AptAdapter3);
            //cardView3=itemView.findViewById(R.id.card3AptAdapter3);
            parent=this.itemView;
            topic=itemView.findViewById(R.id.tvAptAdapter3);
            percent=itemView.findViewById(R.id.tvPercentAptAdapter);

        }
    }

}
