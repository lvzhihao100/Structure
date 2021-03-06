package com.gamerole.commom.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * Created by lvzhihao on 17-4-27.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> title;
    private List<Fragment> views;

    public MyFragmentPagerAdapter(FragmentManager fm, List<String> title, List<Fragment> views) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.title = title;
        this.views = views;
    }

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> views) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.views = views;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }


    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return title == null ? "" : title.get(position);
    }
}
