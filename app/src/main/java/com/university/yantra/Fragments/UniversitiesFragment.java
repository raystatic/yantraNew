package com.university.yantra.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.squareup.picasso.Picasso;
import com.university.yantra.Activities.AptitudeActivity;
import com.university.yantra.ViewMore.TrendingUniversitiesActivity;
import com.university.yantra.R;
import com.university.yantra.ViewMore.AmbassdorsActivity;
import com.university.yantra.ViewMore.ShortTermCoursesActivity;
import com.university.yantra.ViewMore.TrendingCoursesActivity;
import com.university.yantra.adapters.AmbassdorsRecyclerViewAdapter;
import com.university.yantra.adapters.RecommendedCourseAdapter;
import com.university.yantra.adapters.RecommendedUniAdapter;
import com.university.yantra.adapters.ShortTermAdapter;
import com.university.yantra.common.MyApplication;
import com.university.yantra.common.PrefManager;
import com.university.yantra.models.Campaigners;
import com.university.yantra.models.ShortTermCourse;
import com.university.yantra.models.University;
import com.university.yantra.presenters.HomePagePresenter;
import com.university.yantra.repositories.HomePageRepository;
import com.university.yantra.models.Course;
import com.university.yantra.viewactions.UniversityViewAction;
import com.university.yantra.models.AmbassdorsData;
import com.university.yantra.models.ShortTermCoursesData;
import com.university.yantra.models.Tags;
import com.university.yantra.models.UniversityData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UniversitiesFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener,UniversityViewAction{

    public static final String TAG = UniversitiesFragment.class.getSimpleName();
    View view;
    HomePagePresenter presenter;
    private SliderLayout imageSlider;
    private ImageView bannerImg1,bannerImg2,bannerImg3,bannerImg4,bannerImg5,bannerImg6;
    private ImageView choiceImg1,school,recommender,courses,choiceImg5,universities;
    private RecyclerView universityRecyclerView;
    private RecyclerView coursesRecyclerView;
    private RecyclerView shortTermRecyclerView;
    private RecyclerView ambassdorsRecyclerView;
    ArrayList<AmbassdorsData> ambassdorsDatas = new ArrayList<>();
    RecommendedUniAdapter adapter;
    RecommendedCourseAdapter courseAdapter;
    ShortTermAdapter shortTermAdapter;
    AmbassdorsRecyclerViewAdapter ambassdorsRecyclerViewAdapter;
    ArrayList<UniversityData> universityDatas = new ArrayList<>();
    ArrayList<ShortTermCoursesData> shortTermCoursesDatas = new ArrayList<>();
    ArrayList<ShortTermCoursesData> coursesDatas = new ArrayList<>();

    RelativeLayout sliderRelativeLayout;
    DisplayMetrics displayMetrics;

    ImageView imageViewLast;

    LinearLayout lastLayout;

    TextView tv_viewMore_trendingUni, tv_viewMore_trendingCourses, tv_shortTermCarrier, tv_viewMore_ambassaddor;

    @Override
    public void setLikedUniversity(int id) {
        presenter.likeUniversity(id);
    }

    public  UniversitiesFragment(){
        super();
        Log.d(TAG, "Created University Fragment instance.");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void inOnCreateView(View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Fresco.initialize(getContext());
        this.view  = view;

        imageViewLast=view.findViewById(R.id.img_home_last);
        lastLayout=view.findViewById(R.id.last_layout);

        sliderRelativeLayout=view.findViewById(R.id.slider_rel);


        bannerImg1 = view.findViewById(R.id.banner1);
        bannerImg2 = view.findViewById(R.id.banner2);
        bannerImg3 = view.findViewById(R.id.banner3);
        bannerImg4 = view.findViewById(R.id.banner4);
        bannerImg5 = view.findViewById(R.id.banner5);

//        uniRelativeLayout=findViewById(R.id.uni_relative_layout);

        displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        Double height=displayMetrics.heightPixels/(3.0);
        bannerImg1.getLayoutParams().height = displayMetrics.heightPixels/6;
        bannerImg1.getLayoutParams().width = ((Double)(displayMetrics.widthPixels/1.5)).intValue();
        bannerImg1.requestLayout();

        bannerImg2.getLayoutParams().height = displayMetrics.heightPixels/6;
        bannerImg2.getLayoutParams().width = ((Double)(displayMetrics.widthPixels/1.5)).intValue();
        bannerImg2.requestLayout();

        bannerImg3.getLayoutParams().height = displayMetrics.heightPixels/6;
        bannerImg3.getLayoutParams().width = ((Double)(displayMetrics.widthPixels/1.5)).intValue();
        bannerImg3.requestLayout();

        bannerImg4.getLayoutParams().height = displayMetrics.heightPixels/6;
        bannerImg4.getLayoutParams().width = ((Double)(displayMetrics.widthPixels/1.5)).intValue();
        bannerImg4.requestLayout();

        bannerImg5.getLayoutParams().height = displayMetrics.heightPixels/6;
        bannerImg5.getLayoutParams().width = ((Double)(displayMetrics.widthPixels/1.5)).intValue();
        bannerImg5.requestLayout();


//        uniRelativeLayout.getLayoutParams().height=height.intValue();
//        uniRelativeLayout.getLayoutParams().width=displayMetrics.widthPixels;

        imageSlider = view.findViewById(R.id.slider);

        sliderRelativeLayout.getLayoutParams().height=height.intValue();
        sliderRelativeLayout.getLayoutParams().width=displayMetrics.widthPixels;

        LinearLayout choiceLayout1=view.findViewById(R.id.choicelayout1);
        LinearLayout choiceLayout2=view.findViewById(R.id.choicelayout2);
        LinearLayout choiceLayout3=view.findViewById(R.id.choicelayout3);

        Double choiceHeight1=displayMetrics.heightPixels/(4.0);

        choiceLayout1.getLayoutParams().height=choiceHeight1.intValue();
        choiceLayout1.getLayoutParams().width=displayMetrics.widthPixels;

        choiceLayout2.getLayoutParams().height=choiceHeight1.intValue();
        choiceLayout2.getLayoutParams().width=displayMetrics.widthPixels;

        choiceLayout3.getLayoutParams().height=choiceHeight1.intValue();
        choiceLayout3.getLayoutParams().width=displayMetrics.widthPixels;


        lastLayout.getLayoutParams().height=height.intValue();
        lastLayout.getLayoutParams().width=displayMetrics.widthPixels;

//        Picasso.with(getActivity())
//                .load(R.drawable.university_default)
//                .fit()
//                .into(imageViewLast);


        presenter = new HomePagePresenter(getContext(),this,new HomePageRepository());
//        //RecyclerViews
        universityRecyclerView = view.findViewById(R.id.university_recyclerView);
        coursesRecyclerView = view.findViewById(R.id.courses_recyclerView);
        shortTermRecyclerView = view.findViewById(R.id.st_recycler_view);
        ambassdorsRecyclerView = view.findViewById(R.id.am_recycler_view);
//
//
//
        //banner sliding images
        bannerImg1 = view.findViewById(R.id.banner1);
        bannerImg2 = view.findViewById(R.id.banner2);
        bannerImg3 = view.findViewById(R.id.banner3);
        //bannerImg4 = view.findViewById(R.id.banner4);
//
//        //square choice images
        choiceImg1 = view.findViewById(R.id.choice1);
        school = view.findViewById(R.id.school_img);
        recommender = view.findViewById(R.id.recomender);
        courses = view.findViewById(R.id.course_img);
        choiceImg5 = view.findViewById(R.id.choice5);
        universities = view.findViewById(R.id.uni_img);

        tv_viewMore_trendingUni=(TextView)view.findViewById(R.id.tv_viewmore_trendingUni);
        tv_viewMore_trendingCourses=(TextView)view.findViewById(R.id.tv_viewmore_trendingCourses);
        tv_shortTermCarrier=(TextView)view.findViewById(R.id.tv_viewmore_shortTermCarrier);
        tv_viewMore_ambassaddor=(TextView)view.findViewById(R.id.tv_viewmore_ambassadors);

        recommender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AptitudeActivity.class));
            }
        });
        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),TrendingCoursesActivity.class));
            }
        });
        universities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),TrendingUniversitiesActivity.class));
            }
        });
