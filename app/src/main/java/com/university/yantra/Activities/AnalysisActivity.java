package com.university.yantra.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.airbnb.lottie.L;
import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import com.mvc.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;
import com.university.yantra.BaseActivity;
import com.university.yantra.GameBrainBooster.*;
import com.university.yantra.R;
import com.university.yantra.listeners.AnalysisEventListener;
import com.university.yantra.models.Analysis.MCQ;
import com.university.yantra.presenters.AnalysisPresenter;
import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Rishabh on 3/6/2018.
 */

public class AnalysisActivity extends BackPressed implements AnalysisEventListener, AdapterView.OnItemSelectedListener{

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    AnalysisPresenter presenter;
    Button skip;
    int choice=-1;
    LinearLayout linearLayout;
    int i=0;
    TextView textStart;
    TextView textSkip;
    LinearLayout gameInstLayout;
    TextView gameInstructions;
    ImageView instruct;

    Bitmap bitmap;
    LinearLayout l1,l2,l3,l4;
    Button next;
    ImageView upload;
    int f1=0,f2=0,f3=0,f4=0,f5=0,f6=0,f7=0,f8=0,f9=0;
    int array[]={1,2,3};
    ImageView e1,e2,e3,e4,e5,e6,e7,e8,e9;
    RelativeLayout r1,r2,r3,r4,r5,r6,r7,r8,r9;
    int count = 5;
    Spinner subjectSpinner,gameSpinner,pastTimeSpinner;

    RoundCornerProgressBar progress1;
    //bio data screen
    Spinner spinner;
    EditText name;
    EditText age;
    RadioGroup gender;
    RadioButton button,girl,boy;

    //calender work
    DatePicker datePicker;

    Calendar calendar;
    EditText dateEditText;
    int year, month, day;


