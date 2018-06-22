package com.university.yantra.viewactions;

import com.university.yantra.models.Campaigners;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;

import java.util.List;

/**
 * Created by rahul on 18/4/18.
 */

public interface WishlistViewAction extends BaseViewAction {

    public void setWishListRecyclerView(List<University> universityDataList);
    public void setWishListCourses(List<Course> courseList);
    public void setWishListShortTermCourses(List<ShortTermCourse> shortTermCourseList);
    public void setWishListCampaigners(List<Campaigners> campaignersList);


}
