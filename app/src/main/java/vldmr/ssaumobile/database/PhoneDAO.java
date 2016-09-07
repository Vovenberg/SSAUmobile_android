package vldmr.ssaumobile.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vladimir on 03.05.2016.
 */
public class PhoneDAO extends BaseDaoImpl<PhoneEntity,Integer> {
    public PhoneDAO(ConnectionSource connectionSource, Class<PhoneEntity> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

}
