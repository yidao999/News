package com.example.myapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myapplication.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 小川
 * Date: 2019/5/14
 * Description:
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragment= new ArrayList<>();
    private List<String> mFragmentTitles = new ArrayList<>();
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(BaseFragment fragment,String title){
        mFragment.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}