//
//
//
//banner images set
//        bannerImg1.setImageDrawable(getResources().getDrawable(R.drawable.banner_img));
//        bannerImg2.setImageDrawable(getResources().getDrawable(R.drawable.banner_img));
//        bannerImg3.setImageDrawable(getResources().getDrawable(R.drawable.banner_img));
//        bannerImg4.setImageDrawable(getResources().getDrawable(R.drawable.banner_img));
//        Picasso.with(getContext()).load("https://lh3.googleusercontent.com/iQDGkmP5rr2YkO2xttPM1jxyDIdcHoCGScat7t6uXueZiJNLTim5G7Is4lVw2CBtha8=h556")
//                .into(bannerImg1);
//        Picasso.with(getContext()).load("https://lh3.googleusercontent.com/iQDGkmP5rr2YkO2xttPM1jxyDIdcHoCGScat7t6uXueZiJNLTim5G7Is4lVw2CBtha8=h556")
//                .into(bannerImg2);
//        Picasso.with(getContext()).load("https://lh3.googleusercontent.com/iQDGkmP5rr2YkO2xttPM1jxyDIdcHoCGScat7t6uXueZiJNLTim5G7Is4lVw2CBtha8=h556")
//                .into(bannerImg3);
//        Picasso.with(getContext()).load("https://lh3.googleusercontent.com/iQDGkmP5rr2YkO2xttPM1jxyDIdcHoCGScat7t6uXueZiJNLTim5G7Is4lVw2CBtha8=h556")
//                .into(bannerImg4);
//
        //slider images
        HashMap<String, Integer> url_maps = new HashMap<String, Integer>();
        url_maps.put("TOP ENTRANCE EXAMS", R.drawable.scroll_1);
        url_maps.put("FIND THE BEST COLLEGES BASED ON YOUR CAREER INTEREST", R.drawable.scroll_2);
        url_maps.put("SCHOLARSHIP PROGRAMS",R.drawable.scroll_3);
        url_maps.put("TOP CAREER OPTIONS", R.drawable.scroll_4);
        url_maps.put("CAREER COUNSELING", R.drawable.scroll_5);


