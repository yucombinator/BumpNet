package com.bumpnet.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

public class MessageItem{
	public Drawable image;
	public String title;
	private String m_message;
	private int m_parentId;
	private int m_id;

	MessageItem(Context context, int id) {
		m_id = id;
		MessageOpenHelper db = new MessageOpenHelper(context);
		
		SQLiteDatabase readableDB = db.getReadableDatabase();
		String[] selection = {"message_text", "parent_id"};
		String[] selectionArgs = {"" + m_id};
		Cursor matches = readableDB.query(MessageOpenHelper.MESSAGES_TABLE_NAME, selection,
				"id = ?", selectionArgs, null, null, null, null);
		m_message = matches.getString(matches.getColumnIndex("message_text"));
		if(matches.isNull(matches.getColumnIndex("parent_id"))){
			m_parentId = -1;
		} else {
			m_parentId = matches.getInt(matches.getColumnIndex("parent_id"));
		}
		matches.close();
		readableDB.close();
	}
	
	private MessageItem(Context context, int id, String message_text, int parentId) {
		m_id = id;
		m_message = message_text;
		m_parentId = parentId;
	}
	
	public MessageItem[] getReplies(Context context) {
		MessageOpenHelper db = new MessageOpenHelper(context);
		
		SQLiteDatabase readableDB = db.getReadableDatabase();
		String[] selection = {"id", "message_text", "parent_id"};
		String[] selectionArgs = {"" + m_id};
		Cursor matches = readableDB.query(MessageOpenHelper.MESSAGES_TABLE_NAME, selection,
				"parent_id = ?", selectionArgs, null, null, null, null);
		
		int replyCount = matches.getCount();
		int i = 0;
		MessageItem[] replies = new MessageItem[replyCount];
		do {
			replies[i] = new MessageItem(context, matches.getInt(0), matches.getString(1), matches.getInt(2));
			i++;
		} while(matches.moveToNext());
		matches.close();
		readableDB.close();
		
		return replies;
	}
	
	public static MessageItem[] getTopLevelMessages(Context context) {
		MessageOpenHelper db = new MessageOpenHelper(context);
		
		SQLiteDatabase readableDB = db.getReadableDatabase();
		String[] selection = {"id"};
		Cursor matches = readableDB.query(MessageOpenHelper.MESSAGES_TABLE_NAME, selection,
				"parent_id IS NULL", new String[] {}, null, null, null, null);
		
		int topCount = matches.getCount();
		int i = 0;
		MessageItem[] topLevelMessages = new MessageItem[topCount];
		do {
			topLevelMessages[i] = new MessageItem(context, matches.getInt(0));
			i++;
		} while(matches.moveToNext());
		matches.close();
		readableDB.close();
		
		return topLevelMessages;
	}
	
	public int getParentId() {
		return m_parentId;
	}
	
	public Drawable getIcon(){
		return image;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getMessage(){
		return m_message;
	}
	
}
