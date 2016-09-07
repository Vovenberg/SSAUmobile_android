package vldmr.ssaumobile;


import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;

import vldmr.ssaumobile.database.DatabaseHelperFactory;
import vldmr.ssaumobile.utils.PdfParser;

/**
 * Created by Vladimir on 25.04.2016.
 */
public class MyApplication extends Application {
    SharedPreferences settings;

    @Override
    public void onCreate() {
        DatabaseHelperFactory.setHelper(getApplicationContext());

        final String PREFS_NAME = "MyPrefsFile";
        settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getBoolean("my_first_time", true)) {
            int maxSize = 1024 * 1024 * 10;
            Picasso picasso = new Picasso.Builder(getApplicationContext())
                    .memoryCache(new LruCache(maxSize))
                    .build();
        }
        super.onCreate();

    }


    @Override
    public void onTerminate() {
        DatabaseHelperFactory.releaseHelper();
        super.onTerminate();


    }


}