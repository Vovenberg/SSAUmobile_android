package vldmr.ssaumobile.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.viewpagerindicator.CirclePageIndicator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.adapters.ImagePagerAdapter;
import vldmr.ssaumobile.database.DatabaseHelperFactory;
import vldmr.ssaumobile.database.EventEntity;
import vldmr.ssaumobile.utils.MyListView;

/**
 * Created by Vladimir on 14.05.2016.
 */
public class ItemEvent extends AppCompatActivity  {

    static final int LAYOUT= R.layout.item_event_layout;
    NavigationView navigationView;
    Toolbar toolbar;
    int id;
    Drawer.Result drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        initToolbar();


        TextView textViewTitle= (TextView) findViewById(R.id.title_event_item);
        TextView textViewType= (TextView) findViewById(R.id.type_event_item);
        TextView textViewBegin= (TextView) findViewById(R.id.date_begin);
        TextView textViewEnd= (TextView) findViewById(R.id.date_end);
        TextView textViewOrg= (TextView) findViewById(R.id.org_event_item);
        TextView textViewPlace= (TextView) findViewById(R.id.place_event_item);
        TextView textViewText= (TextView) findViewById(R.id.text_event_item);

        final Intent intent=getIntent();
        id=Integer.parseInt(String.valueOf(intent.getCharSequenceExtra("Id")));
        EventEntity eventEntity=null;
        try {
            eventEntity= DatabaseHelperFactory.getHelper().getEventDAO().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        textViewTitle.setText( eventEntity.getTitle());
        String s2= String.valueOf(eventEntity.getBegin());
        String s2new=s2.substring(3, 10).replace("Jun","Июнь").replace("Sep","Сентябрь").replace("Aug","Август").replace("Jul","Июль");
        textViewBegin.setText("Дата начала: "+s2new);
        String s21= String.valueOf(eventEntity.getEnd());
        String s21new=s21.substring(3, 10).replace("Jun","Июнь").replace("Sep","Сентябрь").replace("Aug","Август").replace("Jul","Июль");
        textViewEnd.setText("Дата окончания:"+s21new);
        textViewType.setText(eventEntity.getType());
        setColorType(textViewType,eventEntity.getType(),getApplicationContext());
        textViewOrg.setText("Организатор: "+eventEntity.getOrg());
        textViewPlace.setText("Место проведения: "+eventEntity.getPlace());
        //////////////////////ТЕКСТ/////////////////////////////////////////////
        String s3=eventEntity.getText();

        String s3new=s3.replace("\n","").replace("\t","").replace("\r","").replace("<br />", "\n\t");
        String s33=s3new.replace("&quot;","\"").replace("&ndash;","—").replace("&mdash;","-").replace("]]>"," ").replace("&laquo;","\"").replace("&raquo;","\"");
        String s333 = "\t"+s33.substring(9,s33.length());

        String imgRegex = "<img\\b.+?/>";
        String stest=s333.replaceAll(imgRegex,"");
        Spanned text=Html.fromHtml(stest.replace("\t","<br \\> \t"));

        textViewText.setText(text);
        //Linkify.addLinks(textViewText,Linkify.ALL);
        //textViewText.setClickable(true);
        textViewText.setMovementMethod(LinkMovementMethod.getInstance());
       // textViewText.setMovementMethod(new ScrollingMovementMethod());
        //webView.loadData(s3,"text/html; charset=UTF-8",null);

    }

    private void setColorType(TextView type,String name2, Context mContext) {
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
    }


    public void initToolbar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar_item);
        toolbar.setLogo(R.drawable.logo);


        setSupportActionBar(toolbar);


        //toolbar.setNavigationIcon(R.drawable.ic_menu_24dp);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    }
