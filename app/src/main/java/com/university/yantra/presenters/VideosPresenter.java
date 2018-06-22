package com.university.yantra.presenters;

import android.content.Context;
import android.util.Log;
import android.widget.VideoView;

import com.university.yantra.Recommendation.TabFragmentViewAction;
import com.university.yantra.common.AbstractCallback;
import com.university.yantra.common.EntitiesLoader;
import com.university.yantra.listeners.OnEntitiesReceivedListener;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.Videos;
import com.university.yantra.responses.PaginatedResponse;
import com.university.yantra.viewactions.VideosViewAction;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Rishabh on 3/31/2018.
 */

public class VideosPresenter extends BasePresenter{
    OnEntitiesReceivedListener<Videos> videosRecievedListener;
    VideosViewAction viewAction;

    public VideosPresenter(OnEntitiesReceivedListener<Videos> videosRecievedListener, VideosViewAction viewAction){
        this.viewAction = viewAction;
        this.videosRecievedListener = videosRecievedListener;
    }

    public void loadVideos(Map<String, String> uriQuery){
        repository.getVideos(uriQuery, new EntitiesLoader<>(videosRecievedListener));
    }
}
