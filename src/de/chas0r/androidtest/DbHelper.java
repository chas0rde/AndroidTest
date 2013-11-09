package de.chas0r.androidtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "data";
	public static final String TABLE_NAME = "comments_table";
	public static final String C_ID = "_id";
	public static final String NAME = "name";
	public static final String COMMENT = "comment";
	public static final String EMAIL = "email";
	public static final String TIME = "time";
	public static final int VERSION = 1;
	
	private final String createDb ="create table if not exists "+ TABLE_NAME + " ("
			+ C_ID + " integer primary key autoincrement, "
			+ NAME + " text, "
			+ EMAIL + " text, "
			+ COMMENT + " text, "
			+ TIME + " text" +
					");";
	
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createDb);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		db.execSQL("drop table " + TABLE_NAME);
	}

}
