package com.bumpnet.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumpnet.R;
import com.bumpnet.db.MessageItem;
import com.bumpnet.ui.adapter.MessageAdapter;
import com.fortysevendeg.android.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.android.swipelistview.SwipeListView;

public class MessageListFragment extends Fragment {
	private MessageAdapter adapter;
    private List<MessageItem> data;

    private SwipeListView swipeListView;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.fragment_message_list, container, false);
        data = new ArrayList<MessageItem>();
        initializeDummyData();
        
        //Set up the adapter
        adapter = new MessageAdapter(getActivity().getApplicationContext(), data); //getActivity() is a context

        swipeListView = (SwipeListView) view.findViewById(R.id.msg_list);
        
        //Listener for infinite scrolling
        swipeListView.setOnScrollListener(new OnScrollListener(){

			@Override
			public void onScroll(AbsListView arg0, int firstVisible, int visibleCount, int totalCount) {
		        boolean loadMore = /* maybe add a padding */
		                firstVisible + visibleCount >= totalCount;
		                //infinite loading
		            if(loadMore) {
		            	initializeDummyData();
		                adapter.notifyDataSetChanged();
		            }
				
			}

			@Override
			public void onScrollStateChanged(AbsListView v, int scrollState) {
				// TODO Auto-generated method stub
				
			}
        });
        
        //Listener for swipe events
            swipeListView.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onOpened(int position, boolean toRight) {
            }

            @Override
            public void onClosed(int position, boolean fromRight) {
            }

            @Override
            public void onListChanged() {
            }

            @Override
            public void onMove(int position, float x) {
            }

            @Override
            public void onStartOpen(int position, int action, boolean right) {
               // Log.d("swipe", String.format("onStartOpen %d - action %d", position, action));
            }

            @Override
            public void onStartClose(int position, boolean right) {
              //  Log.d("swipe", String.format("onStartClose %d", position));
            }

            @Override
            public void onClickFrontView(int position) {
               // Log.d("swipe", String.format("onClickFrontView %d", position));
            }

            @Override
            public void onClickBackView(int position) {
              //  Log.d("swipe", String.format("onClickBackView %d", position));
            }

            @Override
            public void onDismiss(int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    data.remove(position);
                }
                adapter.notifyDataSetChanged();
            }
        });

        swipeListView.setAdapter(adapter);
        //Remove line between listview elements
        swipeListView.setDivider(null);
        swipeListView.setDividerHeight(0);
        //Update the ListView with array elements
        adapter.notifyDataSetChanged();

        return view;
    }
    
    
	@Override
	public void onPause() {
	    super.onPause();
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	}
	
    
    @Override
    public void onDestroyView(){
    	super.onDestroyView();
    }

	private void initializeDummyData() {
		MessageItem item = MessageItem.getDummyMessage();
		item.image = getResources().getDrawable(R.drawable.ic_launcher);
	
		data.add(item);
		data.add(item);
		data.add(item);
		data.add(item);
		data.add(item);
		
		Log.d("BumpNet", "Size of data: " + data.size());
	}
	}