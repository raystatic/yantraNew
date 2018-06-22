package com.university.yantra.Recommendation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.university.yantra.R;
import com.university.yantra.ViewMore.ShortTermCoursesActivity;
import com.university.yantra.ViewMore.TrendingCoursesActivity;
import com.university.yantra.ViewMore.TrendingUniversitiesActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link Top1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Top1 extends Fragment  {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView offlineGames;
    ImageView videos;
    ImageView toys;

    public Top1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Top1.
     */
    // TODO: Rename and change types and number of parameters
    public static Top1 newInstance(String param1, String param2) {
        Top1 fragment = new Top1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Top1 newInstance()
    {
        Top1 fragment = new Top1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_recommendations, container, false);
        offlineGames= (ImageView)view.findViewById(R.id.game);
        videos = (ImageView)view.findViewById(R.id.video);
        toys = (ImageView)view.findViewById(R.id.toy) ;

        offlineGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ShortTermCoursesActivity.class));
            }
        });
        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getActivity(),TrendingCoursesActivity.class);
                startActivity(i);
            }
        });
        toys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getActivity(),TrendingUniversitiesActivity.class);
                startActivity(i);
            }
        });

        return view;

    }

}
