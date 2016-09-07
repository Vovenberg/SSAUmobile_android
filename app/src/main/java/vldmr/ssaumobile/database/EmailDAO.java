package vldmr.ssaumobile.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Vladimir on 03.05.2016.
 */
public class EmailDAO extends BaseDaoImpl<EmailEntity,Integer> {
    public EmailDAO(ConnectionSource connectionSource, Class<EmailEntity> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

}
