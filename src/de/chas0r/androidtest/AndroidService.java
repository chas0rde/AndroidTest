package de.chas0r.androidtest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AndroidService extends Service {

	public AndroidService() {
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toaster toaster  = new Toaster(this);
		toaster.start();
		return startId;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	private class Toaster extends Thread {
		Context context;
		
		public Toaster(Context context) {
			this.context = context;
		}
		
		public void run(){
			while(true){
				Log.i("Service", "bäm...headshot");
				try{ 
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
