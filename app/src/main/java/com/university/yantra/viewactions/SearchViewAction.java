package com.university.yantra.viewactions;

import com.university.yantra.models.Campaigners;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;

import java.util.List;

/**
 * Created by Rishabh on 4/19/2018.
 */

public interface SearchViewAction extends BaseViewAction {
    public void setUniversityRecyclerView(List<University> universityDataList);
    public void setCoursesRecyclerView(List<Course> courses);
    public void setShortTermCoursesRecyclerView(List<ShortTermCourse> shortTermCourses);
    public void setCampaignersRecyclerView(List<Campaigners> campaigners);
    public void showLoader();
    public void hideLoader();
}
