package vldmr.ssaumobile.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.database.NewsEntity;

/**
 * Created by Vladimir on 22.05.2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {
    List<NewsEntity> list;
    Context c;

    public RVAdapter(List<NewsEntity> list,Context c) {
        this.list = list;
        this.c=c;
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        String name2 = String.valueOf(list.get(position).getDate());
        String name2new=name2.substring(4, 16);
        holder.data.setText(name2new );
        Picasso.with(c).load(list.get(position).getPic()).resize(300, 300).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        //CardView cv;
        TextView title;
        TextView data;
        ImageView img;
        PersonViewHolder(View itemView) {
            super(itemView);
            //cv = (CardView)itemView.findViewById(R.id.cv);
            title = (TextView)itemView.findViewById(R.id.titleNews);
            data = (TextView)itemView.findViewById(R.id.textNews);
            img = (ImageView)itemView.findViewById(R.id.imagePic);
        }

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    }
