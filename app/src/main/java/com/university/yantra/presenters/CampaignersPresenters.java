package com.university.yantra.presenters;

import android.content.Context;
import android.util.Log;

import com.university.yantra.common.AbstractCallback;
import com.university.yantra.common.LikeEntityManager;
import com.university.yantra.models.Campaigners;
import com.university.yantra.repositories.CampaignersRepository;
import com.university.yantra.responses.PaginatedResponse;
import com.university.yantra.viewactions.AmbasdorsViewAction;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rahul on 10/3/18.
 */

public class CampaignersPresenters {

    AmbasdorsViewAction ambasdorsViewAction;
    Context context;
    CampaignersRepository campaignersRepository;

    public CampaignersPresenters(AmbasdorsViewAction ambasdorsViewAction, Context context, CampaignersRepository campaignersRepository) {
        this.ambasdorsViewAction = ambasdorsViewAction;
        this.context = context;
        this.campaignersRepository = campaignersRepository;
    }

    public void likeCampaigners(int id){campaignersRepository.likeCampaigners(id, new LikeEntityManager(ambasdorsViewAction));}
    public void getCampaigners()
    {
        campaignersRepository.getCampaigners(new HashMap<String, String>(), new AbstractCallback(ambasdorsViewAction) {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("camp response code",response.code()+","+response.message());
                PaginatedResponse<Campaigners> arrayResponse=(PaginatedResponse<Campaigners>) response.body();
                if (arrayResponse!=null)
                {
                    ambasdorsViewAction.setAmbassdorRecyclerView(arrayResponse.getResults());
                }
                else
                {
                    //ambasdorsViewAction.showMessage("null");
                }
            }
        });
    }
}
