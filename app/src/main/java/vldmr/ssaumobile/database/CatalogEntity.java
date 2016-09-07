package vldmr.ssaumobile.database;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Vladimir on 09.06.2016.
 */
@DatabaseTable(tableName = "catalog")
public class CatalogEntity {
    @DatabaseField(generatedId=true )
    Integer id;

    @DatabaseField
    String name;

    @ForeignCollectionField
    public ForeignCollection<PhoneEntity> phones;

    @ForeignCollectionField
    public ForeignCollection<EmailEntity> emails;

    public CatalogEntity(Collection<EmailEntity> emails, Integer id, String name, Collection<PhoneEntity> phones) {

        this.id = id;
        this.name = name;
    }

    public CatalogEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<EmailEntity> getEmails() {
        return emails;
    }

    public ForeignCollection<PhoneEntity> getPhones() {
        return phones;
    }
}
