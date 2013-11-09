package de.chas0r.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import de.chas0r.androidtest.AndroidApplication.ImageDownloader;

public class MultiThreading extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multi_threading);
	}

	public void startService(View v) {
		Intent intent = new Intent(this, AndroidService.class);
		startService(intent);
	}
	
	public void stopService(View v) {
		Intent intent = new Intent(this, AndroidService.class);
		stopService(intent);
	}
	
	public void pauseThread(View v) {
		AndroidApplication app = new AndroidApplication();
		ImageView image = (ImageView) findViewById(R.id.downloadedImage);
		ImageDownloader imageLoader = app.new ImageDownloader(image);
		imageLoader.execute("http://developer.android.com/images/training/basics/basic-lifecycle.png");
	}
}
