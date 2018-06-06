package com.blogspot.amrabuelhamd.EgyptTourGuide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.blogspot.amrabuelhamd.EgyptTourGuide.Fragments.ArabicPhrasesFragment;
import com.blogspot.amrabuelhamd.EgyptTourGuide.Fragments.HotelsFragment;
import com.blogspot.amrabuelhamd.EgyptTourGuide.Fragments.ResturantFragment;
import com.blogspot.amrabuelhamd.EgyptTourGuide.Fragments.TopSightsFragment;

/**
 * Created by amr_aboelhamd on 07/02/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 3)
            return new ArabicPhrasesFragment();
        else if (position==1)
            return new HotelsFragment();
        else if (position == 0)
            return new TopSightsFragment();
        else
            return new ResturantFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return "Top Sights";
        else if(position == 1)
            return "Magnificent Hotels";
        else if(position == 2)
            return "Aswan Best Hotels";
        else
            return "Egyptian Common phrases";
    }
}