    //emotional screen
    String[] descriptionData = {};
    TextView question;
    ImageView skipButton;
    //ImageView prevButton;
    ImageView startButton;
    Button nextButton;
    DisplayMetrics displayMetrics;
    ImageView gameImage;
    TextView gameName;
    RadioGroup choices;
    Typeface mFont;
    ViewGroup mContainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.content_main);

        presenter = new AnalysisPresenter(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Log.d("displayanalysis",displayMetrics.heightPixels+","+displayMetrics.widthPixels);
        Intent i = getIntent();
        if(i.getExtras()!=null){
            if(i.getExtras().getString("state").equals("1")){

                showMessage("1");
                presenter.sequence =26;
                presenter.start(1);
                showPersonalIntroScreen();
            }
            else if(i.getExtras().getString("state").equals("3")){
                presenter.start(2);
                showBioDataScreen();
            }
            else{
                presenter.start(0);
                //showPersonalIntroScreen();
            }

        }


    }


    @Override
    public void showIntroScreen() {
        //Toast.makeText(this,"called Intro",Toast.LENGTH_SHORT).show();
        setContentView(R.layout.profile_intro);
        gameInstLayout = findViewById(R.id.instructions);
        gameInstructions = findViewById(R.id.game_instructions);
        instruct = findViewById(R.id.instr);
        textSkip = findViewById(R.id.text_skip);
        textStart = findViewById(R.id.text_start);
        gameImage=(ImageView)findViewById(R.id.game_image);

        gameName=(TextView)findViewById(R.id.game_name);
        startButton = (ImageView) findViewById(R.id.start_p);
        gameImage.setImageDrawable(getResources().getDrawable(R.drawable.bg_game9));
        gameName.setText("USER PROFILE");
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        gameInstructions.setText(R.string.parental_intro_1);
        setColorOnLayout(getResources().getColor(R.color.game1),0);



    }

    public void setColorOnLayout(int color,int mode){
        //gameInstLayout.setBackgroundColor(color);
        gameInstructions.setTextColor(color);
        instruct.setColorFilter(color);
        gameName.setTextColor(color);
        textStart.setTextColor(color);
        startButton.setColorFilter(color);
        if(mode==1){
            skipButton.setColorFilter(color);
            textSkip.setTextColor(color);
        }
    }
    @Override
    public void showBioDataScreen() {
        setContentView(R.layout.bio_data_layout);
        setProgressBarData(20);
        next = (Button) findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);


        dateEditText = (EditText)findViewById(R.id.dob);
        gender = (RadioGroup)findViewById(R.id.gender);
        girl = (RadioButton)findViewById(R.id.girl);
        boy = (RadioButton)findViewById(R.id.boy);
        name = (EditText)findViewById(R.id.input_name);
        age = (EditText)findViewById(R.id.dob);
        spinner= findViewById(R.id.spinner0);


        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> siblings_array = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.income));
        siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(siblings_array);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numSiblings = (String) spinner.getSelectedItem();
                int genderSelected = gender.getCheckedRadioButtonId();
                button = (RadioButton) findViewById(genderSelected);
                if(button != null){

                    Log.d("Parent.java", button.getText()+"");
                    if(button.getText().equals("Boy"))
                        showMessage("0"+name.getText()+","+age.getText()+","+numSiblings);
                    else
                        showMessage("1"+name.getText()+","+age.getText()+","+numSiblings);
                }
                presenter.next();
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

    }



    @Override
    public void showPreferencesScreen() {
        setContentView(R.layout.favorite_preferences_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

        setProgressBarData(40);

        spinner= findViewById(R.id.spinner1);
        subjectSpinner = findViewById(R.id.spinner);
        gameSpinner = findViewById(R.id.spinner2);
        pastTimeSpinner = findViewById(R.id.spinner3);


        spinner.setOnItemSelectedListener(this);
        subjectSpinner.setOnItemSelectedListener(this);
        gameSpinner.setOnItemSelectedListener(this);
        pastTimeSpinner.setOnItemSelectedListener(this);

        List<String> streamList = new ArrayList<>();
        streamList.add("0");
        streamList.add("1");
        streamList.add(">1");

        ArrayAdapter<String> streamArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.streams));
        streamArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(streamArrayAdapter);

        String[] subject={"Maths","Science","Social Studies","English","Art and Humanities","Physical Education","Economics","Computer Science","Other"};
        ArrayAdapter<String> subjectArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subject);
        subjectArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(subjectArrayAdapter);

        String[] game = {"Action", "Strategy","Puzzle","English","Adventure","Role-Playing","Educational","Racing", "Board Games","Creativity", "Other"};
        ArrayAdapter<String> gameArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,game);
        gameArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameSpinner.setAdapter(gameArrayAdapter);

        String[] pastTime = {"Games","Music and Dance","Art and Craft","Reading","Shopping","Partying","Travelling","Watching TV","Other"};
        ArrayAdapter<String> pastTimeArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pastTime);
        pastTimeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pastTimeSpinner.setAdapter(pastTimeArrayAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stream = (String) spinner.getSelectedItem();
                showMessage(stream);
                presenter.next();
            }
        });


    }

    @Override
    public void showAcademicsScreen() {
        EditText marks10,marks12,year10, year12;
        setContentView(R.layout.academics_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        setProgressBarData(60);

        spinner = findViewById(R.id.spinner0);
        spinner.setOnItemSelectedListener(this);

        String[] state = {"Percentage","Marks","CGPA","SGPA"};

        ArrayAdapter<String> siblings_array = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, state);
        siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(siblings_array);


        subjectSpinner = findViewById(R.id.spinner1);
        subjectSpinner.setAdapter(siblings_array);
        year12 = findViewById(R.id.pass_year);
        marks12 = findViewById(R.id.marks12);

        //String exam12 = subjectSpinner.getSelectedItem().toString();

    }

    @Override
    public void showHigherEducationScreen() {
        setContentView(R.layout.higher_education_screen);
        final LinearLayout mainRelativeLayout = findViewById(R.id.mainRelativeLayout);
        RelativeLayout add = findViewById(R.id.add);
        ImageView add1 = findViewById(R.id.addimage);
        TextView add2 = findViewById(R.id.textView18);
        linearLayout = (LinearLayout) View.inflate(AnalysisActivity.this, R.layout.higher_edu_layout, null);
        spinner =   ((Spinner) linearLayout.findViewById(R.id.name1));
        final String[] state = {"Percentage","Marks","CGPA","SGPA"};

        ArrayAdapter<String> siblings_array = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, state);
        siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(siblings_array);

        mainRelativeLayout.addView(linearLayout);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showMessage("clicked");
                    LinearLayout newLayout = (LinearLayout) View.inflate(
                            AnalysisActivity.this,
                            R.layout.higher_edu_layout_2, null);
                    subjectSpinner =   ((Spinner) newLayout.findViewById(R.id.name2));
