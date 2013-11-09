package de.chas0r.androidtest;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends FragmentActivity {

	private final String TAG = "Base Activity";
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.twitter:
			Log.i(TAG, "Twitter Item Clicked");
			return true;
		case R.id.facebook:
			Log.i(TAG, "Facebook Item Clicked");
			return true;
		case R.id.refresh:
			Log.i(TAG, "Refresh Item Clicked");
			return true;
		default:
			return super.onOptionsItemSelected(item);	
		}
	}
}
