package com.bumpnet.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

public class MessageItem{
	private static final String DUMMY_TITLE = "Lorem ipsum dolor sit amet";
	private static final String DUMMY_TEXT = "Curabitur eros lectus, feugiat ac tempor dapibus, pellentesque id sapien. " +
			"Fusce blandit pellentesque enim ac adipiscing. " +
			"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
			"Vestibulum dignissim nulla at magna pulvinar sodales. Nam ullamcorper cursus lacinia. " +
			"Duis scelerisque, lacus ac pretium pretium, nisi lorem vulputate metus, nec auctor justo turpis non lorem. " +
			"Vestibulum posuere, turpis sit amet vehicula consequat, nisl dui lobortis dui, eget suscipit metus lectus quis arcu. " +
			"Proin auctor eu ligula sed pharetra. Integer in magna eget nisi scelerisque fermentum. Nunc in dui id elit posuere ornare. " +
			"Ut felis metus, lobortis nec tincidunt ornare, condimentum non ipsum. Aenean luctus urna quis urna rutrum, nec blandit massa varius. " +
			"Sed porta eu ligula vitae tempor. Donec tristique, risus sit amet semper euismod, velit eros iaculis arcu, non pharetra nisl massa nec turpis." +
			" Vestibulum vestibulum sem sit amet ante vulputate viverra.";
	
	public Drawable image;
	private String m_title;
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
	
	private MessageItem(int id, String messageTitle, String messageText, int parentId) {
		m_id = id;
		m_title = messageTitle;
		m_message = messageText;
		m_parentId = parentId;
	}
	
	public MessageItem[] getReplies(Context context) {
		MessageOpenHelper db = new MessageOpenHelper(context);
		
		SQLiteDatabase readableDB = db.getReadableDatabase();
		String[] selection = {"id", "message_title", "message_text", "parent_id"};
		String[] selectionArgs = {"" + m_id};
		Cursor matches = readableDB.query(MessageOpenHelper.MESSAGES_TABLE_NAME, selection,
				"parent_id = ?", selectionArgs, null, null, null, null);
		
		int replyCount = matches.getCount();
		int i = 0;
		MessageItem[] replies = new MessageItem[replyCount];
		do {
			replies[i] = new MessageItem(matches.getInt(0), matches.getString(1), 
					matches.getString(2), matches.getInt(3));
			i++;
		} while(matches.moveToNext());
		matches.close();
		readableDB.close();
		
		return replies;
	}
	
	public static MessageItem[] getTopLevelMessages(Context context) {
		MessageOpenHelper db = new MessageOpenHelper(context);
		
		SQLiteDatabase readableDB = db.getReadableDatabase();
		String[] selection = {"id", "message_title", "message_text", "parent_id"};
		Cursor matches = readableDB.query(MessageOpenHelper.MESSAGES_TABLE_NAME, selection,
				"parent_id IS NULL", new String[] {}, null, null, null, null);
		
		int topCount = matches.getCount();
		int i = 0;
		MessageItem[] topLevelMessages = new MessageItem[topCount];
		do {
			topLevelMessages[i] = new MessageItem(matches.getInt(0), matches.getString(1), 
					matches.getString(2), matches.getInt(3));
			i++;
		} while(matches.moveToNext());
		matches.close();
		readableDB.close();
		
		return topLevelMessages;
	}
	
	public static MessageItem getDummyMessage() {
		return new MessageItem(42, DUMMY_TITLE, DUMMY_TEXT, -1);
	}
	
	public int getParentId() {
		return m_parentId;
	}
	
	public Drawable getIcon(){
		return image;
	}
	
	public String getTitle(){
		return m_title;
	}
	
	public String getMessage(){
		return m_message;
	}
	
}
