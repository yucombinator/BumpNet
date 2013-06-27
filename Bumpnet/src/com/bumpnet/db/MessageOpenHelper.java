package com.bumpnet.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MessageOpenHelper extends SQLiteOpenHelper {

	private static final String TAG = "MessageOpenHelper"; //For log messages
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "bumpnetMessageDB";
	public static final String MESSAGES_TABLE_NAME = "messages";
	private static final String CREATE_MESSAGE_DATABASE = "CREATE TABLE " + MESSAGES_TABLE_NAME + "(" +
			"id INTEGER PRIMARY KEY, " +
			"message_title TEXT, " +
			"message_text TEXT, " +
			"parent_id INTEGER" + ");";
	
	public MessageOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_MESSAGE_DATABASE);
		Log.v(TAG, "Database created");
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Requested database upgrade from version" + oldVersion + " to " + newVersion);
		Log.w(TAG, "This Database has only one known version");
	}

}
