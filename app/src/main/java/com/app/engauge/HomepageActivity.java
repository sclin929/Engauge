package com.app.engauge;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Maury on 11/14/14.
 */
public class HomepageActivity extends FragmentActivity {
    HomepageFragmentPagerAdapter fragmentPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // Set the pager so that users can swipe left or right.
        fragmentPagerAdapter =
                new HomepageFragmentPagerAdapter(
                        getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(fragmentPagerAdapter);

        // Set the listeners to the tabs at the top of our activity.
        ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                // Required to use tab listener.
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                // Required to use tab listener.
            }
        };

        // Set the listeners to the view pager.
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between pages, select the
                // corresponding tab.
                getActionBar().setSelectedNavigationItem(position);
            }
        });

        Resources resources = getResources();

        ActionBar.Tab favoritesTab = bar.newTab();
        favoritesTab.setText(resources.getString(R.string.favorites));
        favoritesTab.setTabListener(tabListener);
        bar.addTab(favoritesTab);

        ActionBar.Tab recentTab = bar.newTab();
        recentTab.setText(resources.getString(R.string.recent));
        recentTab.setTabListener(tabListener);
        bar.addTab(recentTab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_homepage_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
