package de.chas0r.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends BaseActivity {

	private final String TAG = "Main Activity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "Main activity created");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void buttonClick (View v) {
		Intent intent = new Intent (this, CommentsActivity.class);
		intent.putExtra("screenText", "HelloWorld");
		startActivity(intent);
	}
	
	public void multiThread (View v) {
		Intent intent = new Intent (this, MultiThreading.class);
		startActivity(intent);
	}
}
