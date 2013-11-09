package de.chas0r.androidtest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SideFragment extends Fragment {
	View view;
	SQLiteDatabase db;
	DbHelper dbhelper;
	ListView list;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		dbhelper = new DbHelper(getActivity());
		db = dbhelper.getWritableDatabase();
		view = inflater.inflate(R.layout.side_fragment, container, false);
		list = (ListView) view.findViewById(R.id.commentsList);
		String[] columns = {DbHelper.C_ID, DbHelper.NAME, DbHelper.COMMENT, DbHelper.EMAIL};
		String[] from = {DbHelper.NAME, DbHelper.COMMENT, DbHelper.EMAIL};
		int[] to = {R.id.name, R.id.comment, R.id.email};
		Cursor cursor = db.query(DbHelper.TABLE_NAME, columns, null, null, null, null, null);
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_row, cursor, from, to);
		list.setAdapter(adapter);
		
		Button comment = (Button) view.findViewById(R.id.newtread);
		comment.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				String name,comment,email;
				EditText nameIn = (EditText) view.findViewById(R.id.nameIn);
				EditText commentIn = (EditText) view.findViewById(R.id.commentIn);
				EditText emailIn = (EditText) view.findViewById(R.id.emailIn);
				
				name = nameIn.getText().toString();
				comment = commentIn.getText().toString();
				email = emailIn.getText().toString(); 
				
				ContentValues cv = new ContentValues();

				cv.put(DbHelper.NAME, name);
				cv.put(DbHelper.COMMENT, comment);
				cv.put(DbHelper.EMAIL, email);
				
				db.insert(DbHelper.TABLE_NAME, null, cv);
			}
		});
		
		Button query = (Button) view.findViewById(R.id.query);
		query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String[] columns = {DbHelper.NAME, DbHelper.COMMENT, DbHelper.EMAIL};
				Cursor cursor = db.query(DbHelper.TABLE_NAME, columns, null, null, null, null, null);
				cursor.moveToFirst();
				
				while(cursor.moveToNext()){
					String name = cursor.getString(cursor.getColumnIndex(DbHelper.NAME));
					String comment = cursor.getString(cursor.getColumnIndex(DbHelper.COMMENT));
					String email = cursor.getString(cursor.getColumnIndex(DbHelper.EMAIL));
					
					Toast.makeText(getActivity(),"Name: "+ name +"\nComment: "+ comment,Toast.LENGTH_SHORT).show();
				}
				
				cursor.close();
				
			}
		});
		
		return view;
	}
}
