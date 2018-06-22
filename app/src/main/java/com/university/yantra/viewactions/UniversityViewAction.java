package com.university.yantra.viewactions;


import com.airbnb.lottie.L;
import com.university.yantra.models.Campaigners;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;

import java.util.List;

/**
 * Created by Rishabh on 2/18/2018.
 */

public interface UniversityViewAction extends BaseViewAction {
    public void setCoursesRecyclerView(List<Course> courses);
    public void setUniversitiesRecyclerView(List<University> universitiesi);
    public void setShortTermCoursesRecyclerView(List<ShortTermCourse> shortTermCourses);
    public void setCampaignersRecyclerView(List<Campaigners> campaigners);
    public void setLikedUniversity(int id);


}
