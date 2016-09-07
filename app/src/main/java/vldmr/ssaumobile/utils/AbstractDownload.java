package vldmr.ssaumobile.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import vldmr.ssaumobile.database.EventEntity;
import vldmr.ssaumobile.database.NewsEntity;

/**
 * Created by Vladimir on 27.05.2016.
 */
public abstract class AbstractDownload  {

    String s;
    Collection<NewsEntity> newsList=new ArrayList<>();
    Collection<EventEntity> eventsList=new ArrayList<>();

    public AbstractDownload() {

    }

    public Collection<NewsEntity> fromJSONtoArray(InputStream is){
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
            s = bf.readLine();
            if (!s.equals("ï»¿")) {
                String str = s.substring(s.indexOf("["), s.length());
                JSONArray array = new JSONArray(str);

                java.util.Date date1 = null;
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);

                    NewsEntity newsEntity = new NewsEntity();
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
                    Log.d("AllNewNEWSwithRepeat", newsEntity.getTitle());
                    newsList.add(newsEntity);
                }
                Log.d("COUNT_NEWS", String.valueOf(newsList.size()));
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }

        return newsList;
    }

    public Collection<EventEntity> fromJSONtoArrayEvents(InputStream is){
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
            s = bf.readLine();
            if (!s.equals("ï»¿")) {
                String str = s.substring(s.indexOf("["), s.length());
                JSONArray array = new JSONArray(str);

                java.util.Date dateBeg = null;
                java.util.Date dateEnd = null;
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);

                    EventEntity eventEntity=new EventEntity();
                    eventEntity.setTitle(object.getString("title"));
                    eventEntity.setText(object.getString("pubText"));

                    String dateString = object.getString("beg");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    try {
                        dateBeg = format.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    eventEntity.setBegin(dateBeg);
                    String dateStringEnd = object.getString("end");
                    try {
                        dateEnd = format.parse(dateStringEnd);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    eventEntity.setEnd(dateEnd);
                    eventEntity.setType(object.getString("typeevents"));
                    eventEntity.setCountry(object.getString("country"));
                    eventEntity.setCity(object.getString("city"));
                    eventEntity.setPlace(object.getString("place"));
                    eventEntity.setAdress(object.getString("address"));
                    eventEntity.setOrg(object.getString("org"));
                    eventEntity.setEmail(object.getString("email"));
                    eventEntity.setUrl(object.getString("url"));




                    Log.d("Allevents", String.valueOf(eventsList.size()));
                    eventsList.add(eventEntity);
                }
                Log.d("COUNT_NEWS", String.valueOf(newsList.size()));
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }

        return eventsList;
    }

}
