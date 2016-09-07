package vldmr.ssaumobile.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 03.05.2016.
 */
public class NewsDAO extends BaseDaoImpl<NewsEntity,Integer> {
    public NewsDAO(ConnectionSource connectionSource, Class<NewsEntity> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }



    public String getLatestDate() throws SQLException, ParseException {
       List<NewsEntity> list=this.queryForAll();
        List<Date> listDate=new ArrayList<>();
        String s=null;
        for (NewsEntity newsEntity:list){
            listDate.add(newsEntity.getDate());
        }
        if (listDate.size()!=0) {
            Date max = listDate.get(0);
            for (int i = 1; i < listDate.size(); i++) {
                if (listDate.get(i).after(max)) max = listDate.get(i);

            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
            s = dateFormat.format(max);
        }
        return s;
    }
}
