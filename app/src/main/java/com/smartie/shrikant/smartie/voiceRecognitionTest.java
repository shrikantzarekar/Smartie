package com.smartie.shrikant.smartie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class voiceRecognitionTest extends Activity implements OnInitListener, OnUtteranceCompletedListener {
	String userspeech ="";
	//private TextView mText;
	private SpeechRecognizer sr;
	private static final String TAG = "MyStt3Activity";
	TextToSpeech t1;
	AudioManager amanager;
	private MyCountDownTimer countDownTimer;

	VideoView mVideoView;
	MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean pausing = false;
    HashMap<String, String> myHashAlarm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newlayout);
		myHashAlarm = new HashMap();
		myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
		        String.valueOf(AudioManager.STREAM_ALARM));
		myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,
		        "end of wakeup message ID");

		//mText = (TextView) findViewById(R.id.textView12);

		getWindow().setFormat(PixelFormat.UNKNOWN);
		mVideoView = (VideoView)findViewById(R.id.videoview);
		mVideoView.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				initializeVideo();
			}
		});

		sr = SpeechRecognizer.createSpeechRecognizer(this);
		sr.setRecognitionListener(new listener());
		countDownTimer = new MyCountDownTimer(Constants.startTime, Constants.interval);
		amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		t1 = new TextToSpeech(this, this);
		t1.setOnUtteranceCompletedListener(this);
		t1.setSpeechRate((float) 0.8);//normal 1
		t1.setPitch((float) 1.8);//normal 1
		initializeVideo();
	}

	// video
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
        // TODO Auto-generated method stub

    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }


	private void initializeVideo(){
        //Displays a video file.   
        String uriPath = "android.resource://com.smartie.shrikant.smartie/"+R.raw.video5;
        Uri uri = Uri.parse(uriPath);
        mVideoView.setVideoURI(uri);
        mVideoView.requestFocus();
        mVideoView.start();
        mystartListen();
	}

    private void mystartListen(){
    	muteSpeaker();
		countDownTimer.start();
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
				"voice.recognition.test");

		intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
		sr.startListening(intent);
		Log.i("111111", "11111111");
    }


	private void muteSpeaker() {
		Log.i("onToggleIsChecked", "ToggleClick Is On");
        //turn ringer silent
        amanager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        Log.i("RINGER_MODE_SILENT", "Set to true");

        //turn off sound, disable notifications
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        Log.i("STREAM_SYSTEM", "Set to true");
        //notifications
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        Log.i("STREAM_NOTIFICATION", "Set to true");
        //alarm
        amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
        Log.i("STREAM_ALARM", "Set to true");
        //ringer
        amanager.setStreamMute(AudioManager.STREAM_RING, true);
        Log.i("STREAM_RING", "Set to true");
        //media
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        Log.i("STREAM_MUSIC", "Set to true");

	}

	private void unmuteSpeaker() {
		Log.i("onToggleIsChecked", "ToggleClick Is Off");

        //turn ringer silent
        amanager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        Log.i(".RINGER_MODE_NORMAL", "Set to true");

        // turn on sound, enable notifications
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
        Log.i("STREAM_SYSTEM", "Set to False");
        //notifications
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
        Log.i("STREAM_NOTIFICATION", "Set to False");
        //alarm
        amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
        Log.i("STREAM_ALARM", "Set to False");
        //ringer
        amanager.setStreamMute(AudioManager.STREAM_RING, false);
        Log.i("STREAM_RING", "Set to False");
        //media
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        Log.i("STREAM_MUSIC", "Set to False");

	}

	class listener implements RecognitionListener {
		public void onReadyForSpeech(Bundle params) {
			Log.d(TAG, "onReadyForSpeech");
		}

		public void onBeginningOfSpeech() {
			Log.d(TAG, "onBeginningOfSpeech");
		}

		public void onRmsChanged(float rmsdB) {
			Log.d(TAG, "onRmsChanged");
		}

		public void onBufferReceived(byte[] buffer) {
			Log.d(TAG, "onBufferReceived");
		}

		public void onEndOfSpeech() {
			Log.d(TAG, "onEndofSpeech");
		}

		public void onError(int error) {
			Log.d(TAG, "error " + error);
			//mText.setText("error " + error);
		}

		public void onResults(Bundle results) {
			String str = new String();
			Log.d(TAG, "onResults " + results);
			ArrayList data = results
					.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
			for (int i = 0; i < data.size(); i++) {
				Log.d(TAG, "result " + data.get(i));
				str += data.get(i);
			}
			// mText.setText("results: "+String.valueOf(data.size()));
			//mText.setText("results: " + String.valueOf(data.get(0)));
			userspeech = "" + String.valueOf(data.get(0));
			parsemyString response = new parsemyString();
			String responseString=response.understand(userspeech);
			if(!responseString.equals("")){
				t1.speak("" + String.valueOf(responseString), TextToSpeech.QUEUE_FLUSH,
						myHashAlarm);
			}

		}

		public void onPartialResults(Bundle partialResults) {
			Log.d(TAG, "onPartialResults");
		}

		public void onEvent(int eventType, Bundle params) {
			Log.d(TAG, "onEvent " + eventType);
		}
	}


	public class MyCountDownTimer extends CountDownTimer {
		  public MyCountDownTimer(long startTime, long interval) {
		   super(startTime, interval);
		  }

		  @Override
		  public void onFinish() {
		   unmuteSpeaker();
		  }

		  @Override
		  public void onTick(long millisUntilFinished) {

		  }
		 }


	@Override
	public void onUtteranceCompleted(String utteranceId) {
		if (utteranceId == "end of wakeup message ID") {
			initializeVideo();
	    }
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status != TextToSpeech.ERROR) {
			t1.setLanguage(Locale.UK);
		}
	}


}