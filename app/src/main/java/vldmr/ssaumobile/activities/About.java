package vldmr.ssaumobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.squareup.picasso.Picasso;

import vldmr.ssaumobile.R;

/**
 * Created by Vladimir on 27.05.2016.
 */
public class About extends AppCompatActivity {

    Drawer.Result drawer=null;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        initToolbar();
        TextView textView= (TextView) findViewById(R.id.about_title);
        textView.setText("Данное приложение является бакалаврской дипломной работой на тему \"Мобильное приложение Самарского университета\".\n\n Разработчик: Кильдюшев В.О.\n Cтудент Самарского университета, специальность\"Информатика и вычислительная техника\".\n\nДипломный руководитель: Еленев Д.В.\n Доцент кафедры \"Информационных систем и технологий\". ");
        }
    private void initToolbar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("О разработчике");
        setSupportActionBar(toolbar);

        //toolbar.setNavigationIcon(R.drawable.ic_menu_24dp);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.inflateMenu(R.menu.menu_toolbar);

        drawer=new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withCloseOnClick(true)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Новости").withIcon(R.drawable.ic_sort_24dp).withIdentifier(1),
                        new PrimaryDrawerItem().withName("События").withIcon(R.drawable.ic_event_note_black_24dp).withIdentifier(6),
                        new PrimaryDrawerItem().withName("Телефонный справочник").withIcon(R.drawable.ic_account_box_black_24dp).withIdentifier(7),
                        new PrimaryDrawerItem().withName("Медиа").withIcon(R.drawable.ic_live_tv_black_24dp).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Карта").withIcon(R.drawable.ic_place_24dp).withIdentifier(3),
                        new SecondaryDrawerItem(),
                        new SecondaryDrawerItem().withName("Об университете").withIdentifier(4),
                        new SecondaryDrawerItem().withName("О разработчике").withIdentifier(5)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        switch (drawerItem.getIdentifier()){
                            case 1:{
                                Intent i=new Intent(About.this,News.class);
                                startActivity(i);
                                break;
                            }
                            case 2:{
                                Intent i=new Intent(About.this,Media.class);
                                startActivity(i);
                                break;
                            }
                            case 3:{Intent i=new Intent(About.this, Map.class);
                                startActivity(i);
                                break;
                            }
                            case 4:{
                                Intent i=new Intent(About.this,About.class);
                                startActivity(i);
                                break;
                            }
                            case 6:{
                                Intent i=new Intent(About.this,Events.class);
                                startActivity(i);
                                break;
                            }
                            case 7:{
                                Intent i=new Intent(About.this,Catalog.class);
                                startActivity(i);
                                break;
                            }

                        }
                    }
                })
                .build();




    }
}
