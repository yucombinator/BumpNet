
package com.bumpnet;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.bumpnet.ui.MessageListFragment;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity
    extends SherlockFragmentActivity
{

    private String[] locations;

    @AfterViews
    void afterViews() {
        locations = getResources().getStringArray(R.array.locations);
        configureActionBar();
    	getSupportFragmentManager()
        .beginTransaction()
        .add(R.id.container, new MessageListFragment(),"frag_list")
        .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater();
        return true;
    }
    public void onPageSelected(int position) {
        Tab tab = getSupportActionBar().getTabAt(position);
        getSupportActionBar().selectTab(tab);
    }

    private void configureActionBar() {
      //  getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    }
}
