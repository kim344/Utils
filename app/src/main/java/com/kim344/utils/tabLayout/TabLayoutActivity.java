package com.kim344.utils.tabLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.kim344.utils.R;

public class TabLayoutActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    TabLayout mTabLayout;
    ContentsPagerAdapter mContentsPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        mTabLayout = (TabLayout) findViewById(R.id.layout_tab);
        mViewPager = (ViewPager) findViewById(R.id.pager_content);

        mTabLayout.addTab(mTabLayout.newTab().setText("Home"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Game"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Movie"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Book"));
        mTabLayout.addTab(mTabLayout.newTab().setText("News"));

        mContentsPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());

        mViewPager.setAdapter(mContentsPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //탭이 선택 되었을때, 호출
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //탭이 선택되지 않았을때, 호출
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //탭이 다시 선택 되었을때, 호출
            }

        });

    }

}
