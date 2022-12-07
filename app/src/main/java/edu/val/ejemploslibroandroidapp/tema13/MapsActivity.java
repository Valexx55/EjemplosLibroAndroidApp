package edu.val.ejemploslibroandroidapp.tema13;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import edu.val.ejemploslibroandroidapp.R;
import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void mostrarUbicacionMapa(View view) {
        //pedir permisos GPS
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 535);
        }

    }

    private boolean gpsEstaActivado() {
        boolean gps_activo = false;

        gps_activo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        return gps_activo;
    }

    private void solictarActivacionGPS() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, 77);
    }

    private void mostrarDirPostal (Location ubicacion)
    {
        try{
            Geocoder geocoder = new Geocoder(this, new Locale("es"));
            List<Address> dirs= geocoder.getFromLocation(ubicacion.getLatitude(),ubicacion.getLongitude(), 1);
            if (dirs!=null && dirs.size()>0) {
                Address direccion = dirs.get(0);
                Log.d(MainActivity.ETIQUETA_LOG, "Dirección = " + direccion.getAddressLine(0) + " CP "+
                        direccion.getPostalCode() + " Localidad "
                                + direccion.getLocality());

            }
            }catch (Exception e)
            {
                Log.e(MainActivity.ETIQUETA_LOG, "Error obteniendo la dirección postal", e);
            }

    }

    private void mostrarUbicacionObtenida(Location ubicacion) {
        Log.d(MainActivity.ETIQUETA_LOG, "Mostrando ubicación obtenida ...");
        LatLng ubicacion_actual = new LatLng(ubicacion.getLatitude(), ubicacion.getLongitude());
        mMap.addMarker(new MarkerOptions().position(ubicacion_actual).title("Estoy aquí"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion_actual));
        mostrarDirPostal(ubicacion);

    }

    private void accedoALaUbicacionGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null) {
                    Location location = locationResult.getLastLocation();
                    mostrarUbicacionObtenida(location);
                    MapsActivity.this.fusedLocationProviderClient.removeLocationUpdates(MapsActivity.this.locationCallback);
                }
            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (gpsEstaActivado()) {
            accedoALaUbicacionGPS();
        } else {
            Log.d(MainActivity.ETIQUETA_LOG, "GPS continua desactivado");
            Toast.makeText(this, "Sin acceso a la ubicación", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity.ETIQUETA_LOG, "Permiso uso GPS concecido");
            if (gpsEstaActivado()) {
                accedoALaUbicacionGPS();
            } else {
                //TODO solictarActivacionGPS();
                solictarActivacionGPS();
            }
        } else {
            Log.d(MainActivity.ETIQUETA_LOG, "Permiso uso GPS concecido");
            Toast.makeText(this, "Sin acceso a la ubicación", Toast.LENGTH_LONG).show();
        }
    }
}