package com.university.yantra.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.university.yantra.R;
import com.university.yantra.adapters.AmbassdorsRecyclerViewAdapter;
import com.university.yantra.adapters.RecommendedCourseAdapter;
import com.university.yantra.adapters.RecommendedUniAdapter;
import com.university.yantra.adapters.SearchFragmmentAdapter;
import com.university.yantra.adapters.SearchIniActivityAdapter;
import com.university.yantra.adapters.ShortTermAdapter;
import com.university.yantra.apiservices.Repository;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Campaigners;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;
import com.university.yantra.presenters.SearchPresenter;
import com.university.yantra.viewactions.SearchViewAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, SearchViewAction {

    Context context;
    public int searchChoice = 1;
    SearchPresenter presenter;
    Map<String, String> queryMap;
    ProgressBar progressBar;
    AmbassdorsRecyclerViewAdapter ambassdorsRecyclerViewAdapter;
    RecommendedCourseAdapter courseAdapter;
    ShortTermAdapter shortTermAdapter;
    RecyclerView recyclerView;
    public SearchView searchView;
    String mQueryString;
    RecommendedUniAdapter adapter;

    android.os.Handler handler;

    RecyclerView recyclerView1;

    View view;
    DisplayMetrics displayMetrics;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search,container,false);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        addStaticData();
        queryMap = new HashMap<>();
        presenter = new SearchPresenter(this,getActivity(),new Repository());
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        handler = new android.os.Handler();

        searchView = (SearchView) view.findViewById(R.id.searchView);
        assert searchView != null;
        searchView.setOnQueryTextListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSearch);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        //recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerViewSearch);

        displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        return view;
    }
    public static SearchFragment newInstance(int index) {
        SearchFragment fragment = new SearchFragment();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    public void addStaticData(){

        RecyclerView recyclerView=view.findViewById(R.id.recyclerViewSearch);
        ArrayList<String> products= new ArrayList<>();

        products.add(" ");
        products.add(" ");

        products.add("Popular Searches");

        products.add("Sharda University");
        products.add("Cambridge University");
        products.add("Delhi University");
        products.add("Banasthali Vidyapeeth");
        products.add("Oxford University");
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.removeAllViewsInLayout();
        LinearLayoutManager mStaggeredVerticalLayoutManager = new LinearLayoutManager(getActivity()); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        SearchFragmmentAdapter adapter=new SearchFragmmentAdapter(getActivity(),products,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged() ;

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mQueryString=query;
        handler.removeCallbacksAndMessages(null);
        progressBar.setVisibility(View.GONE);
        if(TextUtils.isEmpty(query)){
            addStaticData();
        }
        else{
            queryMap.put("search",query);
            switch (searchChoice){
                case 1:presenter.searchUniversity(queryMap);
                    break;
                case 2:presenter.searchCourses(queryMap);
                    break;
                case 3:presenter.searchShortTermCourses(queryMap);
                    break;
                case 4:presenter.searchAmbassdors(queryMap);
                    break;
                default:showMessage("Error");
            }
        }

        return true;    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        mQueryString = newText;
        handler.removeCallbacksAndMessages(null);
        progressBar.setVisibility(View.VISIBLE);
//
//       // presenter.applySearch(mQueryString);
        if(TextUtils.isEmpty(newText)){
            addStaticData();
            progressBar.setVisibility(View.GONE);
            clearRecyclerView();
        }
        else {

            if(!searchView.toString().equals(""))

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(TextUtils.getTrimmedLength(mQueryString)>0){
                            queryMap.put("search",newText);
                            switch (searchChoice){
                                case 1:presenter.searchUniversity(queryMap);
                                    break;
                                case 2:presenter.searchCourses(queryMap);
                                    break;
                                case 3:presenter.searchShortTermCourses(queryMap);
                                    break;
                                case 4:presenter.searchAmbassdors(queryMap);
                                    break;
                                default:showMessage("Error");
                            }
                        }

                        else{

                        }

                    }
                }, 600);
        }
        return true;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void setUniversityRecyclerView(List<University> universityDataList) {
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter =
                new RecommendedUniAdapter(getActivity(),universityDataList,displayMetrics){
                    @Override
                    public void OnLiked(int id) {
                        presenter.likeUniversity(id);
                    }
                };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void setCoursesRecyclerView(List<Course> courses) {
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        courseAdapter =
                new RecommendedCourseAdapter(MyApplication.getInstance().prefManager,getActivity(),courses){
                    @Override
                    public void like(int id) {
                        presenter.likeCourse(id);
                    }
                };

        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        courseAdapter.notifyDataSetChanged();
    }

    @Override
    public void setShortTermCoursesRecyclerView(List<ShortTermCourse> shortTermCourses) {
        recyclerView.setHasFixedSize(true);
        recyclerView.removeAllViews();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        shortTermAdapter = new ShortTermAdapter(new PrefManager(getActivity()),getActivity(),shortTermCourses){
            @Override
            public void like(int id) {
                presenter.likeShortTermCourse(id);
            }
        };
        recyclerView.setAdapter(shortTermAdapter);
        recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        shortTermAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCampaignersRecyclerView(List<Campaigners> campaigners) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ambassdorsRecyclerViewAdapter = new AmbassdorsRecyclerViewAdapter(getActivity(),campaigners,displayMetrics){
            @Override
            public void like(int id) {
                presenter.likeCampaigners(id);
            }
        };
        recyclerView.setAdapter(ambassdorsRecyclerViewAdapter);
        ambassdorsRecyclerViewAdapter.notifyDataSetChanged();
    }

    public void clearRecyclerView() {


        recyclerView.removeAllViewsInLayout();
        recyclerView.removeAllViews();
        if(recyclerView.getAdapter()!=null)
            recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}

