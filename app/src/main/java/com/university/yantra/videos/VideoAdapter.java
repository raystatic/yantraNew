package com.university.yantra.videos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.university.yantra.R;
import com.university.yantra.common.EntitiesLoader;
import com.university.yantra.common.MyApplication;
import com.university.yantra.models.Videos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 21/2/18.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> implements  PopupMenu.OnMenuItemClickListener {
    String link;
    Context ctx;
    List<Videos> videos;
    DisplayMetrics displayMetrics;


    public  VideoAdapter(Context ctx, List<Videos> videos, DisplayMetrics displayMetrics)
    {
        this.ctx=ctx;
        this.videos = videos;
        this.displayMetrics=displayMetrics;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(ctx).inflate(R.layout.youtube_adapter_layout,parent,false);

        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        Double height=displayMetrics.heightPixels/(3.0);
//
//        holder.videoLayout.getLayoutParams().height=height.intValue();
//        holder.videoLayout.getLayoutParams().width=displayMetrics.widthPixels;

        final Videos video = videos.get(position);
        //if(videoDataArrayList.get(holder.getAdapterPosition()).getTitle()!=null) {
            holder.title.setText(video.getName());
        //}
       // holder.Age.setVisibility(View.GONE);

        holder.Age.setText(video.getCategory());
        holder.popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                link="https://www.youtube.com/watch?v="+video.getLink();
                Log.d("linkgen",link+"");
                showPopup(view);
            }
        });


        final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                Toast.makeText(ctx,"error loading thumbnail"+errorReason,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                //holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };

        holder.youTubeThumbnailView.initialize(Config.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

   //             if(urls.get(position)!=null) {
                    youTubeThumbnailLoader.setVideo(video.getLink());

                Log.d("urlfound",video.getLink());
     //           }
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //Toast.makeText(ctx,"failed",Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(ctx, v);
        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(this);
        inflater.inflate(R.menu.video_menu_popup, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.share:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, link+"");
                        sendIntent.setType("text/plain");
                        ctx.startActivity(Intent.createChooser(sendIntent,"Choose an application"));
                        return true;
                    default:
                        return false;
                }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        ImageButton popup ;
        protected ImageView playButton;
        TextView Age;
        TextView title;
        LinearLayout videoLayout;


        public MyViewHolder(View itemView) {
            super(itemView);
            title =(TextView)itemView.findViewById(R.id.title);
            Age = (TextView)itemView.findViewById(R.id.age);
            popup = (ImageButton)itemView.findViewById(R.id.popupmenu);

            //relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);
            youTubeThumbnailView.setOnClickListener(this);

            videoLayout=itemView.findViewById(R.id.video_layout);

        }


        @Override
        public void onClick(View view) {
            Intent intent=YouTubeStandalonePlayer.createVideoIntent((Activity) ctx,Config.DEVELOPER_KEY,videos.get(getLayoutPosition()).getLink());
            ctx.startActivity(intent);
        }
    }


}
