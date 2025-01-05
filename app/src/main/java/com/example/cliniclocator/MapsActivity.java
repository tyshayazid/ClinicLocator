package com.example.cliniclocator;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.cliniclocator.databinding.ActivityMapsBinding;

import java.util.Vector;
import android.Manifest;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    MarkerOptions marker;
    LatLng centerlocation;

    Vector<MarkerOptions> markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using ViewBinding
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerlocation = new LatLng(6.442055282470381, 100.26587420221344);

        markerOptions = new Vector<>();

        // Add markers to the vector
        markerOptions.add(new MarkerOptions().title("Klinik Anda Kangar 24Jam ")
                .position(new LatLng(6.412814693578003, 100.18241535345982))
                .snippet("Open 24h"));
        markerOptions.add(new MarkerOptions().title("Klinik Dr Haji Othman ")
                .position(new LatLng(6.453497110984174, 100.20008892462468))
                .snippet("Open 24h"));
        markerOptions.add(new MarkerOptions().title("Annura Clinic Kangar ")
                .position(new LatLng(6.421886170538998, 100.19744052462453))
                .snippet("Open 24h"));
        markerOptions.add(new MarkerOptions().title("Mediklinik Rakyat Dr Naim Ahmad Kangar 24Jam ")
                .position(new LatLng(6.415792470289826, 100.18279329578898))
                .snippet("Open 24h"));
        markerOptions.add(new MarkerOptions().title("Mediklinik Putra Kangar ")
                .position(new LatLng(6.439672293551554, 100.2092008957891))
                .snippet("Open 24h"));
        markerOptions.add(new MarkerOptions().title("Qualitas Health Klinik Menon")
                .position(new LatLng(6.436802671149855, 100.19424473811827))
                .snippet("Open 24h"));
        markerOptions.add(new MarkerOptions().title("Klinik Remidic Kangar")
                .position(new LatLng(6.436138671122613, 100.18834909578914))
                .snippet("Open 24h"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add all markers to the map
        for (MarkerOptions mark : markerOptions) {
            mMap.addMarker(mark);
        }

        enableMyLocation();

        // Move camera to the center location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation, 10));
    }

    private void enableMyLocation() {
        // Check permission for ACCESS_FINE_LOCATION
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            // Request permission if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }
    }

    // Handle the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, enable the location
                if (mMap != null) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mMap.setMyLocationEnabled(true);
                }
            } else {
                // Permission denied, show a message
                Toast.makeText(this, "Permission denied to access your location", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
