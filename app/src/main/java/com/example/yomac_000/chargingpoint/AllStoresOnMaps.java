package com.example.yomac_000.chargingpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import adapters.PopupAdapter;
import model.Store;
import retrofit.Response;
import services.StoreService;

/**
 * Created by yomac_000 on 22-12-2015.
 */
public class AllStoresOnMaps extends FragmentActivity implements OnMapReadyCallback {
    Response<List<Store>> subprises;
    Iterator it;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        try {
            subprises = new StoreService().getSubprises();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        it = subprises.body().iterator();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        while(it.hasNext()) {
            Store store = (Store) it.next();
            if (store != null) {
                LatLng latLng = new LatLng(store.getLatitude(), store.getLongitude());
                Marker marker = googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(store.getName())
                        .snippet("Facebook ID: " + store.getFacebookID() + "\n" +
                                store.getStreet() + "\n" +
                                store.getZipCode() + "\n" +
                                store.getCity())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.ic_launcher)));
                googleMap.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));
                builder.include(marker.getPosition());
            }
        }
        final LatLngBounds bounds = builder.build();
        int padding = 0; // offset from edges of the map in pixels
        final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                googleMap.moveCamera(cu);
            }
        });
    }
}
