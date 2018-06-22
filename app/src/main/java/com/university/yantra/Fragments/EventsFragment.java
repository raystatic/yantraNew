package com.university.yantra.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.university.yantra.Activities.FiltersActivity;
import com.university.yantra.Activities.WishlistActivity;
import com.university.yantra.R;
import com.university.yantra.events.Tab4_Events_Adapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

/**
 * Created by rahul on 18/2/18.
 */

public class EventsFragment extends android.support.v4.app.Fragment implements BaseSliderView.OnSliderClickListener{

    ViewPager viewPager;
    LinearLayout linearLayout;
    int dotscount;
    FloatingActionMenu menuRed;
    ImageView[] dots;
    ArrayList<String> months;
    //Tab4_RecyclerViewAdapter recyclerViewAdapter;
    Tab4_Events_Adapter events_adapter;
    RecyclerView recyclerView, recyclerView2;

    ArrayList<String> titles,dates,timeAM,timePM,labels,orgNames,addrs,distances;
    ArrayList<Integer> images;
    ArrayList<String> imageUrls;
    private SliderLayout imageSlider;
    HorizontalCalendar horizontalCalendar;
    RelativeLayout relativeLayout;

    public EventsFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab4_fragment,container,false);

        imageSlider = view.findViewById(R.id.slider);
        relativeLayout=(RelativeLayout)view.findViewById(R.id.rel_events_recyclerview);
        //recyclerView=(RecyclerView)view.findViewById(R.id.calendarView);
        recyclerView2=(RecyclerView)view.findViewById(R.id.recyclerview_tab4_events);
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Sharda University", "http://college4u.in/wp-content/uploads/2017/02/sharda1-830x307.jpg");
        url_maps.put("SRM University", "http://www.srmuniv.ac.in/sites/all/themes/srmuniversity/main_layout/images/menu-img2.jpg");
        url_maps.put("Amity University", "https://educationiconnect.com/wp-content/uploads/2015/07/Amity-university-Distance-education-mba-online.jpg");
        url_maps.put("Stanford University", "https://www.timeshighereducation.com/sites/default/files/styles/the_breaking_news_image_style/public/stanford-university-best-universities-in-the-united-states-2016.jpg");
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
        menuRed = (FloatingActionMenu) view.findViewById(R.id.menu_red);

        // for floating action button
        //menuRed.setBackground(new ColorDrawable(getResources().getColor(R.color.filter_blue)));
        // menuRed.setBackgroundColor(getResources().getColor(R.color.filter_blue));
        menuRed.setMenuButtonColorNormal(getResources().getColor(R.color.lightpink));
        menuRed.setMenuButtonColorPressed(getResources().getColor(R.color.lightpink));

        final FloatingActionButton programFab1 = new FloatingActionButton(getContext());
        programFab1.setButtonSize(FloatingActionButton.SIZE_MINI);
        programFab1.setLabelText("Filters");
        programFab1.setImageResource(R.drawable.ic_action_edit2);
        menuRed.addMenuButton(programFab1);
        programFab1.setColorNormal(getResources().getColor(R.color.lightpink));
        programFab1.setColorPressed(getResources().getColor(R.color.lightpink));
        programFab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programFab1.setLabelColors(ContextCompat.getColor(getActivity(), R.color.grey),
                        ContextCompat.getColor(getActivity(), R.color.light_grey),
                        ContextCompat.getColor(getActivity(), R.color.white_transparent));
                programFab1.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));

                startActivity(new Intent(getActivity(), FiltersActivity.class));

                menuRed.close(true);

            }
        });
        final FloatingActionButton programFab2 = new FloatingActionButton(getContext());
        programFab2.setButtonSize(FloatingActionButton.SIZE_MINI);
        programFab2.setLabelText("Wishlist");
        programFab2.setImageResource(R.drawable.ic_action_heart2);
        menuRed.addMenuButton(programFab2);
        programFab2.setColorNormal(getResources().getColor(R.color.lightpink));
        programFab2.setColorPressed(getResources().getColor(R.color.lightpink));
        programFab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programFab2.setLabelColors(ContextCompat.getColor(getActivity(), R.color.grey),
                        ContextCompat.getColor(getActivity(), R.color.light_grey),
                        ContextCompat.getColor(getActivity(), R.color.white_transparent));
                programFab2.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));

                startActivity(new Intent(getActivity(), WishlistActivity.class));

                menuRed.close(true);

            }
        });


        months=new ArrayList<>();
        titles=new ArrayList<>();
        dates=new ArrayList<>();
        timeAM=new ArrayList<>();
        timePM=new ArrayList<>();
        labels=new ArrayList<>();
        orgNames=new ArrayList<>();
        addrs=new ArrayList<>();
        distances=new ArrayList<>();
        images=new ArrayList<>();

        images.add(R.drawable.im1);
        images.add(R.drawable.im2);
        images.add(R.drawable.im3);
        images.add(R.drawable.im4);
        images.add(R.drawable.im5);
        images.add(R.drawable.im6);

        imageUrls=new ArrayList<>();

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

/* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        horizontalCalendar = new HorizontalCalendar.Builder(view, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                relativeLayout.setVisibility(View.VISIBLE);
                Log.d("time",date.getTime().toString());
                Log.d("position_date",position+"");
                String mon=date.getTime().toString().substring(4,7);
                String cal_date=date.getTime().toString().substring(8,10);
                Log.d("mon",mon);
                Log.d("cal_date",cal_date);
                if (mon.equals("Jun"))
                {
                    if (cal_date.equals("26"))
                    {
                        titles.clear();
                        dates.clear();
                        months.clear();
                        timeAM.clear();
                        timePM.clear();
                        labels.clear();
                        addrs.clear();
                        distances.clear();
                        imageUrls.clear();
                        orgNames.clear();

                        titles.add(0,"CIAPC");
                        titles.add(1,"Geologist Exam");

                        dates.add(0,"26");
                        dates.add(1,"26");

                        months.add(0,"Feb");
                        months.add(1,"Feb");

                        timeAM.add(0,"9:00");
                        timeAM.add(1,"10:00");

                        timePM.add(0,"2:00");
                        timePM.add(1,"3:00");

                        labels.add(0,"Summit");
                        labels.add(1,"Exam");

                        addrs.add(0,"Connauhgt Place, New Delhi");
                        addrs.add(1,"Rajouri Garden, New Delhi");

                        distances.add(0,"2.89");
                        distances.add(1,"3.36");

                        orgNames.add(0,"L J Institute");
                        orgNames.add(1,"Alliance College of Law");

                        imageUrls.add(0,"https://i.ndtvimg.com/i/2016-09/computer-generic_650x400_41474385295.jpg");
                        imageUrls.add(1,"https://i.ndtvimg.com/i/2016-09/computer-generic_650x400_41474385295.jpg");

                        setEventsCardViewsAdapter(titles,dates,timeAM,timePM,labels,orgNames,addrs,distances,imageUrls,months);
                    }
                    else if (cal_date.equals("28"))
                    {
                        titles.clear();
                        dates.clear();
                        months.clear();
                        timeAM.clear();
                        timePM.clear();
                        labels.clear();
                        addrs.clear();
                        distances.clear();
                        imageUrls.clear();
                        orgNames.clear();

                        titles.add(0,"Combined Defence Academy Exam");
                        titles.add(1,"ICIEA-2018");

                        dates.add(0,"28");
                        dates.add(1,"28");

                        months.add(0,"Feb");
                        months.add(1,"Feb");

                        timeAM.add(0,"11:00");
                        timeAM.add(1,"12:00");

                        timePM.add(0,"12:00");
                        timePM.add(1,"1:00");

                        labels.add(0,"Exam");
                        labels.add(1,"Summit");

                        addrs.add(0,"Subhash Nagar, New Delhi");
                        addrs.add(1,"Dwarka, New Delhi");

                        distances.add(0,"4.51");
                        distances.add(1,"5.59");

                        orgNames.add(0,"Indus School of Engineering");
                        orgNames.add(1,"NSIT");

                        imageUrls.add(0,"http://news.bbcimg.co.uk/media/images/53219000/jpg/_53219139_alisonwhite6.jpg");
                        imageUrls.add(1,"https://i.ndtvimg.com/i/2016-09/computer-generic_650x400_41474385295.jpg");

                        setEventsCardViewsAdapter(titles,dates,timeAM,timePM,labels,orgNames,addrs,distances,imageUrls,months);
                    }
                    else
                    {
                        clear();
                        //showDialog("No events on this day!");
                    }

                }
                else if (mon.equals("Jul"))
                {
                    if (cal_date.equals("21"))
                    {
                        titles.clear();
                        dates.clear();
                        months.clear();
                        timeAM.clear();
                        timePM.clear();
                        labels.clear();
                        addrs.clear();
                        distances.clear();
                        imageUrls.clear();
                        orgNames.clear();

                        titles.add(0,"Central Police Force Exam");
                        titles.add(1,"ICETSB");

                        dates.add(0,"21");
                        dates.add(1,"21");

                        months.add(0,"Mar");
                        months.add(1,"Mar");

                        timeAM.add(0,"10:00");
                        timeAM.add(1,"9:00");

                        timePM.add(0,"1:00");
                        timePM.add(1,"12:00");

                        labels.add(0,"Exam");
                        labels.add(1,"Summit");

                        addrs.add(0,"Hauz Khaas, New Delhi");
                        addrs.add(1,"Moti Nagar, New Delhi");

                        distances.add(0,"6.46");
                        distances.add(1,"8.65");

                        orgNames.add(0,"Mount Abu Public School");
                        orgNames.add(1,"ISC College");

                        imageUrls.add(0,"https://www.biotecnika.org/wp-content/uploads/2016/04/indian-forest-service-final-results-declared56c17614d428a.jpg");
                        imageUrls.add(1,"https://i.ndtvimg.com/i/2016-09/computer-generic_650x400_41474385295.jpg");

                        setEventsCardViewsAdapter(titles,dates,timeAM,timePM,labels,orgNames,addrs,distances,imageUrls,months);
                    }
                    else if (cal_date.equals("24"))
                    {
                        titles.clear();
                        dates.clear();
                        months.clear();
                        timeAM.clear();
                        timePM.clear();
                        labels.clear();
                        addrs.clear();
                        distances.clear();
                        imageUrls.clear();
                        orgNames.clear();


                        titles.add(0,"SALN Seminar");
                        titles.add(1,"RBI Grade B Exam");

                        dates.add(0,"24");
                        dates.add(1,"24");

                        months.add(0,"Mar");
                        months.add(1,"Mar");

                        timeAM.add(0,"11:00");
                        timeAM.add(1,"12:00");

                        timePM.add(0,"2:00");
                        timePM.add(1,"3:00");

                        labels.add(0,"Summit");
                        labels.add(1,"Exam");

                        addrs.add(0,"Kirti Nagar, New Delhi");
                        addrs.add(1,"Pitampura, New Delhi");

                        distances.add(0,"2.34");
                        distances.add(1,"3.67");


                        orgNames.add(0,"Hotel Florence");
                        orgNames.add(1,"Bharti Public school");

                        imageUrls.add(0,"https://www.e-architect.co.uk/images/jpgs/wales/cardiff-vale-college-b040313-2.jpg");
                        imageUrls.add(1,"https://www.gcccd.edu/news/images/studentservices.jpg");

                        setEventsCardViewsAdapter(titles,dates,timeAM,timePM,labels,orgNames,addrs,distances,imageUrls,months);
                    }
                    else if (cal_date.equals("26"))
                    {
                        titles.clear();
                        dates.clear();
                        months.clear();
                        timeAM.clear();
                        timePM.clear();
                        labels.clear();
                        addrs.clear();
                        distances.clear();
                        imageUrls.clear();
                        orgNames.clear();

                        titles.add(0,"SBI PO Exams");
                        titles.add(1,"IBPS PO Exam");

                        dates.add(0,"26");
                        dates.add(1,"26");

                        months.add(0,"Mar");
                        months.add(1,"Mar");

                        timeAM.add(0,"9:00");
                        timeAM.add(1,"10:00");

                        timePM.add(0,"12:00");
                        timePM.add(1,"1:00");

                        labels.add(0,"Exam");
                        labels.add(1,"Exam");

                        addrs.add(0,"Rajendra Place, New Delhi");
                        addrs.add(1,"Tilak Nagar, New Delhi");

                        distances.add(0,"9.56");
                        distances.add(1,"4.37");

                        orgNames.add(0,"Sagar Institute of Research And Technology");
                        orgNames.add(1,"Stanley College of Eng & Tech");

                        imageUrls.add(0,"https://i.pinimg.com/736x/ff/e6/12/ffe612037402f9040e85e6e2fdfb531d--best-colleges-modern-buildings.jpg");
                        imageUrls.add(1,"http://www.slate.com/content/dam/slate/blogs/the_eye/2013/10/18/131017_EYE_YaleArchBldg.jpg.CROP.original-original.jpg");


                        setEventsCardViewsAdapter(titles,dates,timeAM,timePM,labels,orgNames,addrs,distances,imageUrls,months);
                    }
                    else
                    {
                       clear();
                        //showDialog("No events on this day!");
                    }
                }
                else
                {
                    clear();
                    //showDialog("No events in this month!");
                }

                setEventsCardViews();

            }
        });


//
//        recyclerViewAdapter=new Tab4_RecyclerViewAdapter(getActivity().getApplicationContext(),months);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    public void setEventsCardViewsAdapter(ArrayList<String> titles1,ArrayList<String> dates1,ArrayList<String> timeAM1,ArrayList<String> timePM1,ArrayList<String> labels1,ArrayList<String> orgNames1,ArrayList<String> addrs1,ArrayList<String> distances1,ArrayList<String> imageUrls1,ArrayList<String> months)
    {
        events_adapter=new Tab4_Events_Adapter(getActivity().getApplicationContext(),titles1,dates1,timeAM1,timePM1,labels1,orgNames1,addrs1,distances1,imageUrls1,months);
    }
    public void setEventsCardViews()
    {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(events_adapter);
        recyclerView2.hasFixedSize();
    }

    public void showDialog(String text)
    {
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(false)
                .setMessage(text)
                .setTitle("Note")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        horizontalCalendar.refresh();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
        relativeLayout.setVisibility(View.GONE);
    }
    public void clear()
    {
        titles.clear();
        dates.clear();
        months.clear();
        timeAM.clear();
        timePM.clear();
        labels.clear();
        addrs.clear();
        distances.clear();
        imageUrls.clear();
        orgNames.clear();
    //    recyclerView2.getAdapter().notifyDataSetChanged();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity().getApplicationContext())
        {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(events_adapter);
        recyclerView2.hasFixedSize();
    }

}
