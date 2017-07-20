package com.aojing.main_module.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aojing.main_module.fragment.Fragment_community;
import com.aojing.main_module.fragment.Fragment_my;
import com.aojing.main_module.fragment.Fragment_property;


/**
 * 主页面的地步导航实现的适配器
 * Created by wxw on 2016/12/30.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"社区","房产","我"};
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1){
            return new Fragment_property();
        }else if (position == 2){
            return new Fragment_my();
        }
        return new Fragment_community();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
//ViewPager与TabLayout绑定后，获取的PageTitle就是Tab 的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
