package com.smartie.shrikant.smartie;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;


import java.io.File;

public class ChatBotActivity extends Activity {
    
    private static final String TAG = "MainActivity";
    
    String botname = "alice2";
    EditText question;
    Button btn;
    String request = "Hello. Are you alive? What is your name?";
    Bot bot;
    Chat chatSession;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbot);
        new Setup().execute();
       

        
        
        
    }
    class GetResponse extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            
          //  String request = "Tell me a joke";
        	//bot = new Bot(botname, path);
           // chatSession = new Chat(bot);
            String response = chatSession.multisentenceRespond(request);

            Log.v(TAG, "response = " + response);
            return response;
        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
		@Override
        protected void onPostExecute(String response) {
            if (response.isEmpty()) {
                response = "There is no response";
            }
            ((TextView) findViewById(R.id.title_text))
                    .setText(response);

        }
    }
class Setup extends AsyncTask<Void, Void, String> {
        

		@Override
        protected String doInBackground(Void... params) {
			 File fileExt = new File(getExternalFilesDir(null).getAbsolutePath() + "/bots");
		        if (!fileExt.exists()) {
		            ZipFileExtraction extract = new ZipFileExtraction();

		            try {
		                extract.unZipIt(getAssets().open("bots.zip"), getExternalFilesDir(null).getAbsolutePath() + "/");
		            } catch (Exception e) {
		                e.printStackTrace();
		                return "";
		            }
		        }
		    path = getExternalFilesDir(null).getAbsolutePath();
        	bot = new Bot(botname, path);
            chatSession = new Chat(bot);
            String response="a";
            return response;
        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
		@Override
        protected void onPostExecute(String response) {
            if (!response.isEmpty()) {
                response = "Ready";
                question = (EditText) findViewById(R.id.editText1);
                btn = (Button) findViewById(R.id.button1);
                ((TextView) findViewById(R.id.title_text))
                .setText(response);
                btn.setOnClickListener(new OnClickListener() {
        			
        			@Override
        			public void onClick(View v) {
        				// TODO Auto-generated method stub
        				request = question.getText().toString();
        				new GetResponse().execute();
        				question.setText("");
        				
        			}
        		});
            }
        }
    }



    }
