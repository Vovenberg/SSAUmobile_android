package vldmr.ssaumobile.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Vladimir on 09.06.2016.
 */
@DatabaseTable(tableName = "email")
public class EmailEntity {
    @DatabaseField(generatedId=true )
    Integer id;

    @DatabaseField
    String email;

    @DatabaseField(foreign=true,foreignAutoRefresh=true)
    CatalogEntity catalogEntity;

    public EmailEntity() {
    }

    public EmailEntity(CatalogEntity catalogEntity, String email, Integer id) {
        this.catalogEntity = catalogEntity;
        this.email = email;
        this.id = id;
    }

    public CatalogEntity getCatalogEntity() {
        return catalogEntity;
    }

    public void setCatalogEntity(CatalogEntity catalogEntity) {
        this.catalogEntity = catalogEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
