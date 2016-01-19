package com.kir.wordparser;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.view.View;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;

public class MainActivity extends Activity implements SpellCheckerSessionListener {
	 Button b1;
	   TextView tv1;
	   EditText ed1;
	   private SpellCheckerSession mScs;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	ed1=(EditText) findViewById(R.id.inputLine);
	
	
	
	}
	
	
	
		 public void onGetSuggestions(final SuggestionsInfo[] arg0) {
		      final StringBuilder sb = new StringBuilder();
		      
		      for (int i = 0; i < arg0.length; ++i) {
		         // Returned suggestions are contained in SuggestionsInfo
		         final int len = arg0[i].getSuggestionsCount();
		        // sb.append('');
		         
		         for (int j = 0; j < len; ++j) {
		            sb.append("" + arg0[i].getSuggestionAt(j));
		         }
		         
		         sb.append("");
		      }
		      runOnUiThread(new Runnable() {
		         public void run() {
		        	 ed1.clearComposingText();
		            ed1.setText(sb.toString());
		         }
		      });
		   }
		   
		   @Override
		   public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] arg0) {
		      // TODO Auto-generated method stub
		   }
		   
	
	@SuppressWarnings("deprecation")
	public void parser(View v)
	
	{
		
		 mScs.getSuggestions(new TextInfo(ed1.getText().toString()), 1);
		
		
		
	}
	
	
	public void onResume() {
	      super.onResume();
	      final TextServicesManager tsm = (TextServicesManager) getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
	      mScs = tsm.newSpellCheckerSession(null, null, this, true);
	   }
	   
	   public void onPause() {
	      super.onPause();
	      if (mScs != null) {
	         mScs.close();
	      }
	   }
	
	}

