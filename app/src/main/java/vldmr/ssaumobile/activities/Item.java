package vldmr.ssaumobile.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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

import com.viewpagerindicator.CirclePageIndicator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.adapters.ImagePagerAdapter;
import vldmr.ssaumobile.utils.MyListView;

/**
 * Created by Vladimir on 14.05.2016.
 */
public class Item extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final int LAYOUT= R.layout.item_layout   ;
    NavigationView navigationView;
    Toolbar toolbar;
    int id;
    private int currentPage = 0;
    CirclePageIndicator indicator;
    String[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        initNavigationView();
        initToolbar();


        TextView textViewTitle= (TextView) findViewById(R.id.title_item);
        TextView textViewDate= (TextView) findViewById(R.id.date_item);
        TextView textViewText= (TextView) findViewById(R.id.text_item);
        //WebView webView= (WebView) findViewById(R.id.textWeb_item);

        final MyListView listView= (MyListView) findViewById(R.id.listView_tags);
        listView.setDivider(null);

        final Intent intent=getIntent();
        id=intent.getIntExtra("Id",-1);
        String s=intent.getStringExtra("Title");
        textViewTitle.setText( s);
        String s2=intent.getStringExtra("Date");
        String s2new=s2.substring(4, 16);
        textViewDate.setText(s2new);

        ///////////////////////ТЭГИ/////////////////////////////////////////////
        String s4=intent.getStringExtra("Tags");
        String[] array=tagsToArray(s4);
        List<Map<String,String>> list=new ArrayList<>();
        for(int i=0;i<array.length;i++){
            Map<String,String> map=new HashMap<>();
            map.put("Tag","# "+array[i]);
            list.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(getApplicationContext(),list,R.layout.item_tag, new String[]{"Tag"},new int[]{R.id.tag_item});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(), Tag.class);
                HashMap map = (HashMap) listView.getItemAtPosition(position);
                String s = (String) map.get("Tag");
                intent1.putExtra("Tag", s);
                startActivity(intent1);
            }
        });

        //////////////////////ТЕКСТ/////////////////////////////////////////////
        String s3=intent.getStringExtra("Text");

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

        ////////////ИЗОБРАЖЕНИЯ/////////////////////////////////////////
        Document doc= Jsoup.parse(s333);
        Elements imgs=doc.select("img");
        String[] arrayImg=new String[imgs.size()];
        for(int i=0;i<imgs.size();i++)
        {
            arrayImg[i]=imgs.get(i).attr("src");

        }

        ViewPager viewPager= (ViewPager) findViewById(R.id.image_pager);
        indicator= (CirclePageIndicator) findViewById(R.id.indicator);
        if(imgs.size()!=0) {
            viewPager.setAdapter(new ImagePagerAdapter(this, arrayImg));
            indicator.setViewPager(viewPager);
            ///viewPager.setOnPageChangeListener(indicator);
        }else {
            viewPager.setVisibility(View.INVISIBLE);
            indicator.setVisibility(View.INVISIBLE);
            viewPager.requestLayout();
            viewPager.getLayoutParams().height=0;
            indicator.getLayoutParams().height=0;
           }
        //TODO нажатие на ViewPAger изображений

    }



    private void initNavigationView() {
        navigationView=(NavigationView)findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.news_item:{
                Intent i=new Intent(this,News.class);
                startActivity(i);break;
            }
            case R.id.events_item:{
                Intent i=new Intent(this,Media.class);
                startActivity(i);break;
            }
            /*case R.id.map_item:{
                Intent i=new Intent(this,Map.class);
                startActivity(i);break;
            }*/

        }
        return true;
    }

    public String[] tagsToArray(String s){
        String[] array=null;
        String newS=s.replace('_', ' ');
        Pattern pattern=Pattern.compile(",");
        array=pattern.split(newS);



        return array;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item, menu);
        String string=getSharedPreferences("Tags1",MODE_PRIVATE).getString("Id", null);
        array=string.split(",");
        Boolean f=false;
        for(int i=0;i<array.length;i++){
            if (array[i].equals(String.valueOf(id)))
            {f=true;break;}

        }
        if(f==false){
            menu.getItem(0).setIcon(R.drawable.ic_favorite_outline_24dp);
        }else menu.getItem(0).setIcon(R.drawable.ic_favorite_black_24dp);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.like_item:{
                //TODO СДЕЛАНО добавление и удаление из Избранного нажатием на сердечко
                String string=getSharedPreferences("Tags1",MODE_PRIVATE).getString("Id", null);
                array=string.split(",");
                Boolean f=false;
                for(int i=0;i<array.length;i++){
                    if (array[i].equals(String.valueOf(id)))
                    {f=true;break;}

                }
                if(f==false){
                    saveIdInPrefernces(id);
                    item.setIcon(R.drawable.ic_favorite_black_24dp);
                    Toast.makeText(getApplicationContext(),"Новость добавленна в Избранное",Toast.LENGTH_SHORT).show();
                }else {
                    List<String> newArray=new ArrayList<>();
                    for(int i=0;i<array.length;i++){
                        if (!array[i].equals(String.valueOf(id)))
                        {newArray.add(array[i]);}

                    }
                    array=new String[newArray.size()];
                    int i=0;
                    StringBuilder sb=new StringBuilder();

                    for (String s:newArray){
                        array[i]=s;
                        i++;
                        sb.append(s+",");
                    }
                    SharedPreferences pr=getSharedPreferences("Tags1", MODE_PRIVATE);
                    SharedPreferences.Editor ed=pr.edit();
                    ed.putString("Id",sb.toString());
                    ed.commit();

                    item.setIcon(R.drawable.ic_favorite_outline_24dp);
                    Toast.makeText(getApplicationContext(),"Новость удаленна из Избранного",Toast.LENGTH_SHORT).show();}
                return true;}
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void saveIdInPrefernces(int id) {
        SharedPreferences pr=getSharedPreferences("Tags1", MODE_PRIVATE);
        SharedPreferences.Editor ed=pr.edit();
        String s=pr.getString("Id", null);
        if (s!=null) {
            StringBuilder sb = new StringBuilder(s);
            sb.insert(0, id + ",");
            ed.putString("Id", sb.toString());
            ed.commit();
        }
        else {
            ed.putString("Id", String.valueOf(id));
            ed.commit();
        }
    }
}
