package com.kim344.utils.tabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;

    public ContentsPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentGame();
            case 2:
                return new FragmentMovie();
            case 3:
                return new FragmentBook();
            case 4:
                return new FragmentNews();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mPageCount;
    }

}