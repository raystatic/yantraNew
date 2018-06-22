package com.university.yantra.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.university.yantra.R;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.ResponseMessage;
import ai.api.model.Result;
import ai.api.ui.AIButton;

public class DialogFlow  extends AppCompatActivity implements AIListener,TextToSpeech.OnInitListener {

    AIButton aiButton;
    AIService aiService;
    Intent j;
    private TextToSpeech myTTS;
    private int MY_DATA_CHECK_CODE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_flow);
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            makeRequest();
        }
         final AIConfiguration config = new AIConfiguration("fe658cb71e33443ca71aa23839ad7c54",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        final AIService aiService = AIService.getService(this, config);
        aiService.setListener(this);
        aiButton = findViewById(R.id.micButton);
        aiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aiService.startListening();
            }
        });

    }
    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                101);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {


                } else {

                }
                return;
            }
        }
    }

    @Override
    public void onInit(int i) {

    }


    @Override
    public void onResult(AIResponse result) {
//        final Result results = result.getResult();
//        final List<ResponseMessage> messages = results.getFulfillment().getMessages();
//        for (ResponseMessage message : messages) {
//           Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show();
//        }
//        final List<ResponseMessage> messages = results.getFulfillment().getMessages();
//        for (ResponseMessage message : messages) {
//           Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show();
//        }
        HashMap<String,JsonElement> parameter = result.getResult().getParameters();
        Log.d("Courses",parameter.get("Courses")+"");
        Log.d("Universities",parameter.get("Universities")+"");

        if(result.getResult().getAction().equals("show_course")) {
            j = new Intent(DialogFlow.this, DialogFlowResultActivity.class);
            j.putExtra("type", 2+"");
            j.putExtra("search", parameter.get("Courses")+"");
            j.putExtra("university",parameter.get("universties")+"");
            startActivity(j);
        }
        if(result.getResult().getAction().equals("show_university")) {
            j = new Intent(DialogFlow.this, DialogFlowResultActivity.class);
            j.putExtra("type", 1+"");
            j.putExtra("search", parameter.get("Universities")+"");
            j.putExtra("university",parameter.get("universties")+"");
            startActivity(j);
        }



        final Result results = result.getResult();
        final String speech = results.getFulfillment().getSpeech();
        Toast.makeText(this,speech.toString(),Toast.LENGTH_SHORT).show();
        myTTS.speak(speech.toString(), TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onError(final AIError error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("ApiAi", "onError"+error.getMessage());
                // TODO process error here
            }
        });
    }


    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

        Toast.makeText(this,"listening",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListeningCanceled() {

      }

    @Override
    public void onListeningFinished() {

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                myTTS = new TextToSpeech(this, this);
            }
            else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }


}

