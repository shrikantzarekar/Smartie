package com.smartie.shrikant.smartie;

import java.util.HashMap;

import android.app.Activity;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;


public class TTSActivity extends Activity implements OnInitListener, OnUtteranceCompletedListener{
private TextToSpeech mTts;

private void speak(String text) {
   if(text != null) {
      HashMap<String, String> myHashAlarm = new HashMap<String, String>();
      myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
      myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "SOME MESSAGE");
      mTts.speak(text, TextToSpeech.QUEUE_FLUSH, myHashAlarm);
   }
}

@Override
public void onUtteranceCompleted(String utteranceId) {
	// TODO Auto-generated method stub
	
}

@Override
public void onInit(int status) {
	// TODO Auto-generated method stub
	
}

}
