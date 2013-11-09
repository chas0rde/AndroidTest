package de.chas0r.androidtest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

public class AndroidApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "Application onCreate", Toast.LENGTH_SHORT).show();
	}
	
	public class SecondThread extends Thread {
		public void run() {
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class ImageDownloader extends AsyncTask <String, Void, Bitmap>{

		ImageView image;
		public ImageDownloader(ImageView  view) {
				image = view;
				view = null;
		}
		@Override
		protected Bitmap doInBackground(String... url) {
			String url2 = url[0];
			Bitmap bitmap = null;
			byte[] imageData = getImageFromUrl(url2);
			ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
			bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream), 60, 60, true);
			
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			image.setImageBitmap(bitmap);
		}
	}
	
	public byte[] getImageFromUrl(String url){
		InputStream in = null;
		byte[] byteimage = null;
		try {
			URL imageUrl = new URL(url);
			URLConnection conn = imageUrl.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			
			int response = httpConn.getResponseCode();
			
			if(response == HttpsURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
			
			int nRead;
			byte[] data = new byte[16384];
			
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			
			while((nRead = in.read(data, 0, data.length)) != -1){
				buffer.write(data, 0, nRead);
			}
			
			buffer.flush();
			
			byteimage = buffer.toByteArray();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return byteimage;
	}
}
