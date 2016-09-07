package vldmr.ssaumobile.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vldmr.ssaumobile.utils.PdfParser;

/**
 * Created by Vladimir on 03.05.2016.
 */
public class CatalogDAO extends BaseDaoImpl<CatalogEntity,Integer> {
    public CatalogDAO(ConnectionSource connectionSource, Class<CatalogEntity> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public void createCatalog() throws SQLException {
        Map<String,Map<String,List<String>>> workers;
        workers=new PdfParser().getWorkers();                                                       //PDFparser
        String name;
        Collection<String> tel;
        Collection<String> email;
        for (Map.Entry<String,Map<String,List<String>>> entry:workers.entrySet()){
            name=entry.getKey();
            Map<String,List<String>> map=entry.getValue();
            tel=map.get("Телефоны");
            email=map.get("Почты");


            CatalogEntity catalogEntity=new CatalogEntity();
            catalogEntity.setName(name);
            DatabaseHelperFactory.getHelper().getCatalogDAO().create(catalogEntity);

            for (String s:tel) {
                PhoneEntity phoneEntity = new PhoneEntity();
                phoneEntity.setPhone(s);
                phoneEntity.setCatalogEntity(catalogEntity);
                DatabaseHelperFactory.getHelper().getPhoneDAO().create(phoneEntity);
            }

            for (String s:email) {
                EmailEntity emailEntity = new EmailEntity();
                emailEntity.setEmail(s);
                emailEntity.setCatalogEntity(catalogEntity);
                DatabaseHelperFactory.getHelper().getEmailDAO().create(emailEntity);
            }

        }



    }
}
