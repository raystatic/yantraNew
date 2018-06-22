package com.university.yantra.Recommendation.AnalysisViewPager;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.gms.common.SignInButton;
import com.university.yantra.Activities.BackPressed;
import com.university.yantra.Activities.Recommendations;
import com.university.yantra.R;
import com.university.yantra.Recommendation.AnalysisViewPager.presenters.TabFragment1Presenter;
import com.university.yantra.Recommendation.BaseFragment;
import com.university.yantra.Recommendation.TabFragmentViewAction;
import com.university.yantra.Recommendation.Top2;
import com.university.yantra.adapters.RecommendedCourseAdapter;
import com.university.yantra.adapters.ShortTermAdapter;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.listeners.OnEntitiesReceivedListener;
import com.university.yantra.models.Course;
import com.university.yantra.models.ShortTermCourse;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Paras on 7/19/2017.
 */

public class TabFragment1 extends BaseFragment implements BackPressed.OnBackPressedListener, TabFragmentViewAction{

    PieChart mChart;
    RadarChart radarChart;
    PrefManager prefManager;
    RoundCornerProgressBar Intelligence;
    RoundCornerProgressBar Emotional;
    RoundCornerProgressBar Social;
    ImageView Image;
    Bitmap bitmap;
    TextView IntelligenceScore;
    TextView EmotionalScore;
    TextView SocialScore;
    RoundCornerProgressBar Spatial;
    RoundCornerProgressBar Creativity;
    TextView VerbalScore;
    RoundCornerProgressBar Verbal;
    public static final String ANALYSIS_DONE = "Analysis_Done";
    TextView CreativityScore;
    TextView SpatialScore;
    PieChart mChart2;
    TextView Childname;
    TextView childName2;
    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    ImageView i1,i2,i3,i4,i5,i6;
    CardView cardx, careerReportButton;
    Context mContext;
    RecyclerView coursesRecyclerView, shortTermCoursesRecyclerView;
    ShortTermAdapter shortTermAdapter;
    RecommendedCourseAdapter recommendedCourseAdapter;

    TabFragment1Presenter presenter;


    float creative=20,intelligence=20,emotional=10,social=10,verbal=10,spatial=20,total=10;

    protected String[] mParties = new String[] {
            "Left Brain","Right Brain"
    };

    protected String[] mParties2 = new String[] {
            "Intelligence","Emotion","Social","Verbal Ability","Spatial","Creativity"
    };

    View view;
    Button report;
    CardView feedback;
    Intent intent;
    TextView AnalysisReport;
    ImageView guide,reportImage,guide2;
    //int[] images=new int[3];
//    String[] age=new String[3];
//    String[] gamename=new String[3];

    RecyclerView recyclerView;



    @Override
    public int getLayoutResId() {
        return R.layout.tab_fragment_3;

    }

