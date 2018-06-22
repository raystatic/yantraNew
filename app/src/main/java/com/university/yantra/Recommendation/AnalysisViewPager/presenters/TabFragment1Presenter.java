package com.university.yantra.Recommendation.AnalysisViewPager.presenters;

import com.university.yantra.Recommendation.TabFragmentViewAction;
import com.university.yantra.common.AbstractCallback;
import com.university.yantra.common.EntitiesLoader;
import com.university.yantra.common.LikeEntityManager;
import com.university.yantra.listeners.OnEntitiesReceivedListener;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.presenters.BasePresenter;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sharda on 17/03/18.
 */

public class TabFragment1Presenter extends BasePresenter {
    OnEntitiesReceivedListener<ShortTermCourse> shortTermCoursesListener;
    OnEntitiesReceivedListener<Course> coursesListener;
    TabFragmentViewAction viewAction;
    public TabFragment1Presenter(OnEntitiesReceivedListener<ShortTermCourse> shortTermCoursesListener,
                                 OnEntitiesReceivedListener<Course> coursesListener,TabFragmentViewAction viewAction
                                 ){
        this.viewAction = viewAction;
        this.shortTermCoursesListener = shortTermCoursesListener;
        this.coursesListener = coursesListener;
    }

    public void likeCourse(int id){
        repository.likeCourse(id, new LikeEntityManager(viewAction));
    }
    public void likeShortTermCourse(int id){repository.likeShortTermCourse(id, new LikeEntityManager(viewAction) );}

    public void loadShortTermCourses(Map<String, String> uriQuery){
        repository.getShortTermCourses(uriQuery, new EntitiesLoader<>(shortTermCoursesListener));
    }

    public void loadCourses(Map<String, String> uriQuery){
        repository.getCourses(uriQuery, new EntitiesLoader<>(coursesListener));
    }
}
