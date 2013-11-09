
package de.chas0r.androidtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CommentsActivity extends BaseActivity {
	
	TextView nameOut;
	
	Fragment picture = new PictureFragment();
	Fragment side = new SideFragment();

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_activity, menu);
		return true;
	}
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_activity_frame);
		FragmentManager frgman = getSupportFragmentManager();
		FragmentTransaction frgtrans = frgman.beginTransaction();
		
		frgtrans.replace(R.id.frag_frame, side,"SIDE");
		frgtrans.commit();
	}

	public void swapFrag(View v) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		Fragment current = fragmentManager.findFragmentByTag("SIDE");
		
		if(current.isVisible()) {
			fragmentTransaction.replace(R.id.frag_frame, picture, "PIC");
		} else {
			fragmentTransaction.replace(R.id.frag_frame, side, "SIDE");
		}
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	} 
	
	public void addComment (View v) {
		String name,comment,password;
		EditText nameIn = (EditText) findViewById(R.id.nameIn);
		EditText commentIn = (EditText) findViewById(R.id.commentIn);
		EditText passwordIn = (EditText) findViewById(R.id.emailIn);
		
		name = nameIn.getText().toString();
		comment = commentIn.getText().toString();
		password = passwordIn.getText().toString(); 
		

		TextView nameOut = (TextView) findViewById(R.id.name);
		TextView commentOut = (TextView) findViewById(R.id.comment);
		TextView passwordOut = (TextView) findViewById(R.id.email);
		
		nameOut.setText(name);
		commentOut.setText(comment);
		passwordOut.setText(password);
	}
}