    @Override
    public void inOnCreateView(View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = view;
        AnalysisReport = (TextView)view.findViewById(R.id.anylsisreport);
        Childname = (TextView)view.findViewById(R.id.childName);
        childName2 = (TextView)view.findViewById(R.id.name_child);
        prefManager = new PrefManager(getActivity());
        Social = (RoundCornerProgressBar)view.findViewById(R.id.socials);
        Spatial =(RoundCornerProgressBar)view.findViewById(R.id.spatia);
        Creativity =(RoundCornerProgressBar)view.findViewById(R.id.creativity);
        IntelligenceScore = (TextView)view.findViewById(R.id.intelligenceScore);
        Verbal = (RoundCornerProgressBar)view.findViewById(R.id.verba);
        Emotional =(RoundCornerProgressBar)view.findViewById(R.id.emo);
        EmotionalScore =(TextView)view.findViewById(R.id.emotionalScore);
        SpatialScore =(TextView)view.findViewById(R.id.spatialScore);
        VerbalScore = (TextView)view.findViewById(R.id.verbalScore);
        SocialScore = (TextView)view.findViewById(R.id.socialScore);
        CreativityScore =(TextView)view.findViewById(R.id.creativeScore);
        Intelligence = (RoundCornerProgressBar)view.findViewById(R.id.intelli);
        shortTermCoursesRecyclerView = view.findViewById(R.id.shortTermCoursesRecyclerView);
        coursesRecyclerView = view.findViewById(R.id.coursesRecyclerView);
        careerReportButton = view.findViewById(R.id.careerReportButton);

        feedback=view.findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Top2 top2 = (Top2) getParentFragment();
                if(top2 != null)
                    top2.viewPager.setCurrentItem(1, true);
            }
        });
        reportImage=(ImageView)view.findViewById(R.id.report_image);
        reportImage.setColorFilter(getResources().getColor(R.color.dark_purple));


        mChart = (PieChart) view.findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setCenterTextTypeface(mTfLight);
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(48f);
        mChart.setTransparentCircleRadius(50f);
        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        radarChart = view.findViewById(R.id.radarChart);
        setupRadarChart();

        i1=(ImageView)view.findViewById(R.id.imag1);
        i2=(ImageView)view.findViewById(R.id.imag2);
        i3=(ImageView)view.findViewById(R.id.imag3);
        i4=(ImageView)view.findViewById(R.id.imag4);
        i5=(ImageView)view.findViewById(R.id.imag5);
        i6=(ImageView)view.findViewById(R.id.imag6);

        i1.setColorFilter(getResources().getColor(R.color.fabColor));
        i2.setColorFilter(getResources().getColor(R.color.accent));
        i3.setColorFilter(getResources().getColor(R.color.green));
        i4.setColorFilter(getResources().getColor(R.color.colorAccent));
        i5.setColorFilter(getResources().getColor(R.color.red));
        i6.setColorFilter(getResources().getColor(R.color.gold));

        guide=(ImageView)view.findViewById(R.id.guide);
        guide.setColorFilter(getResources().getColor(R.color.black));
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                skipp(1);
            }
        });
        guide2=(ImageView)view.findViewById(R.id.guide2);
        guide2.setColorFilter(getResources().getColor(R.color.dark_purple));
        guide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                skipp(2);
            }
        });

        setData(2, 100,mChart,mParties);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mChart.getLegend();
        l.setEnabled(false);
        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(10f);

        mChart2 = (PieChart) view.findViewById(R.id.chart2);
        mChart2.setUsePercentValues(true);
        mChart2.getDescription().setEnabled(false);
        mChart2.setExtraOffsets(5, 10, 5, 5);
        mChart2.setDragDecelerationFrictionCoef(0.95f);
        mChart2.setCenterTextTypeface(mTfLight);
        mChart2.setDrawHoleEnabled(true);
        mChart2.setHoleColor(Color.WHITE);
        mChart2.setTransparentCircleColor(Color.WHITE);
        mChart2.setTransparentCircleAlpha(110);
        mChart2.setHoleRadius(45f);
        mChart2.setTransparentCircleRadius(47f);
        mChart2.setDrawCenterText(true);
        mChart2.setRotationAngle(0);
        mChart2.setRotationEnabled(true);
        mChart2.setHighlightPerTapEnabled(true);
        setData2(6, 100,mChart2,mParties2);
        mChart2.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l2 = mChart2.getLegend();
        l2.setEnabled(false);

        // entry label styling
        mChart2.setEntryLabelColor(Color.WHITE);
        mChart2.setEntryLabelTypeface(mTfRegular);
        mChart2.setEntryLabelTextSize(12f);

        presenter = new TabFragment1Presenter(
                new OnEntitiesReceivedListener<ShortTermCourse>(getContext()) {
                    public void onReceived(List<ShortTermCourse> entities){
                        setShortTermRecyclerView(entities);
                    }
                },
                new OnEntitiesReceivedListener<Course>(getContext()) {
                    public void onReceived(List<Course> entities) {
                        setCoursesRecyclerView(entities);
                    }
                }, this);
        presenter.loadCourses(new HashMap<String, String>());
        presenter.loadShortTermCourses(new HashMap<String, String>());

    }

    public static TabFragment1 create() {

        return new TabFragment1();
    }
    public Bitmap getThumbnail(String filename) {

        Bitmap thumbnail = null;

// If no file on external storage, look in internal storage
        try {
            File filePath = getActivity().getFileStreamPath(filename);
            FileInputStream fi = new FileInputStream(filePath);
            thumbnail = BitmapFactory.decodeStream(fi);
            return thumbnail;
        } catch (Exception ex) {
            Log.e("getThumbnail() internal", ex.getMessage());
            return null;
        }


    }

    private void setData(int count, float range, PieChart mChart, String[] mParties) {

        float l;
        total=creative+intelligence+social+spatial+emotional+verbal;
        Log.d("totalScore",total+"");


        float left=intelligence+spatial+verbal;
        Log.d("totalscore",left+"");
        l=(float)(left/total)*100;
        Log.d("totalscoreleft",l+"");

        float right=social+emotional+creative;
        float r=(right/total)*100;
        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry((float)l,"Left Brain"));
        entries.add(new PieEntry((float)r,"Right Brain"));

        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(9f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);
        mChart.highlightValues(null);
        mChart.invalidate();
    }

    private void setData2(int count, float range, PieChart mChart, String[] mParties) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        if(intelligence!=0)
            entries.add(new PieEntry((float) ((intelligence/total)*100),"Intelligence"));

        if(creative!=0)
            entries.add(new PieEntry((float) ((creative/total)*100),"Creativity"));

        if(social!=0)
            entries.add(new PieEntry((float) ((social/total)*100),"Social"));

        if(spatial!=0)
            entries.add(new PieEntry((float) ((spatial/total)*100),"Spatial"));

        if(emotional!=0)
            entries.add(new PieEntry((float) ((emotional/total)*100),"Emotional"));

        if(verbal!=0)
            entries.add(new PieEntry((float) ((verbal/total)*100),"Verbal Ability"));


        PieDataSet dataSet = new PieDataSet(entries, " ");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);
        mChart.highlightValues(null);
        mChart.invalidate();
    }

    void skipp(int x)
    {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        if(x==1)
        {
            alertDialogBuilder.setMessage("It shows comparative distribution of various intelligences which includes Intelligence Factor, Emotional Factor, Verbal Ability Factor, Creativity Factor, Social Factor, & Spatial Intelligence Factor. ");
            alertDialogBuilder.setTitle("Smart Branding Quotient");
        }

        if(x==2)
        {
            alertDialogBuilder.setMessage("Some of the data points like handwriting analysis has not been included in this free report. We recommend you to get 15-16 pages’ career report for child.");
            alertDialogBuilder.setTitle("Brief Analysis Report");
        }

            alertDialogBuilder.setPositiveButton("Got It",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //Toast.makeText(getActivity(),"Resume",Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    @Override
    public void doBack()
    {
        Intent i= new Intent(getActivity(), Recommendations.class);
        startActivity(i);
    }

    @Override
    public void startAnalysisNotDone() {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkError(String message) {

    }

    void setupRadarChart(){
        List<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(4f,0));
        entries.add(new RadarEntry(5f,1));
        entries.add(new RadarEntry(10f,2));
        entries.add(new RadarEntry(7f,3));
        entries.add(new RadarEntry(6f,4));
        RadarDataSet radarDataSet = new RadarDataSet(entries, "Paras");
        radarDataSet.setDrawFilled(true);
        radarDataSet.setColor(Color.CYAN);
//        radarDataSet.setFillAlpha(0);
        List<IRadarDataSet> radarDataSets = new ArrayList<>();
        radarDataSets.add(radarDataSet);
        RadarData radarData = new RadarData(radarDataSets);
        radarData.setHighlightEnabled(true);
        radarData.setValueTextColor(Color.BLACK);
        radarChart.animate();
        radarChart.setData(radarData);
        radarChart.getXAxis().setTextColor(R.color.dark_purple);
        radarChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            private String[] mActivities = new String[]{"Social", "Intelligence", "Spatial", "Emotion", "Creative"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        radarChart.getDescription().setEnabled(false);
        radarChart.getSkipWebLineCount();
        radarChart.setWebLineWidthInner(1.5f);
        radarChart.setWebColor(Color.RED);
        radarChart.setWebLineWidth(2.5f);
        radarChart.setWebColorInner(Color.BLACK);
        radarChart.getLegend().setEnabled(false);
        radarChart.getViewPortHandler().setChartDimens(50,50);
//        radarChart.setWebLineWidthInner(4f);

    }

    public void setShortTermRecyclerView(List<ShortTermCourse> entities) {
        shortTermCoursesRecyclerView.setHasFixedSize(true);
        shortTermCoursesRecyclerView.removeAllViews();
        shortTermCoursesRecyclerView.setNestedScrollingEnabled(false);
        shortTermCoursesRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        shortTermCoursesRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        shortTermAdapter = new ShortTermAdapter(MyApplication.getInstance().prefManager,getActivity(),entities){
            @Override
            public void like(int id) {
                presenter.likeShortTermCourse(id);
            }
        };
        shortTermCoursesRecyclerView.setAdapter(shortTermAdapter);
        shortTermCoursesRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        shortTermAdapter.notifyDataSetChanged();
    }



    public void setCoursesRecyclerView(List<Course> courses){
        coursesRecyclerView.setHasFixedSize(true);
        coursesRecyclerView.removeAllViews();
        coursesRecyclerView.setNestedScrollingEnabled(false);
        coursesRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        coursesRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        RecommendedCourseAdapter courseAdapter =
                new RecommendedCourseAdapter(MyApplication.getInstance().prefManager,getActivity(), courses){
                    @Override
                    public void like(int id) {
                        presenter.likeCourse(id);
                    }
                };

        coursesRecyclerView.setAdapter(courseAdapter);
        coursesRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        courseAdapter.notifyDataSetChanged();
    }

}