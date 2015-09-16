package com.tuacy.wuyunxing.tuacydemo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tuacy.wuyunxing.tuacydemo.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: tuacy
 * @date: 2015/9/16 17:14
 * @version: V1.0
 */
public class NavigationViewActivity extends ActionBarActivity {

	private Toolbar               mToolbar;
	private NavigationView        mNavigationView;
	private DrawerLayout          mDrawerLayout;
	private TabLayout             mTabLayout;
	private ViewPager             mViewPager;
	private ActionBarDrawerToggle mDrawerToggle;

	List<String> mTitles = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigationview);
		initView();
	}

	private void initView() {
		mToolbar = (Toolbar) this.findViewById(R.id.tool_bar);
		mToolbar.setTitle("material design demo");
		setSupportActionBar(mToolbar);
		mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
		mNavigationView = (NavigationView) this.findViewById(R.id.navigation_view);
		mNavigationView.setNavigationItemSelectedListener(mNavigationListener);
		mTabLayout = (TabLayout) this.findViewById(R.id.tab_layout);
		mViewPager = (ViewPager) this.findViewById(R.id.view_pager);
		initDrawer();
		mTitles.add("test1");
		mTitles.add("test2");
		mTitles.add("test3");
		mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(0)));
		mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(1)));
		mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(2)));

		FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public Fragment getItem(int position) {
				return BaseFragment.newInstance(mTitles.get(position));
			}

			@Override
			public int getCount() {
				return mTitles.size();
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return mTitles.get(position);
			}
		};
		mViewPager.setAdapter(adapter);
		mTabLayout.setupWithViewPager(mViewPager);
		mTabLayout.setTabsFromPagerAdapter(adapter);
	}

	private void initDrawer() {
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				mToolbar.setTitle(getString(R.string.drawer_open));
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				mToolbar.setTitle(getString(R.string.drawer_close));
			}
		};
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private NavigationView.OnNavigationItemSelectedListener mNavigationListener = new NavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(MenuItem menuItem) {

			switch (menuItem.getItemId()) {
				case R.id.menu_more:
					mViewPager.setCurrentItem(0);
					break;
				case R.id.menu_share:
					mViewPager.setCurrentItem(1);
					break;
				case R.id.menu_collect:
					mViewPager.setCurrentItem(2);
					break;
				case R.id.menu_setting:
					Snackbar.make(getCurrentFocus(), "click" + menuItem.getTitle(), Snackbar.LENGTH_SHORT)
							.setAction("Right", new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									Toast.makeText(NavigationViewActivity.this, "Yes", Toast.LENGTH_SHORT).show();
								}
							})
							.show();
					break;
				case R.id.sub_menu1:
					Snackbar.make(getCurrentFocus(), "click" + menuItem.getTitle(), Snackbar.LENGTH_SHORT)
							.setAction("Right", new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									Toast.makeText(NavigationViewActivity.this, "Yes", Toast.LENGTH_SHORT).show();
								}
							})
							.show();
					break;
				case R.id.sub_menu2:
					Snackbar.make(getCurrentFocus(), "click" + menuItem.getTitle(), Snackbar.LENGTH_SHORT)
							.setAction("Right", new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									Toast.makeText(NavigationViewActivity.this, "Yes", Toast.LENGTH_SHORT).show();
								}
							})
							.show();
					break;
				default:
					break;
			}
			mDrawerLayout.closeDrawers();
			return true;
		}
	};
}
