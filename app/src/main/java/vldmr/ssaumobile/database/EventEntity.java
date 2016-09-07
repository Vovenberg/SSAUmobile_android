package vldmr.ssaumobile.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Vladimir on 27.05.2016.
 */
@DatabaseTable(tableName = "events")
public class EventEntity {
    @DatabaseField(id=true)
    Integer id;
    @DatabaseField
    String title;
    @DatabaseField
    String text;
    @DatabaseField(columnName = "begin")
    Date begin;
    @DatabaseField(columnName = "end")
    Date end;
    @DatabaseField
    String type;
    @DatabaseField
    String country;
    @DatabaseField
    String city;
    @DatabaseField
    String place;
    @DatabaseField
    String adress;
    @DatabaseField
    String org;
    @DatabaseField
    String email;
    @DatabaseField
    String url;

    public EventEntity() {
    }

    public EventEntity(String adress, Date begin, String city, String country, String email, Date end, String org, String place, String text, String title, String type, String url) {
        this.adress = adress;
        this.begin = begin;
        this.city = city;
        this.country = country;
        this.email = email;
        this.end = end;
        this.org = org;
        this.place = place;
        this.text = text;
        this.title = title;
        this.type = type;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
