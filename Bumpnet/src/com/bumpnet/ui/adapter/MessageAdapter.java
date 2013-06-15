package com.bumpnet.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumpnet.R;
import com.bumpnet.db.MessageItem;

public class MessageAdapter extends BaseAdapter {

    static class ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        TextView tvMessage;
        Button bAction1;
        Button bAction2;
        Button bAction3;
    }
    
    private List<MessageItem> data;
    private Context context;

    public MessageAdapter(Context context, List<MessageItem> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public MessageItem getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MessageItem item = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.row_style, parent, false);
            holder = new ViewHolder();
            holder.ivImage = (ImageView) convertView.findViewById(R.id.row_image);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.row_title);
            holder.tvMessage = (TextView) convertView.findViewById(R.id.row_description);
            holder.bAction1 = (Button) convertView.findViewById(R.id.example_row_b_action_1);
            holder.bAction2 = (Button) convertView.findViewById(R.id.example_row_b_action_2);
            holder.bAction3 = (Button) convertView.findViewById(R.id.example_row_b_action_3);
            convertView.setTag(holder);
            Log.d("BumpNet", "Holder assignments set");
        } else {
            holder = (ViewHolder) convertView.getTag();
            Log.d("BumpNet", "Holder assignments not set");
        }

        holder.ivImage.setImageDrawable(item.getIcon());
        holder.tvTitle.setText(item.getTitle());
        holder.tvMessage.setText(item.getMessage());


        holder.bAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.bAction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            }

        });

        holder.bAction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }
}