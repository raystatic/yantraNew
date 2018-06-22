package com.university.yantra.presenters;

import com.university.yantra.common.EntitiesLoader;
import com.university.yantra.listeners.OnEntitiesReceivedListener;
import com.university.yantra.models.Tag;
import com.university.yantra.models.Videos;
import com.university.yantra.viewactions.ExploreViewAction;
import com.university.yantra.viewactions.VideosViewAction;

import java.util.Map;

/**
 * Created by Rishabh on 3/31/2018.
 */

public class ExplorePresenter extends BasePresenter{
    OnEntitiesReceivedListener<Tag> tagReceivedListener;
    ExploreViewAction viewAction;

    public ExplorePresenter(OnEntitiesReceivedListener<Tag> tagReceivedListener, ExploreViewAction viewAction){
        this.viewAction = viewAction;
        this.tagReceivedListener = tagReceivedListener;
    }

    public void loadTags(Map<String, String> uriQuery){
        repository.getTags(uriQuery, new EntitiesLoader<>(tagReceivedListener));
    }
}
