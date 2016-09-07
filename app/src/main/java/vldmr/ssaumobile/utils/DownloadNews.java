package vldmr.ssaumobile.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import vldmr.ssaumobile.database.DatabaseHelperFactory;
import vldmr.ssaumobile.database.NewsEntity;

/**
 * Created by Vladimir on 03.05.2016.
 */
public class DownloadNews extends AbstractDownload implements Download<NewsEntity> {
    OkHttpClient client;
    String s;


    public DownloadNews() {
        client=new OkHttpClient();

    }

    @Override
    public Collection<NewsEntity> getAllFromData(String date) {
        InputStream is = null;
        try {
            Request request=new Request.Builder()
                    .url("http://ssau.ru/api/news/&date-start=" + date + "&date-end=2016-12-12")
                    .build();
            Response response=client.newCall(request).execute();
            is=response.body().byteStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Collection<NewsEntity> listNewsAPI=fromJSONtoArray(is);
        Collection<NewsEntity> listNewsAPIDateStart=new ArrayList<>();
        Collection<NewsEntity> listNewsDBDateStart=new ArrayList<>();
        Collection<NewsEntity> listNewsDB= null;
        try {
            listNewsDB = DatabaseHelperFactory.getHelper().getNewsDAO().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (NewsEntity ne:listNewsAPI){
            String s=new SimpleDateFormat("yyyy-dd-MM").format(ne.getDate());
            if(s.equals(date)){
                listNewsAPIDateStart.add(ne);
            }
        };
        for (NewsEntity ne:listNewsDB){
            String s=new SimpleDateFormat("yyyy-dd-MM").format(ne.getDate());
            if(s.equals(date)){
                listNewsDBDateStart.add(ne);
            }
        };

        for (NewsEntity ne:listNewsAPIDateStart){
            for (NewsEntity ne2:listNewsDBDateStart){
                if (ne.getId().equals(ne2.getId())){
                    listNewsAPI.remove(ne);

                    Log.d("DeletedRepeatNews", ne2.getTitle());

                }
            }
            if (listNewsAPI.size()==0)break;
        }


        return listNewsAPI;
    }

    @Override
    public Collection<NewsEntity> getSomeLatest() {
        InputStream is = null;
        try {
            Request request=new Request.Builder()
                    .url("http://ssau.ru/api/news/&date-end=2016-12-12&limit=50")
                    .build();
            Response response=client.newCall(request).execute();
            is=response.body().byteStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }if (is!=null) {
            Collection<NewsEntity> listNewsAPI = fromJSONtoArray(is);
            Collection<NewsEntity> listNewsDB = null;
            Collection<NewsEntity> listNewsToCreate = new ArrayList<>();
            try {
                listNewsDB = DatabaseHelperFactory.getHelper().getNewsDAO().queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (NewsEntity ne : listNewsAPI) {
                Boolean b = false;
                for (NewsEntity neDB : listNewsDB) {
                    if (ne.getId().equals(neDB.getId())) {
                        b = true;
                        break;
                    }
                }
                if (b == false) {
                    listNewsToCreate.add(ne);
                }
            }
            return listNewsToCreate;
        }
        return null;
    }

    @Override
    public Collection<NewsEntity> getAllByTag(String tag) {
        InputStream is;
        try {
            Request request = new Request.Builder()
                    .url("http://ssau.ru/api/news/&limit=20&tags="+tag)
                    .build();
            Response response = client.newCall(request).execute();
            is=response.body().byteStream();
            Collection<NewsEntity> list=fromJSONtoArray(is);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void firstDownload(){
        InputStream is;
        try {
            Request request = new Request.Builder()
                    .url("http://ssau.ru/api/news/&limit=20&date-end=2016-12-12")
                    .build();
            Response response = client.newCall(request).execute();
            is=response.body().byteStream();
            Collection<NewsEntity> list=fromJSONtoArray(is);
            for (NewsEntity newsEntity:list){
                try {
                    DatabaseHelperFactory.getHelper().getNewsDAO().create(newsEntity);
                    Log.d("D",newsEntity.getTitle());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Collection<NewsEntity> search(String word){
        InputStream is;
        try {
            Request request = new Request.Builder()
                    .url("http://ssau.ru/api/news/&limit=20&q="+word)
                    .build();
            Response response = client.newCall(request).execute();
            is=response.body().byteStream();
            Collection<NewsEntity> list=fromJSONtoArray(is);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public NewsEntity getById(Integer id){
        InputStream is;
        NewsEntity newsEntity = new NewsEntity();
        try {
            Request request = new Request.Builder()
                    .url("http://ssau.ru/api/news/&id="+id)
                    .build();
            Response response = client.newCall(request).execute();
            is=response.body().byteStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
            s = bf.readLine();
            String str = s.substring(s.indexOf("["), s.length());
            JSONArray array = new JSONArray(str);

            java.util.Date date1 = null;
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);


                newsEntity.setId(Integer.valueOf(object.getString("id")));
                newsEntity.setTitle(object.getString("title"));

                String dateString = object.getString("pubDate");
                String dateStringNew = dateString.substring(0, dateString.length() - 3);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                try {
                    date1 = format.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                newsEntity.setDate(date1);
                newsEntity.setDescr(object.getString("descr"));
                newsEntity.setPic(object.getString("pic"));
                newsEntity.setTags(object.getString("tags"));
                newsEntity.setUrl(object.getString("url"));
                newsEntity.setType(object.getString("type"));
                newsEntity.setPubText(object.getString("pubText"));
                newsEntity.setLike(false);
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }



        return newsEntity;
    }


}