//                    ((TextView) linearLayout
//                            .findViewById(R.id.anotherlayoutTextView))
//                            .setText(“Inflate Layout Text ” + (i + 1));
                ArrayAdapter<String> siblings_array = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, state);
                siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(siblings_array);
                    mainRelativeLayout.addView(newLayout);
            }
        });
        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showMessage("clicked");
                LinearLayout newLayout = (LinearLayout) View.inflate(
                        AnalysisActivity.this,
                        R.layout.higher_edu_layout_2, null);
                subjectSpinner =   ((Spinner) newLayout.findViewById(R.id.name2));
//                    ((TextView) linearLayout
//                            .findViewById(R.id.anotherlayoutTextView))
//                            .setText(“Inflate Layout Text ” + (i + 1));
                ArrayAdapter<String> siblings_array = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, state);
                siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(siblings_array);
                mainRelativeLayout.addView(newLayout);
            }
        });
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showMessage("clicked");
                LinearLayout newLayout = (LinearLayout) View.inflate(
                        AnalysisActivity.this,
                        R.layout.higher_edu_layout_2, null);
                subjectSpinner =   ((Spinner) newLayout.findViewById(R.id.name2));
//                    ((TextView) linearLayout
//                            .findViewById(R.id.anotherlayoutTextView))
//                            .setText(“Inflate Layout Text ” + (i + 1));
                ArrayAdapter<String> siblings_array = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, state);
                siblings_array.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(siblings_array);
                mainRelativeLayout.addView(newLayout);
            }
        });
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
//        prevButton = (ImageView)findViewById(R.id.prev);
//        prevButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                presenter.previous();
//            }
//        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
       setProgressBarData(80);
    }

    @Override
    public void showSocialScreen() {
        setContentView(R.layout.social_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

        EditText fbLink,linkedInLink;
        fbLink = findViewById(R.id.fb_link);
        linkedInLink = findViewById(R.id.linkedin_link);

        setProgressBarData(100);
    }

    @Override
    public void showMoodScreen() {
        // currentMood java class for implementation
        setContentView(R.layout.current_mood_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

        initialize();


        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(1,0,0,0,0,0,0,0,0);
            }
        });


        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,1,0,0,0,0,0,0,0);
            }
        });

        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,1,0,0,0,0,0,0);
            }
        });


        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,1,0,0,0,0,0);
            }
        });


        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,0,1,0,0,0,0);
            }
        });

        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,0,0,1,0,0,0);
            }
        });

        e7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,0,0,0,1,0,0);
            }
        });

        e8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,0,0,0,0,1,0);
            }
        });

        e9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setbb(0,0,0,0,0,0,0,0,1);
            }
        });


    }

    @Override
    public void showLifeChoicesScreen() {
        setContentView(R.layout.life_choices_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });

        initialize();

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1=myfun(f1,r1,1);
            }
        });

        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f2=myfun(f2,r2,2);
            }
        });

        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f3=myfun(f3,r3,3);
            }
        });

        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f4=myfun(f4,r4,4);
            }
        });

        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f5=myfun(f5,r5,5);
            }
        });

        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f6=myfun(f6,r6,6);
            }
        });

        e7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f7=myfun(f7,r7,7);
            }
        });

        e8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f8=myfun(f8,r8,8);
            }
        });


        final String[] text = {" "};

        e9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                f9=myfun(f9,r9,9);
                AlertDialog.Builder builder = new AlertDialog.Builder(AnalysisActivity.this);
                builder.setTitle("Tell your preferences ?");
                // I'm using fragment here so I'm using getView() to provide ViewGroup
                // but you can provide here any other instance of ViewGroup from your Fragment / Activity
                View viewInflated = LayoutInflater.from(AnalysisActivity.this).inflate(R.layout.text_box_dialog, (ViewGroup) findViewById(android.R.id.content), false);
                final EditText input = (EditText) viewInflated.findViewById(R.id.input);
                builder.setView(viewInflated);
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        text[0] = input.getText().toString();

                       dialog.dismiss();

                    }
                });

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public void showQuestion1Screen(MCQ mcq) {
        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        choices = findViewById(R.id.choices);
        question = (TextView)findViewById(R.id.quetxtview);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });


        question.setText(R.string.quest1);
        setProgressBarData(12);

        int genderselected = choices.getCheckedRadioButtonId();
        button = (RadioButton) findViewById(genderselected);




    }

    @Override
    public void showQuestion2Screen(MCQ mcq) {
        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest2);

        setProgressBarData(24);

        int genderselected = choices.getCheckedRadioButtonId();
        button = (RadioButton) findViewById(genderselected);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showMessage(button.getText()+"");

                presenter.next();

            }
        });
    }

    @Override
    public void showQuestion3Screen(MCQ mcq) {

        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest3);
        int genderselected = choices.getCheckedRadioButtonId();
        button = (RadioButton) findViewById(genderselected);

        setProgressBarData(36);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                presenter.next();

            }
        });
    }

    @Override
    public void showQuestion4Screen(MCQ mcq) {
        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest4);


        int genderselected = choices.getCheckedRadioButtonId();
        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(48);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                presenter.next();

            }
        });
    }

    @Override
    public void showQuestion5Screen(MCQ mcq) {
        //parent 6 for this

        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest5);

        int genderselected = choices.getCheckedRadioButtonId();
        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(60);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                presenter.next();

            }
        });
    }
    @Override
    public void showQuestion6Screen(MCQ mcq) {
        //parent 6 for this

        setContentView(R.layout.checkbox_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest6);

        int genderselected = choices.getCheckedRadioButtonId();
        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(72);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                presenter.next();

            }
        });
    }
    @Override
    public void showQuestion7Screen(MCQ mcq) {
        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest7);

        int genderselected = choices.getCheckedRadioButtonId();
        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(84);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                presenter.next();

            }
        });
    }



    @Override
    public void showSeekBarScreen(){
        setContentView(R.layout.seekbar_layout);
        BubbleSeekBar seekBar = (BubbleSeekBar)findViewById(R.id.seek);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest8);

        setProgressBarData(90);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                presenter.next();

            }
        });
    }
    @Override
    public void showQuestion9Screen(MCQ mcq) {
        //parent 6 for this

        setContentView(R.layout.questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        choices = (RadioGroup)findViewById(R.id.choices);
        question.setText(R.string.quest9);

        int genderselected = choices.getCheckedRadioButtonId();
        button = (RadioButton) findViewById(genderselected);
        setProgressBarData(100);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int choice = choices.getCheckedRadioButtonId();
//
//                button = (RadioButton) findViewById(choice);
//                if(button!=null){
//                    showMessage(button.getText()+"");
//                }
                presenter.next();

            }
        });
    }


    @Override
    public void showEmotionalIntroScreen() {
        setContentView(R.layout.emotional_intro);
        gameInstLayout = findViewById(R.id.instructions);
        gameInstructions = findViewById(R.id.game_instructions);
        instruct = findViewById(R.id.instr);
        textSkip = findViewById(R.id.text_skip);
        textStart = findViewById(R.id.text_start);
        gameImage=(ImageView)findViewById(R.id.game_image);
        gameName=(TextView)findViewById(R.id.game_name);
        startButton = (ImageView) findViewById(R.id.start_p);
        skipButton = findViewById(R.id.skip);
        gameImage.setImageDrawable(getResources().getDrawable(R.drawable.bg_game5));
        gameName.setText("EMOTIONAL INTELLIGENCE");
        skipButton = findViewById(R.id.skip);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.lastQuestionId+=7;
                showCriticalIntroScreen();
            }
        });
        setColorOnLayout(getResources().getColor(R.color.game2),1);
    }

    @Override
    public void showCriticalIntroScreen() {
        setContentView(R.layout.critical_intro);
        gameInstLayout = findViewById(R.id.instructions);
        gameInstructions = findViewById(R.id.game_instructions);
        instruct = findViewById(R.id.instr);
        textSkip = findViewById(R.id.text_skip);
        textStart = findViewById(R.id.text_start);
        gameImage=(ImageView)findViewById(R.id.game_image);
        gameName=(TextView)findViewById(R.id.game_name);
        startButton = (ImageView) findViewById(R.id.start_p);
        gameImage.setImageDrawable(getResources().getDrawable(R.drawable.bg_game9));
        gameName.setText("CRITICAL REASONING");
        skipButton = findViewById(R.id.skip);
        skipButton = findViewById(R.id.skip);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLifeChoicesIntroScreen();
                presenter.lastQuestionId+=6;
            }
        });
        setColorOnLayout(getResources().getColor(R.color.game3),1);
    }

    @Override
    public void showCriticalQues1Screen() {
        setContentView(R.layout.critical_questions_layout);

        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();

            }
        });
        setProgressBarData(20);

        intializeQuestions();

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(1,0,0,0);
                //l1.setBackgroundColor(getResources().getColor(R.color.ligorange));
                //showMessage("clicked");
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,1,0,0);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,0,1,0);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,0,0,1);
            }
        });



    }

    private void intializeQuestions(){

        l1 = findViewById(R.id.opt1);
        l2 = findViewById(R.id.opt2);
        l3 = findViewById(R.id.opt3);
        l4 = findViewById(R.id.opt4);


    }
    @Override
    public void showCriticalQues2Screen() {
        setContentView(R.layout.critical_questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        //question.setText("Question 2");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();

            }
        });
        intializeQuestions();
       setProgressBarData(40);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(1,0,0,0);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,1,0,0);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,0,1,0);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,0,0,1);
            }
        });

    }

    @Override
    public void showCriticalQues3Screen() {
        setContentView(R.layout.critical_questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        //question.setText("Question 3");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();

            }
        });
        intializeQuestions();
        setProgressBarData(60);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(1,0,0,0);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,1,0,0);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,0,1,0);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,0,0,1);
            }
        });

    }

    @Override
    public void showCriticalQues4Screen() {
        setContentView(R.layout.critical_questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        intializeQuestions();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();

            }
        });
        setProgressBarData(80);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(1,0,0,0);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,1,0,0);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,0,1,0);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,0,0,1);
            }
        });

    }

    @Override
    public void showCriticalQues5Screen() {
        setContentView(R.layout.critical_questions_layout);
        next = (Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        question = (TextView)findViewById(R.id.quetxtview);
        //question.setText("Question 5");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();

            }
        });
        intializeQuestions();
        setProgressBarData(100);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(1,0,0,0);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,1,0,0);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,0,1,0);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionBackground(0,0,0,1);
            }
        });

    }

    @Override
    public void showLifeChoicesIntroScreen() {
        setContentView(R.layout.lifechoices_intro);
        gameInstLayout = findViewById(R.id.instructions);
        gameInstructions = findViewById(R.id.game_instructions);
        instruct = findViewById(R.id.instr);
        textSkip = findViewById(R.id.text_skip);
        textStart = findViewById(R.id.text_start);
        gameImage=(ImageView)findViewById(R.id.game_image);
        gameName=(TextView)findViewById(R.id.game_name);
        startButton = (ImageView) findViewById(R.id.start_p);
        skipButton = findViewById(R.id.skip);
        gameImage.setImageDrawable(getResources().getDrawable(R.drawable.bg_game5));
        gameName.setText("LIFE CHOICES");
        gameInstructions.setText(R.string.game5_instruction);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        skipButton = findViewById(R.id.skip);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPersonalIntroScreen();
                presenter.lastQuestionId+=2;
            }
        });
        setColorOnLayout(getResources().getColor(R.color.game4),1);
    }

    @Override
    public void showPersonalIntroScreen() {
        setContentView(R.layout.personalize_intro);
        gameInstLayout = findViewById(R.id.instructions);
        gameInstructions = findViewById(R.id.game_instructions);
        instruct = findViewById(R.id.instr);
        textSkip = findViewById(R.id.text_skip);
        textStart = findViewById(R.id.text_start);
        gameImage=(ImageView)findViewById(R.id.game_image);
        gameName=(TextView)findViewById(R.id.game_name);
        startButton = (ImageView) findViewById(R.id.start_p);
        gameImage.setImageDrawable(getResources().getDrawable(R.drawable.bg_game5));
        gameName.setText("PERSONALITY SECTION");
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              presenter.next();

            }
        });
        skipButton = findViewById(R.id.skip);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showEndScreenPersonal();
            }
        });
        setColorOnLayout(getResources().getColor(R.color.game5),1);
    }

    @Override
    public void showBrainBoosterIntroScreen() {
        setContentView(R.layout.brain_booster_intro);
        gameInstLayout = findViewById(R.id.instructions);
        gameInstructions = findViewById(R.id.game_instructions);
        instruct = findViewById(R.id.instr);
        textSkip = findViewById(R.id.text_skip);
        textStart = findViewById(R.id.text_start);
        gameImage=(ImageView)findViewById(R.id.game_image);
        gameName=(TextView)findViewById(R.id.game_name);
        startButton = (ImageView) findViewById(R.id.start_p);
        gameImage.setImageDrawable(getResources().getDrawable(R.drawable.bg_game9));

        gameInstructions.setText(R.string.game9_instruction);
        gameName.setText("BRAIN BOOSTER");
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnalysisActivity.this, com.university.yantra.GameBrainBooster.MainActivity.class));
            }
        });
        skipButton = findViewById(R.id.skip);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPersonalIntroScreen();
                presenter.lastQuestionId++;
            }
        });
        setColorOnLayout(getResources().getColor(R.color.game6),1);
    }


    @Override
    public void showHandwritingScreen() {
        setContentView(R.layout.handwriting_screen_layout);
        next =(Button)findViewById(R.id.next1);
        skip = (Button)findViewById(R.id.skip1);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
        upload = findViewById(R.id.uploadImage);

        try {
            ImagePicker.setMinQuality(600, 600);
                upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        onPickImage(upload);


                }
            });
