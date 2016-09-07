package vldmr.ssaumobile.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.sql.SQLException;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.adapters.MyListAdapter;

/**
 * Created by Vladimir on 03.04.2016.
 */
public class EventsFragment extends Fragment {
    static final int LAYOUT= R.layout.events_upcoming_fragment;
    static final int LAYOUT2= R.layout.events_past_fragment;
    View view;
    ListView  listView;

    public static EventsFragment getInstance(int i){
        EventsFragment mediaFragment =new EventsFragment();
        Bundle b=new Bundle();b.putInt("tab",i);
        mediaFragment.setArguments(b);

        return mediaFragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        switch (getArguments().getInt("tab")){
            case 0:{view=inflater.inflate(LAYOUT,container,false);
                listView=(ListView) view.findViewById(R.id.listView_events_upcoming);
                ListAdapter adapter= MyListAdapter.getEvents(getContext());
                listView.setAdapter(adapter);

                break;}
            case 1:{view=inflater.inflate(LAYOUT2,container,false);
                break;}

        }

        return view;
    }


}
