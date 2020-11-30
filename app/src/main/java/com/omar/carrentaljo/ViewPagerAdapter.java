package com.omar.carrentaljo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 14/03/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> TitList=new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return TitList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TitList.get(position);
    }


    public void addFragment(Fragment fragment, String title)
    {
        fragmentList.add(fragment);
        TitList.add(title);

    }




}
