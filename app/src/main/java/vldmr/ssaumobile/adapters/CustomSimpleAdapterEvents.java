package vldmr.ssaumobile.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
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

import vldmr.ssaumobile.R;

/**
 * Created by Vladimir on 12.05.2016.
 */
public class CustomSimpleAdapterEvents extends SimpleAdapter {
    private Context mContext;
    public LayoutInflater inflater=null;
    public CustomSimpleAdapterEvents(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.item_events, null);

        HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
        TextView title = (TextView)vi.findViewById(R.id.titleEvents);
        TextView type = (TextView)vi.findViewById(R.id.typeEvent);
        TextView date = (TextView)vi.findViewById(R.id.date_beginend_item);

        String name = (String) data.get("Title");
        title.setText(name.replace("&amp;quot;","\""));

        date.setText((CharSequence) data.get("Date"));

        String name2 = (String) data.get("Type");
        type.setText(name2);
        switch (name2) {
            case "Семинар": {
                type.setBackgroundColor(ContextCompat.getColor(mContext, R.color.Semin));
                type.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccentMedia));
                break;
            }
            case "Конференция": {
                type.setBackgroundColor(ContextCompat.getColor(mContext, R.color.Conf));
                type.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
                break;
            }
            case "Форум": {
                type.setBackgroundColor(ContextCompat.getColor(mContext, R.color.Event3BAck));
                type.setTextColor(ContextCompat.getColor(mContext, R.color.Event3));
                break;
            }
            case "Научная школа": {
                type.setBackgroundColor(ContextCompat.getColor(mContext, R.color.Event4BAck));
                type.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccentEvents));
                break;
            }
            case "Конгресс": {
                type.setBackgroundColor(ContextCompat.getColor(mContext, R.color.Event5BAck));
                type.setTextColor(ContextCompat.getColor(mContext, R.color.Event5));
                break;
            }
        }
        return vi;
    }
}