//        //university data

        UniversityData universityData1 = new UniversityData();
        universityData1.setImage("https://timesofindia.indiatimes.com/thumb/msid-59023402,width-400,resizemode-4/59023402.jpg");
        universityData1.setIconImage("https://www.reviewadda.com/assets/uploads/college/logo/LOGO_2922.png");
        universityData1.setLocation("Greater Noida, Uttar Pradesh");
        universityData1.setName("Sharda University");
        universityData1.setPhone("0120 457 0000");
        universityData1.setEnroll("https://www.sharda.ac.in/");
        ArrayList<Tags> tags = new ArrayList<>();

        Tags tag = new Tags();
        tag.setEntrance("Entrance");
        tag.setRating("5.0");
        tag.setType("Private");
        universityData1.setTags(tag);


        UniversityData universityData2 = new UniversityData();
        universityData2.setImage("https://timesofindia.indiatimes.com/photo/58944050.cms");
        universityData2.setIconImage("https://upload.wikimedia.org/wikipedia/en/thumb/a/a4/Indian_Institute_of_Technology_Delhi_logo.png/220px-Indian_Institute_of_Technology_Delhi_logo.png");
        universityData2.setLocation("Hauz Khas,Delhi");
        universityData2.setName("IIT Delhi");
        universityData2.setPhone("+91-11-2659 1701");
        universityData2.setEnroll("http://www.iitd.ac.in/");
        ArrayList<Tags> tags2 = new ArrayList<>();

        Tags tag2 = new Tags();
        tag2.setEntrance("Entrance");
        tag2.setRating("5.0");
        tag2.setType("Public");
        universityData2.setTags(tag2);

        UniversityData universityData3 = new UniversityData();
        universityData3.setImage("http://www.du.ac.in/du/uploads/images/arts_faculty.png");
        universityData3.setIconImage("https://upload.wikimedia.org/wikipedia/en/8/84/University_of_Delhi.png");
        universityData3.setLocation("North Campus,New Delhi");
        universityData3.setName("Universtiy Of Delhi");
        universityData3.setPhone("011-27006900");
        universityData3.setEnroll("http://www.du.ac.in/du/");
        ArrayList<Tags> tags3 = new ArrayList<>();

        Tags tag3 = new Tags();
        tag3.setEntrance("Merit");
        tag3.setRating("4.9");
        tag3.setType("Public");
        tags.add(tag3);
        universityData3.setTags(tag3);


        UniversityData universityData4 = new UniversityData();
        universityData4.setImage("https://images.static-collegedunia.com/public/college_data/images/campusimage/14980395041443100431BHU_NEW.jpg");
        universityData4.setIconImage("https://i1.rgstatic.net/ii/institution.image/AS%3A267464972144648%401440780026988_l");
        universityData4.setLocation("Varanasi, Uttar Pradesh");
        universityData4.setName("Banaras Hindu University ");
        universityData4.setPhone("0542-2368938");
        universityData4.setEnroll("http://www.bhu.ac.in/");
        ArrayList<Tags> tags4 = new ArrayList<>();

        Tags tag4 = new Tags();
        tag4.setEntrance("Entrance");
        tag4.setRating("4.8");
        tag4.setType("Public");
        tags.add(tag4);
        universityData4.setTags(tag4);

        UniversityData universityData5 = new UniversityData();
        universityData5.setImage("http://worldkings.org/Userfiles/Upload/images/Princeton%20University.jpg");
        universityData5.setIconImage("https://studyqa.com/media/upload/univers/840/51/uni_profile_84051.jpg");
        universityData5.setLocation("Princeton, NJ 08544, USA");
        universityData5.setName("Princeton University ");
        universityData5.setPhone("609-258-3000");
        universityData5.setEnroll("https://www.princeton.edu/");
        ArrayList<Tags> tags5 = new ArrayList<>();

        Tags tag5 = new Tags();
        tag5.setEntrance("Entrance");
        tag5.setRating("5.0");
        tag5.setType("Private");
        tags.add(tag5);
        universityData5.setTags(tag5);

        UniversityData universityData6 = new UniversityData();
        universityData6.setImage("https://www.imperial.ac.uk/ImageCropToolT4/imageTool/uploaded-images/IMPERIAL_ICL_ExhibRdEntrance-120--tojpeg_1417791202195_x1.jpg");
        universityData6.setIconImage("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ad/Imperial_College_London_crest.svg/1200px-Imperial_College_London_crest.svg.png");
        universityData6.setLocation("Kensington, London SW7 2AZ, UK");
        universityData6.setName("Imperial College London");
        universityData6.setPhone("+44 20 7589 5111");
        universityData6.setEnroll("https://www.imperial.ac.uk/");
        ArrayList<Tags> tags6 = new ArrayList<>();

        Tags tag6 = new Tags();
        tag6.setEntrance("Merit");
        tag6.setRating("5.0");
        tag6.setType("Public");
        tags.add(tag6);
        universityData6.setTags(tag6);


        UniversityData universityData7 = new UniversityData();
        universityData7.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/IISc_Main_Building.jpg/270px-IISc_Main_Building.jpg");
        universityData7.setIconImage("https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Indian_Institute_of_Science_logo.svg/1200px-Indian_Institute_of_Science_logo.svg.png");
        universityData7.setLocation("CV Raman Rd, Bengaluru, Karnataka");
        universityData7.setName("IISC Bombay");
        universityData7.setPhone(" +91 80 2293 2228");
        universityData7.setEnroll("http://www.iisc.ac.in/");
        ArrayList<Tags> tags7 = new ArrayList<>();

        Tags tag7 = new Tags();
        tag7.setEntrance("Entrance");
        tag7.setRating("4.9");
        tag7.setType("Public");
        tags.add(tag7);
        universityData7.setTags(tag7);


        UniversityData universityData8 = new UniversityData();
        universityData8.setImage("http://images.indianexpress.com/2016/09/amu-759.jpg");
        universityData8.setIconImage("https://upload.wikimedia.org/wikipedia/commons/9/9c/ALMU-logo.jpg");
        universityData8.setLocation("Aligarh, Uttar Pradesh");
        universityData8.setName("Aligarh Muslim University");
        universityData8.setPhone(" +91 80 2293 2228");
        universityData8.setEnroll("https://www.amu.ac.in/");
        ArrayList<Tags> tags8 = new ArrayList<>();

        Tags tag8 = new Tags();
        tag8.setEntrance("Merit");
        tag8.setRating("5.0");
        tag8.setType("Private");
        tags.add(tag8);
        universityData8.setTags(tag8);

        universityDatas.add(universityData1);
        universityDatas.add(universityData2);
        universityDatas.add(universityData3);
        universityDatas.add(universityData4);
        universityDatas.add(universityData5);
        universityDatas.add(universityData6);
        universityDatas.add(universityData7);
        universityDatas.add(universityData8);

        //course Data
        ShortTermCoursesData coursesData1 = new ShortTermCoursesData();
        coursesData1.setCourseName("MBA");
        coursesData1.setCourseImage("https://www.imperial.ac.uk/ImageCropToolT4/imageTool/uploaded-images/IMPERIAL_ICL_ExhibRdEntrance-120--tojpeg_1417791202195_x1.jpg");
        coursesData1.setRating("5.0");
        coursesData1.setType("Premiere");
        coursesData1.setDuration("2 Years");

        coursesDatas.add(coursesData1);

                ShortTermCoursesData coursesData2 = new ShortTermCoursesData();
        coursesData2.setCourseName("CA");
        coursesData2.setCourseImage("http://worldkings.org/Userfiles/Upload/images/Princeton%20University.jpg");
        coursesData2.setRating("5.0");
        coursesData2.setType("Professional");
        coursesData2.setDuration("5 Years");

        coursesDatas.add(coursesData2);

        ShortTermCoursesData coursesData3 = new ShortTermCoursesData();
        coursesData3.setCourseName("BA(Fashion Design)");
        coursesData3.setCourseImage("https://images.static-collegedunia.com/public/college_data/images/campusimage/14980395041443100431BHU_NEW.jpg");
        coursesData3.setRating("5.0");
        coursesData3.setType("Premiere");
        coursesData3.setDuration("3 Years");

        coursesDatas.add(coursesData3);

        ShortTermCoursesData coursesData4 = new ShortTermCoursesData();
        coursesData4.setCourseName("CMA");
        coursesData4.setCourseImage("http://www.du.ac.in/du/uploads/images/arts_faculty.png");
        coursesData4.setRating("5.0");
        coursesData4.setType("Professional");
        coursesData4.setDuration("5 Years");

        coursesDatas.add(coursesData4);

        ShortTermCoursesData coursesData5 = new ShortTermCoursesData();
        coursesData5.setCourseName("M.Sc. (Forensic Science)");
        coursesData5.setCourseImage("https://upload.wikimedia.org/wikipedia/en/thumb/a/a4/Indian_Institute_of_Technology_Delhi_logo.png/220px-Indian_Institute_of_Technology_Delhi_logo.png");
        coursesData5.setRating("5.0");
        coursesData5.setType("Premiere");
        coursesData5.setDuration("2 Years");

        coursesDatas.add(coursesData5);

        ShortTermCoursesData coursesData6 = new ShortTermCoursesData();
        coursesData6.setCourseName("Btech");
        coursesData6.setCourseImage("https://timesofindia.indiatimes.com/thumb/msid-59023402,width-400,resizemode-4/59023402.jpg");
        coursesData6.setRating("5.0");
        coursesData6.setType("Premiere");
        coursesData6.setDuration("4 Years");

        coursesDatas.add(coursesData6);

        ShortTermCoursesData coursesData7 = new ShortTermCoursesData();
        coursesData7.setCourseName("Mtech");
        coursesData7.setCourseImage("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/IISc_Main_Building.jpg/270px-IISc_Main_Building.jpg");
        coursesData7.setRating("5.0");
        coursesData7.setType("Premiere");
        coursesData7.setDuration("2 Years");

        coursesDatas.add(coursesData7);

        ShortTermCoursesData coursesData8 = new ShortTermCoursesData();
        coursesData8.setCourseName("BCA");
        coursesData8.setCourseImage("http://images.indianexpress.com/2016/09/amu-759.jpg");
        coursesData8.setRating("5.0");
        coursesData8.setType("Professional");
        coursesData8.setDuration("3 Years");

        coursesDatas.add(coursesData8);

        ShortTermCoursesData coursesData9 = new ShortTermCoursesData();
        coursesData9.setCourseName("LLB");
        coursesData9.setCourseImage("http://professionalcourse.in/images/llb.jpg");
        coursesData9.setRating("5.0");
        coursesData9.setType("Professional");
        coursesData9.setDuration("3 Years");

        coursesDatas.add(coursesData9);

