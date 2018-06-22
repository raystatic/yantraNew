package com.university.yantra.viewactions;

import com.university.yantra.models.University;
import com.university.yantra.models.UniversityData;

import java.util.List;

/**
 * Created by rahul on 10/3/18.
 */

public interface TrendingUniversitiesViewAction extends BaseViewAction{

    public void setTrendingUniversitiesRecyclerView(List<University> universityDataList);

}
