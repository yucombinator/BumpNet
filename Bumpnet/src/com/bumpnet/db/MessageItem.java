package com.bumpnet.db;

import android.graphics.drawable.Drawable;

public class MessageItem{
	public Drawable image;
	public String title;
	public String message;
	
	public Drawable getIcon(){
		return image;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getMessage(){
		return message;
	}
	
}