//
        // Short Term courses data
        ShortTermCoursesData shortTermCoursesData1 = new ShortTermCoursesData();
        shortTermCoursesData1.setCourseImage("https://blog.coursera.org/wp-content/uploads/2018/01/Blog-3.png");
        shortTermCoursesData1.setCourseName("Google IT Support Professional Certificate");
        shortTermCoursesData1.setDuration("3 Months");
        shortTermCoursesData1.setType("Boost 20%");
        shortTermCoursesData1.setRating("5.0");
        shortTermCoursesData1.setVendor("Coursera");
        shortTermCoursesData1.setEnroll("https://www.coursera.org/specializations/google-it-support");

        ShortTermCoursesData shortTermCoursesData2 = new ShortTermCoursesData();
        shortTermCoursesData2.setCourseImage("https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://coursera-course-photos.s3.amazonaws.com/3f/08f9900d1311e48743bfe1d327ce7a/CourseTrackLogo.jpg");
        shortTermCoursesData2.setCourseName("Data Science Specialization");
        shortTermCoursesData2.setDuration("2 Months");
        shortTermCoursesData2.setType("Boost 20%");
        shortTermCoursesData2.setRating("4.8");
        shortTermCoursesData2.setVendor("Coursera");
        shortTermCoursesData2.setEnroll(" https://www.coursera.org/specializations/jhu-data-science");

        ShortTermCoursesData shortTermCoursesData3 = new ShortTermCoursesData();
        shortTermCoursesData3.setCourseImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDYfAQeVLU2QUx3yDBMgEzYAQoXcwqUNKbANoe_7g7FwHcpYW45g");
        shortTermCoursesData3.setCourseName("Neural Networks Foundation");
        shortTermCoursesData3.setDuration("3 Months");
        shortTermCoursesData3.setType("Boost 20%");
        shortTermCoursesData3.setRating("5.0");
        shortTermCoursesData3.setVendor("Udacity");
        shortTermCoursesData3.setEnroll("https://in.udacity.com/course/neural-networks-foundation-nanodegree--nd101-innl");

        ShortTermCoursesData shortTermCoursesData4 = new ShortTermCoursesData();
        shortTermCoursesData4.setCourseImage("https://blogs.nvidia.com/wp-content/uploads/2016/11/AIPodcast-key.jpg");
            shortTermCoursesData4.setCourseName("Artificial Intelligence Foundation");
        shortTermCoursesData4.setDuration("4 Months");
        shortTermCoursesData4.setType("Boost 20%");
        shortTermCoursesData4.setRating("4.5");
        shortTermCoursesData4.setVendor("Udacity");
        shortTermCoursesData4.setEnroll(" https://in.udacity.com/course/artificial-intelligence-foundation-nanodegree--nd002-inaif");


        shortTermCoursesDatas.add(shortTermCoursesData1);
        shortTermCoursesDatas.add(shortTermCoursesData2);
        shortTermCoursesDatas.add(shortTermCoursesData3);
        shortTermCoursesDatas.add(shortTermCoursesData4);

        //ambassdors data

        AmbassdorsData ambassdorsData1 = new AmbassdorsData();
        ambassdorsData1.setAmbassdorName("Gaurav Gupta");
        ambassdorsData1.setBaca("BA");
        ambassdorsData1.setLocation("Delhi");
        ambassdorsData1.setUniversity("FMS");
        ambassdorsData1.setLinkedIn("https://www.linkedin.com/in/gauravright85/");

        AmbassdorsData ambassdorsData2 = new AmbassdorsData();
        ambassdorsData2.setAmbassdorName("Rishabh Jain");
        ambassdorsData2.setBaca("BA");
        ambassdorsData2.setLocation("New Delhi");
        ambassdorsData2.setUniversity("Delhi University");
        ambassdorsData2.setLinkedIn("https://www.linkedin.com/in/rishabh-jain-b4b191158/");

        AmbassdorsData ambassdorsData3 = new AmbassdorsData();
        ambassdorsData3.setAmbassdorName("Paras Jain");
        ambassdorsData3.setBaca("BA");
        ambassdorsData3.setLocation("Telangana");
        ambassdorsData3.setUniversity("NIT Warangal");
        ambassdorsData3.setLinkedIn("https://www.linkedin.com/in/parasjaincs/");

        AmbassdorsData ambassdorsData4 = new AmbassdorsData();
        ambassdorsData4.setAmbassdorName("Nikhil Jain");
        ambassdorsData4.setBaca("BA");
        ambassdorsData4.setLocation("Ghaziabad");
        ambassdorsData4.setUniversity("Institute of Management Studies");
        ambassdorsData4.setLinkedIn("https://www.linkedin.com/in/jain0nikhil/");

        AmbassdorsData ambassdorsData5 = new AmbassdorsData();
        ambassdorsData5.setAmbassdorName("Yash Shah");
        ambassdorsData5.setBaca("BA");
        ambassdorsData5.setLocation("New Delhi");
        ambassdorsData5.setUniversity("Hansraj College");

        AmbassdorsData ambassdorsData6 = new AmbassdorsData();
        ambassdorsData6.setAmbassdorName("Rahul Ray");
        ambassdorsData6.setBaca("CC");
        ambassdorsData6.setLocation("New Delhi");
        ambassdorsData6.setUniversity("Keshav Mahavidyalaya");
        ambassdorsData6.setLinkedIn("https://www.linkedin.com/in/rahul-ray-1b6748151/");

        AmbassdorsData ambassdorsData7 = new AmbassdorsData();
        ambassdorsData7.setAmbassdorName("Praveen Jangra");
        ambassdorsData7.setBaca("CC");
        ambassdorsData7.setLocation("New Delhi");
        ambassdorsData7.setUniversity("Keshav Mahavidyalaya");
        ambassdorsData7.setLinkedIn("https://www.linkedin.com/in/praveen-jangra-b5608314b/");

        AmbassdorsData ambassdorsData8 = new AmbassdorsData();
        ambassdorsData8.setAmbassdorName("Preksha Sachdeva");
        ambassdorsData8.setBaca("CC");
        ambassdorsData8.setLocation("New Delhi");
        ambassdorsData8.setUniversity("Keshav Mahavidyalaya");
        ambassdorsData8.setLinkedIn("https://www.linkedin.com/in/preksha-sachdeva-bb5475138/");


        AmbassdorsData ambassdorsData9 = new AmbassdorsData();
        ambassdorsData9.setAmbassdorName("Prashant Gupta");
        ambassdorsData9.setBaca("BA");
        ambassdorsData9.setLocation("England");
        ambassdorsData9.setUniversity("Nottingham Business school");
        ambassdorsData9.setLinkedIn("https://www.linkedin.com/in/prashant-gupta-bb265869/");


        ambassdorsDatas.add(ambassdorsData1);
        ambassdorsDatas.add(ambassdorsData9);
        ambassdorsDatas.add(ambassdorsData2);
        ambassdorsDatas.add(ambassdorsData3);
        ambassdorsDatas.add(ambassdorsData4);
        ambassdorsDatas.add(ambassdorsData5);
        ambassdorsDatas.add(ambassdorsData6);
        ambassdorsDatas.add(ambassdorsData7);
        ambassdorsDatas.add(ambassdorsData8);
