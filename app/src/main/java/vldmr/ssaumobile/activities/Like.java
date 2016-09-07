package vldmr.ssaumobile.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.adapters.MyListAdapter;
import vldmr.ssaumobile.adapters.RVAdapter;
import vldmr.ssaumobile.database.DatabaseHelperFactory;
import vldmr.ssaumobile.database.NewsEntity;
import vldmr.ssaumobile.utils.DownloadNews;

/**
 * Created by Vladimir on 19.05.2016.
 */
public class Like extends AppCompatActivity {

    Toolbar toolbar;
    ProgressDialog progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.like_layout);
        initToolbar();
        progress = new ProgressDialog(Like.this);
        progress.setMessage("Loading...");

        ListView listView= (ListView) findViewById(R.id.listView_like_layout);
        listView.setAdapter(MyListAdapter.getLikeNewsAdapter(getApplicationContext(),getSharedPreferences("Tags1",MODE_PRIVATE)));


        ///CardView cv= (CardView) findViewById(R.id.cv);
       /* RecyclerView rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //rv.setHasFixedSize(true);
        rv.setAdapter(new RVAdapter(getList(getSharedPreferences("Tags1",MODE_PRIVATE)),getApplicationContext()));*/


        //TODO CardView
    }

    private List<NewsEntity> getList(SharedPreferences tags1) {
        String string=tags1.getString("Id", null);
        String[] array=string.split(",");
        NewsEntity ne=null;
        List<NewsEntity> list=new ArrayList<>();
        List<String> listForInternet=new ArrayList<>();
        for (int i=0;i<array.length;i++){
            try {
                ne= DatabaseHelperFactory.getHelper().getNewsDAO().queryForId(Integer.valueOf(array[i]));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (ne!=null){
                list.add(ne);
            }

            else{
                try {
                    NewsEntity ne2=new DownloadNewsByLike().execute(array[i]).get();
                    list.add(ne2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        }
        return list;
    }

    public void initToolbar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar_like_layout);
        //toolbar.setLogo(R.drawable.logo);
        setTitle("Избранное");
        setSupportActionBar(toolbar);

        //toolbar.setNavigationIcon(R.drawable.ic_menu_24dp);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    class DownloadNewsByLike extends AsyncTask<String,Void,NewsEntity> {

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
