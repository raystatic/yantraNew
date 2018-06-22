package com.university.yantra.viewactions;

import com.university.yantra.models.ShortTermCourse;

import java.util.List;

/**
 * Created by rahul on 10/3/18.
 */

public interface ShortTermCoursesViewAction extends BaseViewAction {

    public void setShortTermCoursesRecyclerView(List<ShortTermCourse> shortTermCourseList);

}
