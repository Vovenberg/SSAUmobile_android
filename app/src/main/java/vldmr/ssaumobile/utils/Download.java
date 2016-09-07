package vldmr.ssaumobile.utils;

import java.util.Collection;

/**
 * Created by Vladimir on 03.05.2016.
 */
public interface Download<T> {
    public Collection<T> getAllFromData(String date);

    public Collection<T> getSomeLatest();

    public Collection<T> getAllByTag(String tag);

    public void firstDownload();

    public Collection<T> search(String word);

    public T getById(Integer id);

}
