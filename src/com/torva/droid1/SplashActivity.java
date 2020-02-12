package com.torva.droid1;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		TextView VersionSplash = (TextView)findViewById(R.id.textView2);
		Animation VersionSplashAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		VersionSplash.startAnimation(VersionSplashAnimation);
		
		ImageView Skip = (ImageView)findViewById(R.id.Logo);
		Skip.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),MenuActivity.class));
				finish();
			}
		});
		
		Timer timer1 = new Timer();
		timer1.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),MenuActivity.class));
				finish();
			}
		}, 3500);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}

}
