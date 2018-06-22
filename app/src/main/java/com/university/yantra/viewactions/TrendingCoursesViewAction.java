package com.university.yantra.viewactions;

import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCoursesData;

import java.util.List;

/**
 * Created by rahul on 10/3/18.
 */

public interface TrendingCoursesViewAction  extends BaseViewAction{

    public void setTrendingCoursesRecyclerView(List<Course> courseList);
    public void startVideos();

}
