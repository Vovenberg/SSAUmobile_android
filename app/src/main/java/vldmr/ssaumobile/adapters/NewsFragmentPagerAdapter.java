package vldmr.ssaumobile.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vldmr.ssaumobile.fragments.NewsFragment;

/**
 * Created by Vladimir on 31.03.2016.
 */
public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
    String[] tabs;

    public NewsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        tabs=new String[]{"Все","Новости","Объявления"} ;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return NewsFragment.getInstance(position);

            case 1:
                return NewsFragment.getInstance(position);
            case 2:
                return NewsFragment.getInstance(position);
            case 3:
                return NewsFragment.getInstance(position);

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
