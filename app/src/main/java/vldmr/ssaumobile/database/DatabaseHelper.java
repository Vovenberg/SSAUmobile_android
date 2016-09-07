package vldmr.ssaumobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import vldmr.ssaumobile.utils.DownloadNews;

/**
 * Created by Vladimir on 25.04.2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "ssaumobile42.db";
    private static final int DATABASE_VERSION = 1;

    private NewsDAO newsDAO;
    private CatalogDAO catalogDAO;
    private PhoneDAO phoneDAO;
    private EmailDAO emailDAO;
    private EventDAO eventDAO;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, NewsEntity.class);
            TableUtils.createTable(connectionSource, EventEntity.class);
            TableUtils.createTable(connectionSource,CatalogEntity.class);
            TableUtils.createTable(connectionSource,PhoneEntity.class);
            TableUtils.createTable(connectionSource,EmailEntity.class);

            try {
                DatabaseHelperFactory.getHelper().getCatalogDAO().createCatalog();
                Log.d("CATALOG","CREATED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //new DownloadNewsTask().execute();
            Log.d("DB", "CREATED");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE NewsEntity");
        onCreate(database);
    }

    public NewsDAO getNewsDAO() throws SQLException {
        if (newsDAO == null) {
            newsDAO = new NewsDAO(getConnectionSource(), NewsEntity.class);

        }
        return newsDAO;
    }

    public CatalogDAO getCatalogDAO() throws SQLException {
        if (catalogDAO == null) {
            catalogDAO = new CatalogDAO(getConnectionSource(), CatalogEntity.class);

        }
        return catalogDAO;
    }
    public PhoneDAO getPhoneDAO() throws SQLException {
        if (phoneDAO == null) {
            phoneDAO = new PhoneDAO(getConnectionSource(), PhoneEntity.class);

        }
        return phoneDAO;
    }
    public EmailDAO getEmailDAO() throws SQLException {
        if (emailDAO == null) {
            emailDAO = new EmailDAO(getConnectionSource(), EmailEntity.class);

        }
        return emailDAO;
    }
    public EventDAO getEventDAO() throws SQLException {
        if (eventDAO == null) {
            eventDAO = new EventDAO(getConnectionSource(), EventEntity.class);

        }
        return eventDAO;
    }

    class DownloadNewsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            new DownloadNews().firstDownload();

            return null;
        }


    }
}