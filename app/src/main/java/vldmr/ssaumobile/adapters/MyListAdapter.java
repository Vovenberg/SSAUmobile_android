package vldmr.ssaumobile.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.activities.Catalog;
import vldmr.ssaumobile.database.CatalogEntity;
import vldmr.ssaumobile.database.DatabaseHelperFactory;
import vldmr.ssaumobile.database.EventEntity;
import vldmr.ssaumobile.database.NewsEntity;
import vldmr.ssaumobile.utils.DownloadNews;

/**
 * Created by Vladimir on 28.04.2016.
 */
public class MyListAdapter  {
    static SimpleAdapter adapter;
    static List<NewsEntity> listtags;
    static ProgressDialog progress;

    public static SimpleAdapter getNewsAdapter(Context context) throws SQLException {


        List<Map<String,Object>> list=new ArrayList<>();
        int count=(int) DatabaseHelperFactory.getHelper().getNewsDAO().countOf();
        String[] titles= new String[count];
        String[] text= new String[count];
        String[] pic= new String[count];
        Integer[] id= new Integer[count];


        List<NewsEntity> listNewsFromDB = DatabaseHelperFactory.getHelper().getNewsDAO().queryForAll();
        List<NewsEntity> listNews=getSortedList(listNewsFromDB);

        int j=0;

        for (NewsEntity news:listNews){
            String title=news.getTitle();
            titles[j] = title.replace("&quot;", "\"");
            text[j]= String.valueOf(news.getDate());
            pic[j]=news.getPic();
            id[j]= news.getId();
            j++;
        }

        return getAdapter(context,count,titles,text,pic,id);
    }

    public static ListAdapter getNewsAdapterAdsOnly(Context context) throws SQLException {
        List<Map<String,Object>> list=new ArrayList<>();
        int count=(int) DatabaseHelperFactory.getHelper().getNewsDAO().countOf();
        String[] titles= new String[count];
        String[] text= new String[count];
        String[] pic= new String[count];
        Integer[] id= new Integer[count];


        //while (listNews.size()==0){
        List<NewsEntity> listNewsFromDB = DatabaseHelperFactory.getHelper().getNewsDAO().queryForAll();
        List<NewsEntity> listNews=getSortedList(listNewsFromDB);


        int j=0;
        for (NewsEntity news:listNews){
            if (news.getType().equals("Объявление")){
                String title=news.getTitle();
                titles[j] = title.replace("&quot;", "\"");
                text[j]= String.valueOf(news.getDate());
                pic[j]=news.getPic();
                id[j]= news.getId();
                j++;
            }
        }


        for (int i=0;i<count;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("Title",titles[i]);
            map.put("Date",text[i]);
            map.put("Img",pic[i]);
            map.put("Id",id[i]);

            list.add(map);
        }
        String[] from={"Title","Date","Map"};
        int[] to={R.id.titleNews,R.id.textNews,R.id.imagePic};
        adapter=new CustomSimpleAdapter(context,list,R.layout.item,from,to);

        return adapter;

    }

    public static ListAdapter getNewsAdapterNewsOny(Context context) throws SQLException {
        List<Map<String,Object>> list=new ArrayList<>();
        int count=(int) DatabaseHelperFactory.getHelper().getNewsDAO().countOf();
        String[] titles= new String[count];
        String[] text= new String[count];
        String[] pic= new String[count];
        Integer[] id= new Integer[count];

        List<NewsEntity> listNewsFromDB = DatabaseHelperFactory.getHelper().getNewsDAO().queryForAll();
        List<NewsEntity> listNews=getSortedList(listNewsFromDB);



        int j=0;
        for (NewsEntity news:listNews){
            if (news.getType().equals("Новость")||news.getType().equals("Новость портала" )){
                String title=news.getTitle();
                titles[j] = title.replace("&quot;", "\"");
                text[j]= String.valueOf(news.getDate());
                pic[j]=news.getPic();
                id[j]= news.getId();
                j++;
            }
        }


        for (int i=0;i<count;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("Title",titles[i]);
            map.put("Date",text[i]);
            map.put("Img",pic[i]);
            map.put("Id",id[i]);
            list.add(map);
        }
        String[] from={"Title","Date","Map"};
        int[] to={R.id.titleNews,R.id.textNews,R.id.imagePic};
        adapter=new CustomSimpleAdapter(context,list,R.layout.item,from,to);

        return adapter;



    }


