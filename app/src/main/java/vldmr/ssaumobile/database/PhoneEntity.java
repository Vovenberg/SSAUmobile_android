package vldmr.ssaumobile.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Vladimir on 09.06.2016.
 */
@DatabaseTable(tableName = "phone")
public class PhoneEntity {
    @DatabaseField(generatedId=true )
    Integer id;

    @DatabaseField
    String phone;

    @DatabaseField(foreign=true,foreignAutoRefresh=true)
    CatalogEntity catalogEntity;

    public PhoneEntity() {
    }

    public PhoneEntity(CatalogEntity catalogEntity, Integer id, String phone) {

        this.catalogEntity = catalogEntity;
        this.id = id;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
