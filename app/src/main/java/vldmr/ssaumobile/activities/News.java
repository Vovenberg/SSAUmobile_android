package vldmr.ssaumobile.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.adapters.MyListAdapter;
import vldmr.ssaumobile.adapters.NewsFragmentPagerAdapter;
import vldmr.ssaumobile.database.DatabaseHelper;
import vldmr.ssaumobile.database.DatabaseHelperFactory;
import vldmr.ssaumobile.database.EventEntity;
import vldmr.ssaumobile.database.NewsEntity;
import vldmr.ssaumobile.utils.DownloadEvents;
import vldmr.ssaumobile.utils.DownloadNews;


/**
 * Created by Vladimir on 25.03.2016.
 */
public class News extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final int LAYOUT=R.layout.news;

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    NavigationView navigationView;
    ProgressDialog progress;
    ProgressDialog progress2;
    SharedPreferences sharedPreferences;
    DownloadNewsTask news;
    Drawer.Result drawer=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        /*sharedPreferences=getSharedPreferences("com.mycompany.AppName",MODE_PRIVATE);
        if (sharedPreferences.getBoolean("firstrun",true)){

        }*/

        progress = new ProgressDialog(News.this);
        progress.setMessage("Loading...");
        progress2 = new ProgressDialog(News.this);
        progress2.setMessage("Проверьте подключение к сети Интернет");
        news=new DownloadNewsTask();
        try {
            news.execute().get();
            initTabs();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        initToolbar();
        //initNavigationView();

    }


    private void initNavigationView() {
            navigationView=(NavigationView)findViewById(R.id.nav);
            navigationView.setNavigationItemSelectedListener(this);


        }

    private void initTabs() {
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(new NewsFragmentPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initToolbar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Новости");
        setSupportActionBar(toolbar);

        //toolbar.setNavigationIcon(R.drawable.ic_menu_24dp);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.inflateMenu(R.menu.menu_toolbar);

    drawer=new Drawer()
            .withActivity(this)
            .withToolbar(toolbar)
            .withCloseOnClick(true)
            .withActionBarDrawerToggle(true)
            .withHeader(R.layout.header)
            .addDrawerItems(
                    new PrimaryDrawerItem().withName("Новости").withIcon(R.drawable.ic_sort_24dp).withIdentifier(1),
                    new PrimaryDrawerItem().withName("События").withIcon(R.drawable.ic_event_note_black_24dp).withIdentifier(6),
                    new PrimaryDrawerItem().withName("Телефонный справочник").withIcon(R.drawable.ic_account_box_black_24dp).withIdentifier(7),
                    new PrimaryDrawerItem().withName("Медиа").withIcon(R.drawable.ic_live_tv_black_24dp).withIdentifier(2),
                    new PrimaryDrawerItem().withName("Карта").withIcon(R.drawable.ic_place_24dp).withIdentifier(3),
                    new SecondaryDrawerItem(),
                    new SecondaryDrawerItem().withName("Об университете").withIdentifier(4),
                    new SecondaryDrawerItem().withName("О разработчике").withIdentifier(5)

            )
            .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                    switch (drawerItem.getIdentifier()){
                        case 1:{
                            Intent i=new Intent(News.this,News.class);
                            startActivity(i);
                            break;
                        }
                        case 2:{
                            Intent i=new Intent(News.this,Media.class);
                            startActivity(i);
                            break;
                        }
                        case 3:{Intent i=new Intent(News.this,Map.class);
                            startActivity(i);
                            break;
                        }
                        case 4:{
                            Intent i=new Intent(News.this,University.class);
                            startActivity(i);
                            break;
                        }
                        case 6:{
                            Intent i=new Intent(News.this,Events.class);
                            startActivity(i);
                            break;
                        }
                        case 7:{
                            Intent i=new Intent(News.this,Catalog.class);
                            startActivity(i);
                            break;
                        }
                        case 5:{
                            Intent i=new Intent(News.this,About.class);
                            startActivity(i);
                            break;
                        }

                    }
                }
            })
            .build();



    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.news_item:{
                Intent i=new Intent(this,News.class);
                startActivity(i);break;
            }
            case R.id.events_item:{
                Intent i=new Intent(this,Media.class);
                startActivity(i);break;
            }
            /*case R.id.map_item:{
                Intent i=new Intent(this,Map.class);
                startActivity(i);break;
            }*/

        }
        return true;
    }

    class DownloadNewsTask extends AsyncTask<Void, Boolean, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String s = null;
            /*try {

                s= DatabaseHelperFactory.getHelper().getNewsDAO().getLatestDate();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }*/
            /*if (s!=null)
            {*/
            DownloadNews news = new DownloadNews();
            Collection<NewsEntity> list = news.getSomeLatest();
            if (list!=null) {
                if (list.size() != 0) {
                    for (NewsEntity newsEntity : list) {
                        try {
                            DatabaseHelperFactory.getHelper().getNewsDAO().create(newsEntity);
                            Log.d("CreateNEWS", newsEntity.getTitle());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }


                //TODO СДЕЛАНО Переполнение БД
                List<NewsEntity> listDB = null;
                Collection<NewsEntity> listDBnew = new ArrayList<>();

                try {
                    listDB = MyListAdapter.getSortedList(DatabaseHelperFactory.getHelper().getNewsDAO().queryForAll());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (listDB.size() > 50) {
                    for (int i = 0; i < 50; i++) {
                        listDBnew.add(listDB.get(i));
                    }
                    try {
                        DatabaseHelperFactory.getHelper().getNewsDAO().delete(listDB);
                        for (NewsEntity newsEntity : listDBnew) {
                            DatabaseHelperFactory.getHelper().getNewsDAO().create(newsEntity);

                        }
                        Log.d("CreateNEWSafterOverflow", String.valueOf(listDBnew.size()));
                        Log.d("CreateNEWSbeforeOver", String.valueOf(listDB.size()));

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

            }
            DownloadEvents events = new DownloadEvents();
            Collection<EventEntity> listEvents = events.getSomeLatest();
            if (listEvents!=null) {
                if (listEvents.size() != 0) {
                    try {
                        List<EventEntity> list1 = DatabaseHelperFactory.getHelper().getEventDAO().queryForAll();
                        if (list1.size() != 0) {
                            for (EventEntity e : list1) {
                                DatabaseHelperFactory.getHelper().getEventDAO().delete(e);
                            }
                        }
                    } catch (SQLException e) {
                    e.printStackTrace(); }

                    for (EventEntity eventEntity : listEvents) {
                        try {
                            DatabaseHelperFactory.getHelper().getEventDAO().create(eventEntity);
                            Log.d("CreateEVENT", eventEntity.getTitle());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                publishProgress(true);
                return null;
            }
            publishProgress(false);
            return null;
        }

        @Override
        protected void onPreExecute() {
            progress.show();
            super.onPreExecute();


        }

        @Override
        protected void onProgressUpdate(Boolean... values) {
            if (values[0]==false)Toast.makeText(News.this,"Отсутствует подключение к сети Интернет",Toast.LENGTH_LONG).show();
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progress.dismiss();
            cancel(false);
            super.onPostExecute(aVoid);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.search_item:{
                Intent intent=new Intent(getApplicationContext(),Search.class);
                startActivity(intent);
                return true;}
            case R.id.like_news:{
                Intent intent=new Intent(getApplicationContext(),Like.class);
                startActivity(intent);
                return true;}
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        progress.dismiss();
    }


}