//
        }
        catch (Exception e){
            Log.d("exc upload image",e+"");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               presenter.next();

            }
        });


    }

    @Override
    public void onBackPressed() {
       presenter.previous();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void showNetworkError(String message) {

    }

    @Override
    public void showEndScreenIntro() {
        setContentView(R.layout.end_screen);
        TextView clickButton = findViewById(R.id.textView5);
        clickButton.setVisibility(View.VISIBLE);
        nextButton = findViewById(R.id.start_next_game);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });


    }

    @Override
    public void showEndScreenEmotional() {

        setContentView(R.layout.end_screen);
        nextButton = findViewById(R.id.start_next_game);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
    }

    @Override
    public void showEndScreenLife() {
        setContentView(R.layout.end_screen);
        nextButton = findViewById(R.id.start_next_game);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
    }

    @Override
    public void showEndScreenPersonal() {

        setContentView(R.layout.end_screen);



        TextView message = findViewById(R.id.textView6);
        message.setText("YOU HAVE SUCCESSFULLY COMPLETED ANALYSIS!");
        TextView clickButton = findViewById(R.id.textView5);
        clickButton.setVisibility(View.GONE);
        nextButton = findViewById(R.id.start_next_game);
        nextButton.setText("View Results");
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i=new Intent(AnalysisActivity.this, Recommendations.class);
                i.putExtra("key",1);
                startActivity(i);
            }
        });
    }

    @Override
    public void showEndScreenCritical() {
        setContentView(R.layout.end_screen);
        nextButton = findViewById(R.id.start_next_game);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.next();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateEditText.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private void setProgressBarData(int completion){
        progress1 = (RoundCornerProgressBar) findViewById(R.id.progress_1);
        progress1.setProgressColor(getResources().getColor(R.color.dorange));
        progress1.setProgressBackgroundColor(getResources().getColor(R.color.ligorange));
        progress1.setMax(100);
        progress1.setProgress(completion);
    }
    public void showMessage(String message){
        //Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    void setbb(int a1,int a2,int a3,int a4,int a5,int a6,int a7,int a8,int a9)
    {
        if(a1==1)
        {
            choice = 1;
            r1.setBackground(getResources().getDrawable(R.drawable.circle));
        }

        else
        {
            r1.setBackground(getResources().getDrawable(R.drawable.circle_white));
        }

        if(a2==1)
        {
            choice =2;
            r2.setBackground(getResources().getDrawable(R.drawable.circle));
        }

        else
        {
            r2.setBackground(getResources().getDrawable(R.drawable.circle_white));
        }

        if(a3==1)
        {
            choice =3;
            r3.setBackground(getResources().getDrawable(R.drawable.circle));

        }

        else
        {

            r3.setBackground(getResources().getDrawable(R.drawable.circle_white));
        }

        if(a4==1)
        {
            choice =4;
            r4.setBackground(getResources().getDrawable(R.drawable.circle));

        }

        else
        {
            r4.setBackground(getResources().getDrawable(R.drawable.circle_white));
        }

        if(a5==1)
        {
            choice = 5;
            r5.setBackground(getResources().getDrawable(R.drawable.circle));
        }

        else
        {
            r5.setBackground(getResources().getDrawable(R.drawable.circle_white));
        }

        if(a6==1)
        {
            choice =6;
            r6.setBackground(getResources().getDrawable(R.drawable.circle));
        }

        else
        {
            r6.setBackground(getResources().getDrawable(R.drawable.circle_white));
        }

        if(a7==1)
        {
            choice = 7;
            r7.setBackground(getResources().getDrawable(R.drawable.circle));
        }

        else
        {
            r7.setBackground(getResources().getDrawable(R.drawable.circle_white));
        }

        if(a8==1)
        {
            choice = 8;
            r8.setBackground(getResources().getDrawable(R.drawable.circle));
        }

        else
        {
            r8.setBackground(getResources().getDrawable(R.drawable.circle_white));
        }

        if(a9==1)
        {
            choice = 9;
            r9.setBackground(getResources().getDrawable(R.drawable.circle));
        }

        else
        {
            r9.setBackground(getResources().getDrawable(R.drawable.circle_white));
        }
    }
    private void setQuestionBackground(int a1,int a2,int a3,int a4){
        if(a1==1)
        {
            choice = 1;
            //showMessage("choice 1");
            l1.setBackgroundResource(R.drawable.question_selected);
        }

        else
        {
            l1.setBackground(getResources().getDrawable(R.drawable.rectangle));
        }

        if(a2==1)
        {
            choice =2;
            l2.setBackgroundResource(R.drawable.question_selected);
        }

        else
        {
            l2.setBackground(getResources().getDrawable(R.drawable.rectangle));
        }

        if(a3==1)
        {
            choice =3;
            l3.setBackgroundResource(R.drawable.question_selected);

        }

        else
        {

            l3.setBackground(getResources().getDrawable(R.drawable.rectangle));
        }

        if(a4==1)
        {
            choice =4;
            l4.setBackgroundResource(R.drawable.question_selected);

        }

        else
        {
            l4.setBackground(getResources().getDrawable(R.drawable.rectangle));
        }
    }
    public void inc()
    {
        if(i==2)
        {
            i=0;
        }

        else
        {
            i++;
        }
    }
    int myfun(int f,RelativeLayout r,int y)
    {
        if(f==0)
        {
            if((f1+f2+f3+f4+f5+f6+f7+f8+f9)<3)
            {
                f = 1;
                r.setBackground(getResources().getDrawable(R.drawable.circle));
                array[i]=y;
                inc();
            }

            else if((f1+f2+f3+f4+f5+f6+f7+f8+f9)==3)
            {
                if(array[0]==1)
                {
                    f1=0;
                    r1.setBackground(getResources().getDrawable(R.drawable.circle_white));
                }

                if(array[0]==2)
                {
                    f2=0;
                    r2.setBackground(getResources().getDrawable(R.drawable.circle_white));
                }

                if(array[0]==3)
                {
                    f3=0;
                    r3.setBackground(getResources().getDrawable(R.drawable.circle_white));
                }

                if(array[0]==4)
                {
                    f4=0;
                    r4.setBackground(getResources().getDrawable(R.drawable.circle_white));
                }
                if(array[0]==5)
                {
                    f5=0;
                    r5.setBackground(getResources().getDrawable(R.drawable.circle_white));
                }

                if(array[0]==6)
                {
                    f6=0;
                    r6.setBackground(getResources().getDrawable(R.drawable.circle_white));
                }

                if(array[0]==7)
                {
                    f7=0;
                    r7.setBackground(getResources().getDrawable(R.drawable.circle_white));
                }

                if(array[0]==8)
                {
                    f8=0;
                    r8.setBackground(getResources().getDrawable(R.drawable.circle_white));
                }

                if(array[0]==9)
                {
                    f9=0;
                    r9.setBackground(getResources().getDrawable(R.drawable.circle_white));
                }


                array[0]=array[1];
                array[1]=array[2];
                f =1;
                r.setBackground(getResources().getDrawable(R.drawable.circle));
                i=2;
                array[i]=y;
            }
        }

        else
        {
            f = 0;
            array_swap(y);
            r.setBackground(getResources().getDrawable(R.drawable.circle_white));
        }
        return f;
    }
    public void array_swap(int index)
    {
        if(array[0]==index)
        {
            array[0]=array[1];
            array[1]=array[2];
            i--;
        }

        else if(array[1]==index)
        {
            array[1]=array[2];
            i--;
        }

        else if(array[2]==index)
        {
            i=2;
        }
    }
    private void initialize(){
        e1=(ImageView)findViewById(R.id.e1);
        e2=(ImageView)findViewById(R.id.e2);
        e3=(ImageView)findViewById(R.id.e3);
        e4=(ImageView)findViewById(R.id.e4);
        e5=(ImageView)findViewById(R.id.e5);
        e6=(ImageView)findViewById(R.id.e6);
        e7=(ImageView)findViewById(R.id.e7);
        e8=(ImageView)findViewById(R.id.e8);
        e9=(ImageView)findViewById(R.id.e9);

        r1=(RelativeLayout) findViewById(R.id.r1);
        r2=(RelativeLayout) findViewById(R.id.r2);
        r3=(RelativeLayout) findViewById(R.id.r3);
        r4=(RelativeLayout) findViewById(R.id.r4);
        r5=(RelativeLayout) findViewById(R.id.r5);
        r6=(RelativeLayout) findViewById(R.id.r6);
        r7=(RelativeLayout) findViewById(R.id.r7);
        r8=(RelativeLayout) findViewById(R.id.r8);
        r9=(RelativeLayout) findViewById(R.id.r9);
    }
    public void onPickImage(View view) {
        ImagePicker.pickImage(this, "Select your image:");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{

            bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
            if(bitmap!=null){
                upload.setImageBitmap(bitmap);
                showMessage("done");


            }
            else{
                showMessage("There was some problem uploading the image");
            }
        }
        catch (Exception e){
            Log.d("set image error",e+"");
        }


        super.onActivityResult(requestCode, resultCode, data);

    }
    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                       getParent(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                } else {
                    Toast.makeText(AnalysisActivity.this, "Permission Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }}

