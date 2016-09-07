package vldmr.ssaumobile.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.utils.TouchImageView;

/**
 * Created by Vladimir on 19.05.2016.
 */
public class ImagePagerAdapter extends PagerAdapter {
    Context context;
    String[] imgs;

    public ImagePagerAdapter() {
    }

    public ImagePagerAdapter(Context context, String[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();

        View view=layoutInflater.inflate(R.layout.image_item,container,false);
        TouchImageView imageView= (TouchImageView) view.findViewById(R.id.imageView_image_item);

        Picasso.with(context).load(imgs[position]).into(imageView);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
