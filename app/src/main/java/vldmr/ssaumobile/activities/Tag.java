package vldmr.ssaumobile.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.adapters.MyListAdapter;
import vldmr.ssaumobile.database.DatabaseHelperFactory;
import vldmr.ssaumobile.database.NewsEntity;
import vldmr.ssaumobile.utils.DownloadNews;

/**
 * Created by Vladimir on 16.05.2016.
 */
public class Tag extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{


    NavigationView navigationView;
    Toolbar toolbar;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_layout);
        initNavigationView();
        initToolbar();
        progress=new ProgressDialog(Tag.this);
        final ListView listView= (ListView) findViewById(R.id.listView_tag_layout);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_tag_layout);
        String tag=getIntent().getStringExtra("Tag");
        if (tag!=null) {
            //////РЕЗУЛЬТАТЫ ПОИСКА ПО ТЭГУ////////////////////
            saveTagInPrefernces(tag);
            toolbar.setTitle(tag);
            ListAdapter adapter = MyListAdapter.getNewsAdapterByTag(Tag.this, tag);
            if (adapter != null) {

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    ///////////////////НАЖАТИЕ И ПЕРЕХОД В ITEM ///////////////////////////////
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getApplicationContext(), Item.class);
                        HashMap map= (HashMap)listView.getItemAtPosition(position);

                        String s= (String) map.get("Title");
                        String s2= (String) map.get("Date");
                        NewsEntity newsEntity = null;
                        try {
                            newsEntity= new DownloadNewsByLike().execute(String.valueOf(map.get("Id"))).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
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
            } else
                Toast.makeText(Tag.this, "Новости с этим тэгом отсутствуют", Toast.LENGTH_LONG);
        }
        else {
            //////РЕЗУЛЬТАТЫ ПОИСКА ПО СЛОВУ////////////////////
            tag=getIntent().getStringExtra("Search");
            saveSearchInPrefernces(tag);
            ListAdapter adapter = MyListAdapter.getNewsAdapterBySearch(Tag.this, tag);
            if (adapter != null) {
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    ///////////////////НАЖАТИЕ И ПЕРЕХОД В ITEM ///////////////////////////////
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getApplicationContext(), Item.class);
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
            } else
                Toast.makeText(Tag.this, "Новости с этой фразой отсутствуют", Toast.LENGTH_LONG);
        }

    }

    private void saveTagInPrefernces(String tag) {
        SharedPreferences pr=getSharedPreferences("Tags1", MODE_PRIVATE);
        SharedPreferences.Editor ed=pr.edit();
        String s=pr.getString("Tag", null);
        if (s!=null) {
            StringBuilder sb = new StringBuilder(s);
            sb.insert(0, tag + ",");
            ed.putString("Tag", sb.toString());
            ed.commit();
        }
        else {
            ed.putString("Tag",tag);
            ed.commit();
        }
    }
    private void saveSearchInPrefernces(String word) {
        SharedPreferences pr=getSharedPreferences("Search", MODE_PRIVATE);
        SharedPreferences.Editor ed=pr.edit();
        String s=pr.getString("Search", null);
        if (s!=null) {
            StringBuilder sb = new StringBuilder(s);
            sb.insert(0, word + ",");
            ed.putString("Search", sb.toString());
            ed.commit();
        }
        else {
            ed.putString("Search", word);
            ed.commit();
        }
    }

    private void initNavigationView() {
        navigationView=(NavigationView)findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);

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



    public void initToolbar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar_tag_layout);
        //toolbar.setLogo(R.drawable.logo);
        String s=getIntent().getStringExtra("Search");
        if (s!=null) toolbar.setTitle("Поиск по \"" +s+"\"");
        else toolbar.setTitle("Поиск по # " +getIntent().getStringExtra("Tag"));
        setSupportActionBar(toolbar);

        //toolbar.setNavigationIcon(R.drawable.ic_menu_24dp);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private class DownloadNewsByLike extends AsyncTask<String,Void,NewsEntity> {

        @Override
        protected NewsEntity doInBackground(String... params) {

            DownloadNews news=new DownloadNews();
            NewsEntity res=news.getById(Integer.valueOf(params[0]));

            return res;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();
        }



        @Override
        protected void onPostExecute(NewsEntity newsEntities) {

            progress.dismiss();
        }
    }

}
