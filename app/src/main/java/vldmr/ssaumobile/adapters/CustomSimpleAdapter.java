package vldmr.ssaumobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import vldmr.ssaumobile.R;

/**
 * Created by Vladimir on 12.05.2016.
 */
public class CustomSimpleAdapter extends SimpleAdapter {
    private Context mContext;
    public LayoutInflater inflater=null;
    public CustomSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.item, null);

        HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
        TextView title = (TextView)vi.findViewById(R.id.titleNews);
        TextView date = (TextView)vi.findViewById(R.id.textNews);
        ImageView image=(ImageView) vi.findViewById(R.id.imagePic);

        String name = (String) data.get("Title");

        title.setText(name.replace("&amp;trade;",""));

        String name2 = (String) data.get("Date");
        String name2new=name2.substring(4, 16).replace("Jun","Июнь");
        date.setText(name2new);

        String image_url = (String) data.get("Img");

        Picasso.with(mContext).load(image_url).error(R.drawable.no_img).resize(237, 237).into(image);

        return vi;
    }
}


