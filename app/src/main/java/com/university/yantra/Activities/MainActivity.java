package com.university.yantra.Activities;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.squareup.picasso.Picasso;
import com.university.yantra.BaseActivity;
import com.university.yantra.HelpDesk.HelpDeskActivity;
import com.university.yantra.R;
import com.university.yantra.bottomfragments.ProfileFragment;

import com.university.yantra.adapters.BottomNavigationAdapter;
import com.university.yantra.apiservices.responses.AuthenticationResponse;
import com.university.yantra.common.CustomTypefaceSpan;
import com.university.yantra.common.MyApplication;
import com.university.yantra.listeners.OnUserReceivedListener;
import com.university.yantra.listeners.auth.OnAuthenticatedListener;
import com.university.yantra.models.User;
import com.university.yantra.presenters.AuthenticationPresenter;
import com.university.yantra.presenters.CurrentUserPresenter;
import com.university.yantra.viewactions.BaseViewAction;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements ProfileFragment.OnFragmentInteractionListener,OnAuthenticatedListener,BaseViewAction, NavigationView.OnNavigationItemSelectedListener, OnUserReceivedListener, View.OnClickListener{

    AHBottomNavigation bottomNavigation;
    private AHBottomNavigationViewPager viewPager;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private int[] tabColors;
    private  NavigationView navigationView;
    private BottomNavigationAdapter adapter;
    private boolean useMenuResource = true;
    TextView username;
    int counter=0;
    TextView email;
    private Handler handler = new Handler();
    private android.support.v4.app.Fragment currentFragment;

    private TextView login;
    private TextView logout;
    CircleImageView circleImageView;
    private AHBottomNavigationAdapter navigationAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    ShowcaseView showcaseView;
    ImageView navNotif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navNotif = findViewById(R.id.notif);
        navNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainActivity.this,NotificationActivity.class);
                j.putExtra("type",1+"");
                j.putExtra("search","sharda");
                startActivity(j);
            }
        });
        changeStatusBarColor();
        ImageView search = findViewById(R.id.phone);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.white));

        bottomNavigation.setBehaviorTranslationEnabled(false);

        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setColoredModeColors(getResources().getColor(R.color.filter_blue),getResources().getColor(R.color.filter_blue));

        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        // Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);


        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);
        boolean enabledTranslucentNavigation = getSharedPreferences("shared", MODE_PRIVATE)
                .getBoolean("translucentNavigation", false);
        setTheme(enabledTranslucentNavigation ? R.style.AppTheme_TranslucentNavigation : R.style.AppTheme);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }

        if (prefManager.isFirstTimeLaunch())
        {
            learnAppFeatures();

        }

        initUI();
        if(MyApplication.getInstance().prefManager.isFirstTimeMainLaunch()){

                //Toast.makeText(this,"hello world",Toast.LENGTH_LONG).show();
                showcaseView = new ShowcaseView.Builder(this)
                        .setTarget(new ViewTarget(findViewById(R.id.feature2)))
                        .setOnClickListener(MainActivity.this)
                        .setStyle(R.style.CustomShowcaseTheme4)
                        .setContentTitle("Home")
                        .setContentText("You will all the suggestions for courses and colleges in yantra")
                        .build();

                showcaseView.setButtonText(getString(R.string.next));
                //showcaseViewBuilder = ShowcaseViewBuilder.init(this);
                bottomNavigation.setCurrentItem(1);
                MyApplication.getInstance().prefManager.setFirstTimeMainLaunch(false);

        }
        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (currentFragment == null) {
                    //currentFragment = adapter.getCurrentFragment();
                }

                if (wasSelected) {
                    //currentFragment.refresh();

                    return true;
                }

                if (currentFragment != null) {
                    //currentFragment.willBeHidden();
                }

                viewPager.setCurrentItem(position, false);

                currentFragment = adapter.getCurrentFragment();
                //currentFragment.willBeDisplayed();
                updateBottomNavigationItems(true);

                currentFragment = adapter.getCurrentFragment();
                //currentFragment.willBeDisplayed();
                updateBottomNavigationItems(true);


                return true;
            }
        });

        checkAndRequestPermissions();
        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override public void onPositionChange(int y) {
                // Manage the new y position
            }
        });

