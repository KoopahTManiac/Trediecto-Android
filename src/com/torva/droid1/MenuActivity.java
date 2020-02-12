package com.torva.droid1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		ImageView Start = (ImageView)findViewById(R.id.start);
		Start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent game = new Intent(getApplicationContext(),GameActivity.class);
				EditText editor = (EditText)findViewById(R.id.UsernameField);
				if(editor.getText().toString() != null || editor.getText().toString() !="")
				{
					game.putExtra("Username", editor.getText().toString());
					editor = (EditText)findViewById(R.id.PasswordField);
					if(editor.getText().toString() != null || editor.getText().toString() !="")
					{
						game.putExtra("Password", editor.getText().toString());
						
						startActivity(game);
						finish();
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

}
