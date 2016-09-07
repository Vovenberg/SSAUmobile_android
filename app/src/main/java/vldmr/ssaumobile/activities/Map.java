package vldmr.ssaumobile.activities;/*
package vldmr.ssaumobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import vldmr.ssaumobile.R;

*/

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import vldmr.ssaumobile.R;

/**
 * Created by Vladimir on 12.05.2016.
 */

public class Map extends AppCompatActivity implements android.location.LocationListener {
    Toolbar toolbar;
    Drawer.Result drawer;
    GoogleMap m_googleMap;
    NavigationView navigationView;
    Marker m1;
    Marker m3;
    Marker m3a;
    Marker m5;
    Marker m15;
    Marker m10;
    Marker m11;
    Marker m16;
    Marker m14;
    Marker m9;
    Marker m7;
    Marker m_science;
    Marker m_vk;
    Marker m_sport;
    Marker m_manegh;
    Marker m_auto;
    Marker m_compinat;
    Marker m_pole;
    Marker m_ncomplecs;
    Marker m3b;
    Marker m_o2;
    Marker m_o3;
    Marker m_o4;
    Marker m_o5;
    Marker m_o6;
    Marker m_o7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        createMapView();
        initToolbar();
    }

    private void createMapView() {
        try {
            if (null == m_googleMap) {
                m_googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    m_googleMap.setMyLocationEnabled(true);
                } else {
                    Log.e("mapApp", "Location is bad");
                }
                m_googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                m_googleMap.getUiSettings().setZoomControlsEnabled(true);
                m_googleMap.getUiSettings().setScrollGesturesEnabled(true);
                m_googleMap.getUiSettings().setZoomGesturesEnabled(true);
                m_googleMap.getUiSettings().setCompassEnabled(true);
                m_googleMap.getUiSettings().setMapToolbarEnabled(true);



                m1=m_googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(53.201114, 50.108324))
                        .title("1 корпус"));
                m3=m_googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(53.212157, 50.177165))
                        .title("3 корпус"));
                m3a=m_googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(53.211948, 50.177701))
                        .title("3a корпус"));
                m5=m_googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(53.211944, 50.175822))
                        .title("5 корпус"));
                m15=m_googleMap.addMarker(new MarkerOptions()////////////////
                        .position(new LatLng(53.212954, 50.178034))
                        .title("15 корпус")
                        .snippet("Медиацентр"));
                m10=m_googleMap.addMarker(new MarkerOptions()///////////////////
                        .position(new LatLng(53.213186, 50.175348))
                        .title("10 корпус"));
                m11=m_googleMap.addMarker(new MarkerOptions()//////////////
                        .position(new LatLng(53.213744, 50.174774))
                        .title("11 корпус"));
                m3b=m_googleMap.addMarker(new MarkerOptions()///////////////
                        .position(new LatLng(53.212414, 50.1763144))
                        .title("3б корпус"));
                m16=m_googleMap.addMarker(new MarkerOptions()///////////////
                        .position(new LatLng(53.212761, 50.177784))
                        .title("16 корпус")
                        .snippet("Библиотека"));
                m14=m_googleMap.addMarker(new MarkerOptions()//////////////
                        .position(new LatLng(53.215137, 50.172938))
                        .title("14 корпус")
                        );
                m9=m_googleMap.addMarker(new MarkerOptions()/////////////
                        .position(new LatLng(53.215182, 50.174081))
                        .title("9 корпус"));
                m7=m_googleMap.addMarker(new MarkerOptions()////////////////
                        .position(new LatLng(53.214446, 50.172595))
                        .title("7 корпус"));
                m_science=m_googleMap.addMarker(new MarkerOptions()////////////////
                        .position(new LatLng(53.215916, 50.174595))
                        .title("НТЦ \"Наука\"")
                        );
                m_vk = m_googleMap.addMarker(new MarkerOptions()/////////////
                        .position(new LatLng(53.214259, 50.176473))
                        .title("Военная кафедра")
                );
                m_sport=m_googleMap.addMarker(new MarkerOptions()/////////////////
                        .position(new LatLng(53.213340, 50.177615))
                        .title("Спорткомплекс")
                );
                m_manegh=m_googleMap.addMarker(new MarkerOptions()////////////////
                        .position(new LatLng(53.213668, 50.176810))
                        .title("Крытый манеж")
                );
                m_auto=m_googleMap.addMarker(new MarkerOptions()///////////////
                        .position(new LatLng(53.213308, 50.174031))
                        .title("Автостоянка")
                );
                m_compinat=m_googleMap.addMarker(new MarkerOptions()///////////////////
                        .position(new LatLng(53.213045, 50.173226))
                        .title("Комбинат питания")
                );
                m_pole=m_googleMap.addMarker(new MarkerOptions()///////////////////
                        .position(new LatLng(53.213707, 50.173677))
                        .title("Футбольное поле")
                );
                m_ncomplecs=m_googleMap.addMarker(new MarkerOptions()///////////////////
                        .position(new LatLng(53.214028, 50.175361))
                        .title("Научный комплекс")
                );
                m_o2=m_googleMap.addMarker(new MarkerOptions()///////////////////
                        .position(new LatLng(53.211418, 50.1750351))
                        .title("Общежитие №2")
                );
                m_o3=m_googleMap.addMarker(new MarkerOptions()///////////////////
                        .position(new LatLng(53.211912, 50.174407))
                        .title("Общежитие №3")
                );
                m_o4=m_googleMap.addMarker(new MarkerOptions()///////////////////
                        .position(new LatLng(53.210910, 50.175705))
                        .title("Общежитие №4")
                );
                m_o5=m_googleMap.addMarker(new MarkerOptions()///////////////////
                        .position(new LatLng(53.212760, 50.170834))
                        .title("Общежитие №5")
                );
                m_o6=m_googleMap.addMarker(new MarkerOptions()///////////////////
                        .position(new LatLng(53.211636, 50.176349))
                        .title("Общежитие №6")
                );
                m_o7=m_googleMap.addMarker(new MarkerOptions()///////////////////
                        .position(new LatLng(53.212664, 50.178613))
                        .title("Общежитие №7")
                );


                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(53.212157, 50.177165))
                        .zoom(17)                   // Sets the zoom
                        .build();    // Creates a CameraPosition from the builder
                m_googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                        cameraPosition));


                if (null == m_googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        // Getting latitude of the current location
        double latitude = location.getLatitude();

        // Getting longitude of the current location
        double longitude = location.getLongitude();

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Showing the current location in Google Map
        m_googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        m_googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarMap);
        toolbar.setTitle("Учебные корпуса");
        setSupportActionBar(toolbar);

        //toolbar.setNavigationIcon(R.drawable.ic_menu_24dp);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.inflateMenu(R.menu.menu_map);

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
                                Intent i=new Intent(Map.this,News.class);
                                startActivity(i);
                                break;
                            }
                            case 2:{
                                Intent i=new Intent(Map.this,Media.class);
                                startActivity(i);
                                break;
                            }
                            case 3:{Intent i=new Intent(Map.this, vldmr.ssaumobile.activities.Map.class);
                                startActivity(i);
                                break;
                            }
                            case 4:{
                                Intent i=new Intent(Map.this,University.class);
                                startActivity(i);
                                break;
                            }
                            case 6:{
                                Intent i=new Intent(Map.this,Events.class);
                                startActivity(i);
                                break;
                            }
                            case 7:{
                                Intent i=new Intent(Map.this,Catalog.class);
                                startActivity(i);
                                break;
                            }
                            case 5:{
                                Intent i=new Intent(Map.this,About.class);
                                startActivity(i);
                                break;
                            }


                        }
                    }
                })
                .build();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_3: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m3.getPosition()));
                m3.showInfoWindow();
                break;
            }
            case R.id.menu_3а: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m3a.getPosition()));
                m3a.showInfoWindow();
                break;
            }
            case R.id.menu_14: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m14.getPosition()));
                m14.showInfoWindow();
                break;
            }
            case R.id.menu_1: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m1.getPosition()));
                m1.showInfoWindow();
                break;
            }
            case R.id.menu_5: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m5.getPosition()));
                m5.showInfoWindow();
                break;
            }
            case R.id.menu_15: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m15.getPosition()));
                m15.showInfoWindow();
                break;
            }
            case R.id.menu_10: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m10.getPosition()));
                m10.showInfoWindow();
                break;
            }
            case R.id.menu_11: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m11.getPosition()));
                m11.showInfoWindow();
                break;
            }
            case R.id.menu_16: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m16.getPosition()));
                m16.showInfoWindow();
                break;
            }
            case R.id.menu_9: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m9.getPosition()));
                m9.showInfoWindow();
                break;
            }
            case R.id.menu_7: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m7.getPosition()));
                m7.showInfoWindow();
                break;
            }
            case R.id.menu_3b: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m3b.getPosition()));
                m3b.showInfoWindow();
                break;
            }
            case R.id.menu_science: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_science.getPosition()));
                m_science.showInfoWindow();
                break;
            }
            case R.id.menu_vk: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_vk.getPosition()));
                m_vk.showInfoWindow();
                break;
            }
            case R.id.menu_sport: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_sport.getPosition()));
                m_sport.showInfoWindow();
                break;
            }
            case R.id.menu_manegh: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_manegh.getPosition()));
                m_manegh.showInfoWindow();
                break;
            }
            case R.id.menu_parking: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_auto.getPosition()));
                m_auto.showInfoWindow();
                break;
            }
            case R.id.menu_comb: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_compinat.getPosition()));
                m_compinat.showInfoWindow();
                break;
            }
            case R.id.menu_pole: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_pole.getPosition()));
                m_pole.showInfoWindow();
                break;
            }
            case R.id.menu_ncomplecs: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_ncomplecs.getPosition()));
                m_ncomplecs.showInfoWindow();
                break;
            }
            case R.id.menu_o2: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_o2.getPosition()));
                m_o2.showInfoWindow();
                break;
            }
            case R.id.menu_o3: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_o3.getPosition()));
                m_o3.showInfoWindow();
                break;
            }
            case R.id.menu_o4: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_o4.getPosition()));
                m_o4.showInfoWindow();
                break;
            }
            case R.id.menu_o5: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_o5.getPosition()));
                m_o5.showInfoWindow();
                break;
            }
            case R.id.menu_o6: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_o6.getPosition()));
                m_o6.showInfoWindow();
                break;
            }
            case R.id.menu_o7: {
                m_googleMap.animateCamera(CameraUpdateFactory.newLatLng(m_o7.getPosition()));
                m_o7.showInfoWindow();
                break;
            }
        }
        return true;
    }

    private Location getMyLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Location myLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (myLocation == null) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            String provider = lm.getBestProvider(criteria, true);
            myLocation = lm.getLastKnownLocation(provider);
        }

        return myLocation;
    }
}