//        bottomNavigationItems.add(item1);
//        bottomNavigationItems.add(item2);
//        bottomNavigationItems.add(item3);
//        bottomNavigation.addItems(bottomNavigationItems);
        tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
        navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_3);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);


        //get current user details from server




    }
    private  boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);

        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int readPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }

        if(writePermission != PackageManager.PERMISSION_GRANTED)
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(readPermission != PackageManager.PERMISSION_GRANTED)
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }

        return true;
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.filter_blue));
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void hideToolbar() {
        getSupportActionBar().hide();
    }

    private void initUI() {

        login = (TextView)navigationView.getHeaderView(0). findViewById(R.id.loginButton);
        logout = (TextView)navigationView.getHeaderView(0).findViewById(R.id.logoutButton);
        circleImageView = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.circle_image_view);
        if(!MyApplication.getInstance().prefManager.isLoggedIn()){
            circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile_default));
        }
        circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile_default));
        username =  (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_username);
        email = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_email);
        if(MyApplication.getInstance().prefManager.isLoggedIn()){
            new CurrentUserPresenter(this, this).getCurrentUser();

            logout.setVisibility(View.VISIBLE);
            login.setVisibility(View.GONE);
            if(MyApplication.getInstance().prefManager.getString(LoginActivity.FB_LOGIN)!=null&&
                    MyApplication.getInstance().prefManager.getString(LoginActivity.FB_PROFILE)!=null){
               // showMessage("true");
                if(MyApplication.getInstance().prefManager.getString(LoginActivity.FB_LOGIN).equals("true")){
                   // showMessage("profile"+MyApplication.getInstance().prefManager.getString(LoginActivity.FB_PROFILE) +"");
                    Picasso.with(this).load("https://graph.facebook.com/" + MyApplication.getInstance().prefManager.getString(LoginActivity.FB_PROFILE) + "/picture?type=large").
                            error(R.drawable.ic_profile_default).into(circleImageView);

                }

            }

            else{
                //handle here for google login and default login

                Picasso.with(getApplicationContext()).load(R.drawable.ic_profile_default).into(circleImageView);

            }





        }
        else{
            logout.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
            Picasso.with(getApplicationContext()).load(R.drawable.ic_profile_default).into(circleImageView);
            username.setVisibility(View.VISIBLE);
            username.setText("Hi There");
            email.setText("");

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
                //logout.setVisibility(View.VISIBLE);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(getApplicationContext()).load(R.drawable.ic_profile_default).into(circleImageView);
                AuthenticationPresenter presenter = new AuthenticationPresenter(MainActivity.this,MainActivity.this);
                presenter.logout();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//
//            }
//        };
//        mDrawerToggle.syncState();
//
//
//        drawer.setDrawerListener(mDrawerToggle);


        //bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);



        if (useMenuResource)
        {
            tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
            navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_3);
            navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);
        }
        else
        {  AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_search, R.color.color_tab_1);
            AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_home, R.color.color_tab_2);
            AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_profile, R.color.color_tab_3);


            bottomNavigationItems.add(item1);
            bottomNavigationItems.add(item2);
            bottomNavigationItems.add(item3);
            bottomNavigation.addItems(bottomNavigationItems);
        }

        bottomNavigation.setTranslucentNavigationEnabled(true);
        bottomNavigation.setCurrentItem(0);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (currentFragment == null) {
                    //currentFragment = adapter.getCurrentFragment();
                }

                if (wasSelected) {
                    //currentFragment.refresh();

                    return true;
                }

                if (currentFragment != null) {
                    //currentFragment.willBeHidden();
                }

                viewPager.setCurrentItem(position, false);

                currentFragment = adapter.getCurrentFragment();
                //currentFragment.willBeDisplayed();
                updateBottomNavigationItems(false);

                currentFragment = adapter.getCurrentFragment();
                //currentFragment.willBeDisplayed();
                updateBottomNavigationItems(false);


                return true;
            }
        });

        viewPager.setOffscreenPageLimit(4);
        adapter = new BottomNavigationAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);

        currentFragment = adapter.getCurrentFragment();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Setting custom colors for notification
                AHNotification notification = new AHNotification.Builder()
                        .setText(":)")
                        .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.blue))
                        .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color_clubs))
                        .build();
                //bottomNavigation.setNotification(notification, 1);
                Snackbar.make(bottomNavigation, "Welcome to Yantra",
                        Snackbar.LENGTH_SHORT).show();

            }
        }, 3000);

        bottomNavigation.setBehaviorTranslationEnabled(false);




        //login logout functionality




    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
    public void updateBottomNavigationItems(boolean addItems) {

        if (useMenuResource) {
            if (addItems) {
                //navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_5);
                navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);
                //bottomNavigation.setNotification("1", 3);
            } else {
                navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_3);
                navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);
            }
            return;
        }


        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Aurora",
                R.drawable.ic_search,
                R.color.color_tab_1);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Chats",
                R.drawable.ic_home,
                R.color.color_tab_3);

        if (addItems) {
            bottomNavigation.addItem(item4);
            bottomNavigation.addItem(item5);
            //bottomNavigation.setNotification("1", 3);
        } else {
            bottomNavigation.removeAllItems();
            bottomNavigation.addItems(bottomNavigationItems);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.analysis){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    if(MyApplication.getInstance().prefManager.isLoggedIn())
//                        startActivity(new Intent(MainActivity.this, NotificationActivity.class));
//                    else{
//                        showMessage("Login To View Notifications");
//                        startLoginActivity();
//                    }

                    Intent i = new Intent(MainActivity.this, AptitudeActivity.class);
                    startActivity(i);

                }
            }, 200);
        }
        if(id == R.id.notif){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    if(MyApplication.getInstance().prefManager.isLoggedIn())
//                        startActivity(new Intent(MainActivity.this, NotificationActivity.class));
//                    else{
//                        showMessage("Login To View Notifications");
//                        startLoginActivity();
//                    }

                    startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                }
            }, 200);
        }

        else if (id==R.id.nav_share)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey, Looking for a good college but confused. Register at Yantra and get best colleges.");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            }, 200);
        }

        else if (id==R.id.follow)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://www.facebook.com/"));
                    startActivity(i);

                }
            }, 200);
        }

        else if (id==R.id.nav_rate)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
