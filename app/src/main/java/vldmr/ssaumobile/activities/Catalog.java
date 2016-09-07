package vldmr.ssaumobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.w3c.dom.Text;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import vldmr.ssaumobile.R;
import vldmr.ssaumobile.adapters.MyListAdapter;
import vldmr.ssaumobile.database.CatalogEntity;
import vldmr.ssaumobile.database.DatabaseHelperFactory;
import vldmr.ssaumobile.fragments.MyDialogFragment;

/**
 * Created by Vladimir on 09.06.2016.
 */
public class Catalog extends AppCompatActivity {
    Toolbar toolbar;
    Drawer.Result drawer;
    ListView listView;
    LinkedHashMap mapIndex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_layout);
        initToolbar();
        listView= (ListView) findViewById(R.id.listView_catalog_layout);
        List<CatalogEntity> list=null;
        int count=0;
        try {
            list= DatabaseHelperFactory.getHelper().getCatalogDAO().queryForAll();
            count= (int) DatabaseHelperFactory.getHelper().getCatalogDAO().countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] names=new String[count];
        int i=0;
        for (CatalogEntity catalog:list){
            names[i]=catalog.getName();
            i++;
        }
        Arrays.sort(names);
        getIndexList(names);
        displayIndex();
        listView.setAdapter(new ArrayAdapter(getApplicationContext(),R.layout.item_catalog,names));
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s= (String) listView.getItemAtPosition(position);
                Bundle bundle=new Bundle();
                bundle.putCharSequence("name",s);
                FragmentManager manager = getSupportFragmentManager();
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.setArguments(bundle);
                myDialogFragment.show(manager,"dialog");
            }
        });


    }

    private void getIndexList(String[] fruits) {
        mapIndex = new LinkedHashMap<String, Integer>();
        for (int i = 0; i < fruits.length; i++) {
            String fruit = fruits[i];
            String index = fruit.substring(0, 1);

            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
        }
    }

    private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);

        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater().inflate(
                    R.layout.side_index_item, null);
            textView.setText(index);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView selectedIndex = (TextView) v;
                    listView.setSelection((Integer) mapIndex.get(selectedIndex.getText()));
                }
            });
            indexLayout.addView(textView);
        }
    }

    private void initToolbar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar_catalog_layout);
        toolbar.setTitle("Телефонный справочник");
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
                                Intent i=new Intent(Catalog.this,News.class);
                                startActivity(i);
                                break;
                            }
                            case 2:{
                                Intent i=new Intent(Catalog.this,Media.class);
                                startActivity(i);
                                break;
                            }
                            case 3:{Intent i=new Intent(Catalog.this,Map.class);
                                startActivity(i);
                                break;
                            }
                            case 4:{
                                Intent i=new Intent(Catalog.this,University.class);
                                startActivity(i);
                                break;
                            }
                            case 6:{
                                Intent i=new Intent(Catalog.this,Events.class);
                                startActivity(i);
                                break;
                            }
                            case 7:{
                                Intent i=new Intent(Catalog.this,Catalog.class);
                                startActivity(i);
                                break;
                            }
                            case 5:{
                                Intent i=new Intent(Catalog.this,About.class);
                                startActivity(i);
                                break;
                            }
                        }
                    }
                })
                .build();



    }
}
