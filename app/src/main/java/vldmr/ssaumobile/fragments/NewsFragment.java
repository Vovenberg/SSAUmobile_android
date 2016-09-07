package vldmr.ssaumobile.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.HashMap;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.activities.Item;
import vldmr.ssaumobile.adapters.MyListAdapter;
import vldmr.ssaumobile.database.DatabaseHelperFactory;
import vldmr.ssaumobile.database.NewsEntity;

/**
 * Created by Vladimir on 03.04.2016.
 */
public class NewsFragment extends Fragment {

    static final int LAYOUT= R.layout.news_all_fragment;
    static final int LAYOUT2= R.layout.news_news_fragment;
    static final int LAYOUT3= R.layout.news_ads_fragment;
    View view;
    int i;
    ListView  listView;
    ListView  listViewNewsOnly;
    ListView  listViewAdsOnly;


    public static NewsFragment getInstance(int i){
        NewsFragment newsFragment=new NewsFragment();
        Bundle b=new Bundle();b.putInt("tab",i);
        newsFragment.setArguments(b);

        return newsFragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        switch (getArguments().getInt("tab")){
            case 0:{view=inflater.inflate(LAYOUT,container,false);
                listView=(ListView) view.findViewById(R.id.listView);
                try {
                    ListAdapter adapter=MyListAdapter.getNewsAdapter(getContext());
                    listView.setAdapter(adapter);

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getContext(), Item.class);
                        HashMap map= (HashMap)listView.getItemAtPosition(position);

                        String s= (String) map.get("Title");
                        String s2= (String) map.get("Date");
                        NewsEntity newsEntity = null;
                        try {
                            newsEntity=DatabaseHelperFactory.getHelper().getNewsDAO().queryForId((Integer) map.get("Id"));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        intent.putExtra("Title",s);
                        intent.putExtra("Date",s2);
                        intent.putExtra("Text",newsEntity.getPubText());
                        intent.putExtra("Tags",newsEntity.getTags());
                        intent.putExtra("Id",newsEntity.getId());

                        startActivity(intent);
                    }
                });

                break;}
            case 1:{view=inflater.inflate(LAYOUT2,container,false);
                listViewNewsOnly=(ListView) view.findViewById(R.id.listViewNewsOnly);
                try {
                    listViewNewsOnly.setAdapter(MyListAdapter.getNewsAdapterNewsOny(getContext()));
                    listViewNewsOnly.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(getContext(), Item.class);
                            HashMap map= (HashMap)listViewNewsOnly.getItemAtPosition(position);

                            String s= (String) map.get("Title");
                            String s2= (String) map.get("Date");
                            NewsEntity newsEntity = null;
                            try {
                                newsEntity=DatabaseHelperFactory.getHelper().getNewsDAO().queryForId((Integer) map.get("Id"));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            intent.putExtra("Title",s);
                            intent.putExtra("Date",s2);
                            intent.putExtra("Text",newsEntity.getPubText());
                            intent.putExtra("Tags",newsEntity.getTags());
                            intent.putExtra("Id",newsEntity.getId());

                            startActivity(intent);
                        }
                    });
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }


                break;}
            case 2:{view=inflater.inflate(LAYOUT3,container,false);
                listViewAdsOnly=(ListView) view.findViewById(R.id.listViewAdsOnly);
                try {
                    listViewAdsOnly.setAdapter(MyListAdapter.getNewsAdapterAdsOnly(getContext()));
                    listViewAdsOnly.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(getContext(), Item.class);
                            HashMap map= (HashMap)listViewAdsOnly.getItemAtPosition(position);

                            String s= (String) map.get("Title");
                            String s2= (String) map.get("Date");
                            NewsEntity newsEntity = null;
                            try {
                                newsEntity=DatabaseHelperFactory.getHelper().getNewsDAO().queryForId((Integer) map.get("Id"));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            intent.putExtra("Title",s);
                            intent.putExtra("Date",s2);
                            intent.putExtra("Text",newsEntity.getPubText());
                            intent.putExtra("Tags",newsEntity.getTags());
                            intent.putExtra("Id",newsEntity.getId());

                            startActivity(intent);
                        }
                    });
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                break;}

        }
        return view;
    }



}