//                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
//                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
//                    // To count with Play market backstack, After pressing back button,
//                    // to taken back to our application, we need to add following flags to intent.
//                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
//                            Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
//                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//
//
//                    //Toast.makeText(this, "This would redirect the user to rate us after we publish the app!", Toast.LENGTH_LONG).show();
//                    try {
//                        startActivity(goToMarket);
//                    } catch (ActivityNotFoundException e) {
//                        startActivity(new Intent(Intent.ACTION_VIEW,
//                                Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
//                    }

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/")));

                }
            }, 200);

        }

        else if(id==R.id.recommendations){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, Recommendations.class ));


                }
            }, 200);
        }
        else if (id==R.id.Feed)
        {
            try{
                Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "universityexperts85@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Yantra Feedback");
                    intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(intent);
            }catch(ActivityNotFoundException e){
                //TODO smth
               // Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }

        }

        else if (id==R.id.about_us)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, AboutActivity.class ));


                }
            }, 200);
        }

        else if (id==R.id.privacy_policy)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, PrivacyActivity.class ));
                }
            }, 200);
        }
        else if (id==R.id.helpdesk)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, HelpDeskActivity.class ));
                }
            }, 200);
        }
//        else if (id==R.id.step)
//        {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    startActivity(new Intent(MainActivity.this, StepperActivity.class ));
//                }
//            }, 200);
//        }
        else if (id==R.id.publication)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(MainActivity.this, com.university.yantra.GameBrainBooster.MainActivity.class);
                    intent.putExtra("flag","true");
                    startActivity(intent);
                }
            }, 200);
        }
        else if (id==R.id.booking)
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(MainActivity.this, AptitudeActivity.class);
                    intent.putExtra("flag","true");
                    startActivity(intent);
                }
            }, 200);
        }

        else if (id==R.id.guide)
        {
            learnAppFeatures();
        }
        else if (id==R.id.register)
        {
            startActivity(new Intent(getApplicationContext(),PaymentsActivity.class));
        }
        else if (id==R.id.loan)
        {
            startActivity(new Intent(getApplicationContext(),SearchActivity.class));
        }
        else if (id==R.id.forms)
        {
            startActivity(new Intent(getApplicationContext(),ForexActivity.class));
        }
        else if (id==R.id.resume)
        {
            startActivity(new Intent(getApplicationContext(),PreferencesActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void learnAppFeatures()
    {
        bottomNavigation.setCurrentItem(0);
        showcaseView=new ShowcaseView.Builder(MainActivity.this)
                .setTarget(new ViewTarget(findViewById(R.id.feature2)))
                .setStyle(R.style.CustomShowcaseTheme4)
                .setOnClickListener(MainActivity.this)
                .setContentTitle("Home")
                .setContentText("You will all the suggestions for courses and colleges in yantra")
                .build();
        showcaseView.setButtonText("NEXT");
        bottomNavigation.setCurrentItem(1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
       // dra();
    }


    @Override
    public void onUserReceived(User user) {
        //showMessage(user.getId() + ": " + user.getEmail());


        username.setVisibility(View.VISIBLE);
        username.setText(user.getFirstName());
        Log.d("username",user.getFirstName());
        email.setText(user.getEmail());
    }

    @Override
    public void onAuthenticated(AuthenticationResponse authenticationResponse) {

    }


    @Override
    public void onClick(View view) {
        switch (counter)
        {
            case 0:
                showcaseView.setShowcase(new ViewTarget(findViewById(R.id.phone)), true);
                showcaseView.setContentTitle("Call Us");
                showcaseView.setContentText("For any query, you call us 24X7");
                showcaseView.setStyle(R.style.CustomShowcaseTheme2);
                showcaseView.setButtonText("Next");
                break;
            case 1:
                showcaseView.setShowcase(new ViewTarget(findViewById(R.id.notif)), true);
                showcaseView.setContentTitle("Notifications");
                showcaseView.setStyle(R.style.CustomShowcaseTheme3);
                showcaseView.setContentText("For Every update you will be notified..");
                showcaseView.setButtonText("Next");
                break;
            case 2:
                showcaseView.setShowcase(new ViewTarget(findViewById(R.id.feature4)), true);
                showcaseView.setContentTitle("Search");
                showcaseView.setStyle(R.style.CustomShowcaseTheme2);
                showcaseView.setContentText("You can explore colleges with your own requirements");
                showcaseView.setButtonText("Next");
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewPagerEx.LayoutParams.WRAP_CONTENT, ViewPagerEx.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                showcaseView.setButtonPosition(layoutParams);
                layoutParams.setMargins(30,0,0,30);
                bottomNavigation.setCurrentItem(3);
                break;

            case 3:
                layoutParams = new RelativeLayout.LayoutParams(ViewPagerEx.LayoutParams.WRAP_CONTENT, ViewPagerEx.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

                showcaseView.setStyle(R.style.CustomShowcaseTheme3);
                showcaseView.setTarget(Target.NONE);
                showcaseView.setContentTitle("Yantra");
                showcaseView.setContentText("A platform for all the students all over the world to get the best of the education from the best colleges :))");
                showcaseView.setButtonText("Start");
                showcaseView.setButtonPosition(layoutParams);
                bottomNavigation.setCurrentItem(0);
                break;
            case 4:
                showcaseView.hide();
                break;
        }
        counter++;
    }

    void startLoginActivity() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