    public static ListAdapter getNewsAdapterByTag(Context c,String tag){
        progress=new ProgressDialog(c);
        //progress.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        progress.setMessage("Loading..");
        List<NewsEntity> listNewsTag= null;
        try {
            listNewsTag = (List<NewsEntity>) new DownloadNewsByTag().execute(tag).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (listNewsTag!=null) {
            int count = listNewsTag.size();
            String[] titles = new String[count];
            String[] text = new String[count];
            String[] pic = new String[count];
            Integer[] id = new Integer[count];

            int j = 0;
            for (NewsEntity news : listNewsTag) {
                String title=news.getTitle();
                titles[j] = title.replace("&quot;","\"");
                text[j] = String.valueOf(news.getDate());
                pic[j] = news.getPic();
                id[j] = news.getId();
                j++;

            }

            return getAdapter(c, count, titles, text, pic, id);
        }
        else return null;
    }

    public static ListAdapter getNewsAdapterBySearch(Context c,String word){
        progress=new ProgressDialog(c);
        //progress.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        progress.setMessage("Loading..");

        List<NewsEntity> listNewsTag= null;
        try {
            listNewsTag = (List<NewsEntity>) new DownloadNewsBySearch().execute(word).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (listNewsTag!=null) {
            int count = listNewsTag.size();
            String[] titles = new String[count];
            String[] text = new String[count];
            String[] pic = new String[count];
            Integer[] id = new Integer[count];

            int j = 0;
            for (NewsEntity news : listNewsTag) {
                String title=news.getTitle();
                titles[j] = title.replace("&quot;","\"");
                text[j] = String.valueOf(news.getDate());
                pic[j] = news.getPic();
                id[j] = news.getId();
                j++;

            }

            return getAdapter(c, count, titles, text, pic, id);
        }
        else return null;
    }

    public static List getSortedList(List<NewsEntity> list){
        NewsEntity[] arr= new NewsEntity[list.size()];
        int k=0;
        for (NewsEntity ne:list){
            arr[k]=ne;
            k++;
        }
        for(int i=arr.length-1;i>0;i--){
            for (int j=0;j<i;j++){
                if (arr[j].getDate().before(arr[j + 1].getDate())){
                    NewsEntity tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;

                }
            }

        }
        List<NewsEntity> list1=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            list1.add(arr[i]);
        }
        return list1;
    }

    private static SimpleAdapter getAdapter (Context c,int count,String[] titles,String[] text,String[] pic,Integer[] id){


        List<Map<String,Object>> list=new ArrayList<>();
        for (int i=0;i<count;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("Title",titles[i]);
            map.put("Date",text[i]);
            map.put("Img",pic[i]);
            map.put("Id",id[i]);
            list.add(map);
        }
        String[] from={"Title","Date","Map"};
        int[] to={R.id.titleNews,R.id.textNews,R.id.imagePic};
        adapter=new CustomSimpleAdapter(c,list,R.layout.item,from,to);

        return adapter;
    }

    public static ListAdapter getLikeNewsAdapter(Context c,SharedPreferences tags1) {
        progress=new ProgressDialog(c);
        //progress.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        progress.setMessage("Loading..");

        String string=tags1.getString("Id", null);
        String[] array=string.split(",");
        NewsEntity ne=null;
        List<NewsEntity> list=new ArrayList<>();
        List<String> listForInternet=new ArrayList<>();
        for (int i=0;i<array.length;i++){
            try {
                ne=DatabaseHelperFactory.getHelper().getNewsDAO().queryForId(Integer.valueOf(array[i]));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (ne!=null){
                list.add(ne);
            }
            //TODO СДЕЛАНО скачивание избранных новстей из интернета,тк отсутствую в бд
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


        int count=list.size();
        String[] titles= new String[count];
        String[] text= new String[count];
        String[] pic= new String[count];
        int j=0;
        for (NewsEntity news:list){

                String title=news.getTitle();
                titles[j] = title.replace("&quot;", "\"");
                text[j]= String.valueOf(news.getDate());
                pic[j]=news.getPic();
                j++;

        }
        List<Map<String,Object>> list1=new ArrayList<>();
        for (int i=0;i<count;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("Title",titles[i]);
            map.put("Date",text[i]);
            map.put("Img",pic[i]);
            list1.add(map);
        }
        String[] from={"Title","Date","Map"};
        int[] to={R.id.titleNews,R.id.textNews,R.id.imagePic};
        adapter=new CustomSimpleAdapter(c,list1,R.layout.item,from,to);

        return adapter;


    }

    public static ListAdapter getCatalogList(Context applicationContext) {
        List<CatalogEntity> list=null;
        int count=0;
        try {
            list=DatabaseHelperFactory.getHelper().getCatalogDAO().queryForAll();
            count= (int) DatabaseHelperFactory.getHelper().getCatalogDAO().countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] names=new String[count];
        int i=0;
        for (CatalogEntity catalog:list){
            names[i]=catalog.getName();
            i++;
        }
        Arrays.sort(names);
        return new ArrayAdapter(applicationContext,R.layout.item_catalog,names);
    }

    public static ListAdapter getEvents(Context context) {
        List<EventEntity> listEvents=null;
        int count=0;
        try {
            listEvents= DatabaseHelperFactory.getHelper().getEventDAO().queryForAll();
            count= (int) DatabaseHelperFactory.getHelper().getEventDAO().countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] titles= new String[count];
        String[] type= new String[count];
        String[] id= new String[count];
        String[] date= new String[count];

        int j=0;
        for (EventEntity event:listEvents){
            String title=event.getTitle();
            titles[j] = title.replace("&quot;", "\"");
            type[j]= event.getType();
            id[j]= String.valueOf(event.getId());
            String s2= String.valueOf(event.getBegin());
            String s2new=s2.substring(3, 10).replace("Jun","Июнь").replace("Sep","Сентябрь").replace("Aug","Август").replace("Jul","Июль");
            String s21= String.valueOf(event.getEnd());
            String s21new=s21.substring(3, 10).replace("Jun","Июнь").replace("Sep","Сентябрь").replace("Aug","Август").replace("Jul","Июль");

            date[j]=s2new+" - "+s21new;
            j++;
        }
        List<Map<String,Object>> list=new ArrayList<>();
        for (int i=0;i<count;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("Title",titles[i]);
            map.put("Type",type[i]);
            map.put("Date",date[i]);
            map.put("Id",id[i]);
            list.add(map);
        }
        String[] from={"Title","Type","Date"};
        int[] to={R.id.titleEvents,R.id.typeEvent,R.id.date_beginend_item};

        return new CustomSimpleAdapterEvents(context,list,R.layout.item_events,from,to);
    }


    static class DownloadNewsByTag extends AsyncTask<String,Void,Collection<NewsEntity>> {

        @Override
        protected Collection<NewsEntity> doInBackground(String... params) {

            DownloadNews news=new DownloadNews();
            String s=params[0].substring(2,params[0].length());
            String s2=s.replace(" ","_");
            Collection<NewsEntity> list=news.getAllByTag(s2);

            return list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();
        }



        @Override
        protected void onPostExecute(Collection<NewsEntity> newsEntities) {
            listtags= (List<NewsEntity>) newsEntities;
            progress.dismiss();
        }
    }

    static class DownloadNewsBySearch extends AsyncTask<String,Void,Collection<NewsEntity>> {

        @Override
        protected Collection<NewsEntity> doInBackground(String... params) {

            DownloadNews news=new DownloadNews();

            Collection<NewsEntity> list=news.search(params[0]);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();
        }



        @Override
        protected void onPostExecute(Collection<NewsEntity> newsEntities) {
            listtags= (List<NewsEntity>) newsEntities;
            if (progress.isShowing()){
                progress.dismiss();
            }
        }
    }

    static class DownloadNewsByLike extends AsyncTask<String,Void,NewsEntity> {

        @Override
        protected NewsEntity doInBackground(String... params) {

            DownloadNews news=new DownloadNews();
            NewsEntity res=news.getById(Integer.valueOf(params[0]));

            return res;
        }






    }
}
