package vldmr.ssaumobile.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import vldmr.ssaumobile.database.EventEntity;
import vldmr.ssaumobile.database.NewsEntity;

/**
 * Created by Vladimir on 27.05.2016.
 */
public class DownloadEvents extends AbstractDownload implements Download<EventEntity>{
    OkHttpClient client;
    String s;


    public DownloadEvents() {
        client=new OkHttpClient();

    }

    @Override
    public Collection<EventEntity> getAllFromData(String date) {
        return null;
    }

    @Override
    public Collection<EventEntity> getSomeLatest() {
        InputStream is = null;
        try {
            Request request=new Request.Builder()
                    .url("http://test33.ssau.ru/api/events/")
                    .build();
            Response response=client.newCall(request).execute();
            is=response.body().byteStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return fromJSONtoArrayEvents(is);
    }

    @Override
    public Collection<EventEntity> getAllByTag(String tag) {
        return null;
    }

    @Override
    public void firstDownload() {

    }

    @Override
    public Collection<EventEntity> search(String word) {
        return null;
    }

    @Override
    public EventEntity getById(Integer id) {
        return null;
    }
}
