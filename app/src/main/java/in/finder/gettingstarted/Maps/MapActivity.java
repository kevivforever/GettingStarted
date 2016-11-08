package in.finder.gettingstarted.Maps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import in.finder.gettingstarted.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap m_map;
    boolean mapReady=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Button btnMap = (Button) findViewById(R.id.button_map);
        if (btnMap != null) {
            btnMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mapReady)
                        m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            });
        }

        Button btnSatellite = (Button) findViewById(R.id.button_satellite);
        if (btnSatellite != null) {
            btnSatellite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mapReady)
                        m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            });
        }

        Button btnHybrid = (Button) findViewById(R.id.button_hybrid);
        if (btnHybrid != null) {
            btnHybrid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mapReady)
                        m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            });
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map){
        mapReady=true;
        m_map = map;
        LatLng newYork = new LatLng(40.7484,-73.9857);
        CameraPosition target = CameraPosition.builder().target(newYork).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));

        m_map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in America.")
                .position(newYork));

    }
}
