package vldmr.ssaumobile.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import vldmr.ssaumobile.R;

/**
 * Created by Vladimir on 18.05.2016.
 */
public class Search extends AppCompatActivity implements View.OnKeyListener{
    Toolbar toolbar;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        initToolbar();
        initEditText();
        initListViewWithTags();
        initListViewWithSearch();


    }

    @Override
    protected void onResume() {
        super.onResume();
        initListViewWithTags();
        initListViewWithSearch();
    }

    private void initListViewWithSearch() {
        SharedPreferences pr=getSharedPreferences("Search",MODE_PRIVATE);
        Object[] arrayNew ;
        String string=pr.getString("Search", null);
        //TODO СДЕЛАНО Удаление повторяющихся элементов в массиве
        if(string!=null) {
            String[] array=string.split(",");
            List<String> finalList = new ArrayList<String>();
            for (String s :array ) {
                if (!finalList.contains(s)) {
                    finalList.add(s);
                }
            }
            arrayNew=finalList.toArray();

        }else {arrayNew=new Object[1]; arrayNew[0]="Запросов еще не было";}
        final ListView listView= (ListView) findViewById(R.id.listView_search_search);
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.simple_list_item, arrayNew);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(), Tag.class);
                String s = (String) listView.getItemAtPosition(position);
                intent1.putExtra("Search", s);
                startActivity(intent1);
            }
        });

    }

    private void initListViewWithTags() {
        SharedPreferences pr=getSharedPreferences("Tags1",MODE_PRIVATE);
        Object[] arrayNew ;
        String string=pr.getString("Tag", null);
        String[] array=string.split(",");
        //TODO СДЕЛАНО Удаление повторяющихся элементов в массиве
        List<String> finalList = new ArrayList<String>();
        for (String s :array ) {
            if (!finalList.contains(s)) {
                finalList.add(s);
            }
        }
        arrayNew=finalList.toArray();

        final ListView listView= (ListView) findViewById(R.id.listView_search);
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.simple_list_item, arrayNew);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getApplicationContext(), Tag.class);
                String s = (String) listView.getItemAtPosition(position);
                intent1.putExtra("Tag", s);
                startActivity(intent1);
            }
        });
    }

    public void initToolbar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar_search);
        toolbar.setTitle("Поиск");
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

    private void initEditText() {
        editText= (EditText) findViewById(R.id.edit_search);
        editText.setOnKeyListener(this);

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN &&
                (keyCode == KeyEvent.KEYCODE_ENTER))
        {
            // сохраняем текст, введенный до нажатия Enter в переменную
            String str = editText.getText().toString();
            Intent intent=new Intent(this,Tag.class);
            intent.putExtra("Search",str);
            startActivity(intent);
            return true;
        }
        return false;
    }




}
