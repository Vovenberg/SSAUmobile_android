package vldmr.ssaumobile.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Vladimir on 03.05.2016.
 */
public class EventDAO extends BaseDaoImpl<EventEntity,Integer> {
    public EventDAO(ConnectionSource connectionSource, Class<EventEntity> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

}