//
        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            imageSlider.addSlider(textSliderView);

        }
//setting recycler views

        presenter.getCourses();
        presenter.getShortTermCourses();
        presenter.getUniversities();
        presenter.getCampaigners();
        //setUniversitiesRecyclerView();
        //setCoursesRecyclerView();
        //setShortTermCoursesRecyclerView();
        //setAmbassdorsRecyclerView();

        tv_viewMore_trendingUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(), TrendingUniversitiesActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("UniversityData",universityDatas);
                intent.putExtra("Bundle",bundle);
                startActivity(intent);
            }
        });

        tv_viewMore_trendingCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(), TrendingCoursesActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("ShortTermCoursesData",coursesDatas);
                intent.putExtra("Bundle",bundle);
                startActivity(intent);
            }
        });

        tv_shortTermCarrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(), ShortTermCoursesActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("Urls",shortTermCoursesDatas);
                intent.putExtra("Bundle",bundle);
                startActivity(intent);
            }
        });

        tv_viewMore_ambassaddor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity().getApplicationContext(), AmbassdorsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("AmbassadorsData",ambassdorsDatas);
                intent.putExtra("Bundle",bundle);
                startActivity(intent);
            }
        });

    }


    @Override
    public void setUniversitiesRecyclerView(List<University> universities) {
        universityRecyclerView.setHasFixedSize(true);
        universityRecyclerView.removeAllViews();
        universityRecyclerView.setNestedScrollingEnabled(false);
        universityRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        universityRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        adapter =
                new RecommendedUniAdapter(getContext(),universities,displayMetrics){
                    @Override
                    public void like(int id) {
                        presenter.likeUniversity(id);
                    }
                };
        universityRecyclerView.setAdapter(adapter);
        universityRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        adapter.notifyDataSetChanged();

    }

    @Override
    public void setCoursesRecyclerView(List<Course> courses) {
        coursesRecyclerView.setHasFixedSize(true);
        coursesRecyclerView.removeAllViews();
        coursesRecyclerView.setNestedScrollingEnabled(false);
        coursesRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        coursesRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        courseAdapter =
                new RecommendedCourseAdapter(MyApplication.getInstance().prefManager,getContext(),courses){
                    @Override
                    public void like(int id) {
                        presenter.likeCourse(id);
                    }
                };

        coursesRecyclerView.setAdapter(courseAdapter);
        coursesRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        courseAdapter.notifyDataSetChanged();
    }

    @Override
    public void setShortTermCoursesRecyclerView(List<ShortTermCourse> shortTermCourses) {
        shortTermRecyclerView.setHasFixedSize(true);
        shortTermRecyclerView.removeAllViews();
        shortTermRecyclerView.setNestedScrollingEnabled(false);
        shortTermRecyclerView.removeAllViewsInLayout();
        StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        shortTermRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
        shortTermAdapter = new ShortTermAdapter(new PrefManager(getContext()),getContext(),shortTermCourses){
            @Override
            public void like(int id) {
                presenter.likeShortTermCourse(id);
            }
        };
        shortTermRecyclerView.setAdapter(shortTermAdapter);
        shortTermRecyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);

        shortTermAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCampaignersRecyclerView(List<Campaigners> campaigners){
        ambassdorsRecyclerView.setHasFixedSize(true);
        ambassdorsRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        ambassdorsRecyclerView.setLayoutManager(linearLayoutManager);
        ambassdorsRecyclerViewAdapter = new AmbassdorsRecyclerViewAdapter(getActivity(),campaigners,displayMetrics){
            @Override
            public void like(int id) {
                presenter.likeCampaigners(id);
            }
        };
        ambassdorsRecyclerView.setAdapter(ambassdorsRecyclerViewAdapter);
        ambassdorsRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static UniversitiesFragment newInstance() {
        return new UniversitiesFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_universities;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        view = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }
}