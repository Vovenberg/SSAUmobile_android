package vldmr.ssaumobile.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Vladimir on 25.04.2016.
 */
@DatabaseTable(tableName = "news")
public class NewsEntity {
    @DatabaseField (id=true)
    Integer id;

    @DatabaseField
    String title;

    @DatabaseField
    String descr;

    @DatabaseField
    String pubText;

    @DatabaseField(columnName = "date")
    Date date;

    @DatabaseField
    String pic;

    @DatabaseField
    String type;

    @DatabaseField
    String tags;

    @DatabaseField
    String url;

    @DatabaseField
    Boolean like;

    public NewsEntity() {
    }

    public NewsEntity(Date date, String descr, Integer id, Boolean like, String pic, String pubText, String tags, String title, String type, String url) {
        this.date = date;
        this.descr = descr;
        this.id = id;
        this.like = like;
        this.pic = pic;
        this.pubText = pubText;
        this.tags = tags;
        this.title = title;
        this.type = type;
        this.url = url;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date  date) {
        this.date = date;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPubText() {
        return pubText;
    }

    public void setPubText(String pubText) {
        this.pubText = pubText;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    @Override
    public String toString() {
        return "NewsEntity{" +
                "id=" + id +
                ", pubText='" + pubText + '\'' +
                '}';
    }


}
