package com.university.yantra.viewactions;

import com.university.yantra.models.AmbassdorsData;
import com.university.yantra.models.Campaigners;

import java.util.List;

/**
 * Created by rahul on 10/3/18.
 */

public interface AmbasdorsViewAction extends BaseViewAction {

    public void setAmbassdorRecyclerView(List<Campaigners> ambassdorsDataList);

}
