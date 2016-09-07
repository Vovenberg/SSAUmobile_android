package vldmr.ssaumobile.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vldmr.ssaumobile.activities.Events;
import vldmr.ssaumobile.fragments.EventsFragment;
import vldmr.ssaumobile.fragments.MediaFragment;

/**
 * Created by Vladimir on 03.04.2016.
 */
public class EventsFragmentPagerAdapter extends FragmentPagerAdapter {

    String[] tabs;

    public EventsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        tabs=new String[]{"Планируются","Прошедшие"} ;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return EventsFragment.getInstance(position);

            case 1:
                return EventsFragment.getInstance(position);
            case 2:
                return EventsFragment.getInstance(position);
            case 3:
                return EventsFragment.getInstance(position);

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
