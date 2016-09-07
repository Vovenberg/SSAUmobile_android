package vldmr.ssaumobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.adapters.MediaFragmentPagerAdapter;

/**
 * Created by Vladimir on 25.03.2016.
 */
public class Media extends AppCompatActivity {
    static final int LAYOUT= R.layout.media;
    Drawer.Result drawer;

    Toolbar toolbar;

    TabLayout tabLayout;
    ViewPager viewPager;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        initToolbar();
        initTabs();

    }


    private void initTabs() {
        tabLayout=(TabLayout)findViewById(R.id.tablayout_events);
        viewPager=(ViewPager)findViewById(R.id.view_pager_events);
        viewPager.setAdapter(new MediaFragmentPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }



    public void initToolbar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar_events);
        toolbar.setTitle("Медиа");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer=new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withCloseOnClick(true)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Новости").withIcon(R.drawable.ic_sort_24dp).withIdentifier(1),
                        new PrimaryDrawerItem().withName("События").withIcon(R.drawable.ic_event_note_black_24dp).withIdentifier(6),
                        new PrimaryDrawerItem().withName("Персоналии").withIcon(R.drawable.ic_account_box_black_24dp).withIdentifier(7),
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
                                Intent i=new Intent(Media.this,News.class);
                                startActivity(i);
                                break;
                            }
                            case 2:{
                                Intent i=new Intent(Media.this,Media.class);
                                startActivity(i);
                                break;
                            }
                            case 3:{
                                break;
                            }
                            case 4:{
                                Intent i=new Intent(Media.this,University.class);
                                startActivity(i);
                                break;
                            }
                            case 6:{
                                Intent i=new Intent(Media.this,Events.class);
                                startActivity(i);
                                break;
                            }
                            case 7:{
                                Intent i=new Intent(Media.this,Catalog.class);
                                startActivity(i);
                                break;
                            }
                            case 5:{
                                Intent i=new Intent(Media.this,About.class);
                                startActivity(i);
                                break;
                            }

                        }
                    }
                })
                .build();

    }

}
