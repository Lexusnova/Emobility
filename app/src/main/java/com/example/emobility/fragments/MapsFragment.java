package com.example.emobility.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.emobility.adapters.CustomInfoWindowAdapter;
import com.example.emobility.R;
import com.example.emobility.classes.Favourite;
import com.example.emobility.classes.Verwalter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

/**
 * @class MapsFragment
 * @brief Ausgabe der Ladesäule, visuelle Darstellung, ist unsere Maps womit der User die Ladesäulen aufsuchen kann
 */
public class MapsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final int REQ_PERMISSION = 101;
    private GoogleMap mMap;
    boolean alreadyExecuted = false;
    double loclat, loclong;
    LatLng myloclatlong;
    String spinnertext;
    ArrayList<Marker> markers = new ArrayList<>();
    ArrayList<Marker> favmarkers = new ArrayList<>();
    String markerid;
    Marker tempmarker;
    Circle drawncircle;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * @param googleMap Dies ist ein Googlemaps objekt der Google Maps SDK für Android und der Einstiegspunkt für alle Methoden, die sich auf die Karte beziehen.
         * @brief Es werden die ladesaeulen marker erstellt.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            //new fetchDataJson(requireContext());
            if (!alreadyExecuted) {
                fetchDataJson temp = new fetchDataJson(getContext());
                googleMap.clear();

                Log.d("saulesizee", String.valueOf(temp.saeulen.size()));
                try {
                    for (int i = 0; i < temp.saeulen.size(); i++) {
                        // Add a marker in Sydney and move the camera
                        LatLng latlng = new LatLng(temp.saeulen.get(i).getBreitengrad(), temp.saeulen.get(i).getLaengengrad());
                        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getContext()));
                        Marker marker = googleMap.addMarker(new MarkerOptions().position(latlng).title(temp.saeulen.get(i).getBetreiber()).snippet(
                                "Art-Ladeeinrichtung: " + temp.saeulen.get(i).getFastcharging() + "\n"
                                +"Ladepunkte: " + temp.saeulen.get(i).getLadepunkte() + "      "


                        ));
                        markers.add(marker);
                        if (marker != null) {
                            marker.setTag(marker.getId());
                        }
                        Log.d("markersize", String.valueOf(markers.size()));
                    }
                    LatLng mitte = new LatLng(temp.saeulen.get(0).getBreitengrad(), temp.saeulen.get(0).getLaengengrad());
                    moveToCurrentLocation(mitte, googleMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                alreadyExecuted = true;
            }
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    addfavorite(marker);
                    return false;
                }
            });
            if (checkPermission()) {
                mMap.setMyLocationEnabled(true);
            } else {
                askPermission();
            }
            setPadding();
        }
    };

    /**
     * @brief hier wird nach zugriff auf den standort gecheckt und ob es schon erlaubt wurde darauf zuzugreifen.
     */
    private boolean checkPermission() {
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * @brief hier wird nach zugriff auf den standort gefragt und ob man darauf zugreifen darf.
     */
    private void askPermission() {
        ActivityCompat.requestPermissions(
                (Activity) getContext(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQ_PERMISSION
        );
    }
    /**
     * @param requestCode hier wird der requestcode übergeben.
     * @param permissions hier werden die ganzen permissions übergeben.
     * @param grantResults hier werden die ganzen ergebnisse übergeben, ob es bspw. gewährt wurde auf den standort zuzugreifen.
     * @brief hier wird nach zugriff auf den standort gefragt und ob man darauf zugreifen darf.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                loclat = mMap.getMyLocation().getLatitude();
                loclong = mMap.getMyLocation().getLongitude();
                myloclatlong = new LatLng(loclat, loclong);
                if (checkPermission())
                    mMap.setMyLocationEnabled(true);
                loclat = mMap.getMyLocation().getLatitude();
                loclong = mMap.getMyLocation().getLongitude();
                myloclatlong = new LatLng(loclat, loclong);
            } else {
                Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        ImageButton ibutton = (ImageButton) view.findViewById(R.id.addFav);
        ibutton.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           buttonCase(view);
                                       }
                                   }
        );
        Spinner spinner = view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.radius, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    /**
     * @param currentLocation ist die aktuelle position die übergeben wird.
     * @brief hier wird sich nur zu einer bestimmten position auf der karte bewegt.
     */
    private void moveToCurrentLocation(LatLng currentLocation, @NonNull GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

    }

    /**
     * @brief erstellt eine Padding damit der BottomNavigation noch zu sehen ist
     */
    public void setPadding() {
        mMap.setPadding(0, 0, 0, 150);
    }

    /**
     * @param view ist die Basisklasse für Widgets, die verwendet werden, um interaktive UI-Komponenten (Schaltflächen, Textfelder usw.) zu erstellen.
     * @brief die funktion sorgt für das hinzufügen der favouriten.
     */
    public void buttonCase(@NonNull View view) {
        if (markerid == null) {
            Toast.makeText(getContext(), "Please Click on Marker, before you add on Favourite", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("tagcheck", String.valueOf(markers.get(1).getTag()));
        Log.d("tagsize", String.valueOf(markers.size()));
        for (int i = 0; i < markers.size(); i++) {
            if (markerid.equals(String.valueOf(markers.get(i).getTag()))) {
                if (favmarkers.isEmpty()) {
                    markers.get(i).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                    favmarkers.add(markers.get(i));
                    Toast.makeText(getContext(), "added", Toast.LENGTH_SHORT).show();
                } else {
                    for (int b = 0; b < favmarkers.size(); b++) {
                        if (favmarkers.contains(tempmarker)) {
                            Toast.makeText(getContext(), "marker already added as favourite", Toast.LENGTH_SHORT).show();
                            break;
                        } else {
                            markers.get(i).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                            favmarkers.add(markers.get(i));
                            Toast.makeText(getContext(), "added", Toast.LENGTH_SHORT).show();
                            Log.d("favsize", String.valueOf(favmarkers.size()));
                            break;
                        }
                    }
                }
            }
        }
        //Elemente werden gesendet zu dem Verwalter
        if (!favmarkers.isEmpty()) {
            for (int i = 0; i <favmarkers.size(); i++ ) {
                Log.d("IdOfFav", favmarkers.get(i).getId());
            }
            String last = favmarkers.get(favmarkers.size() - 1).getId();
            last = last.replace("m", "");
            int id = Integer.parseInt(last);
            if (Verwalter.getFavouritenListe().isEmpty()) {
                Favourite favourite = new Favourite(id);
                Verwalter.sendFavourite(favourite);
            } else {
                Favourite favourite = new Favourite(id);
                Verwalter.sendFavourite(favourite);
            }

        }
        Verwalter.setFavmarkers(favmarkers);
    }


    //Hier löscht es die Elemente
    /*
    public void deleteFavourite() {
        if (Verwalter.getFavmarkers().size() == favmarkers.size()) {
            return;
        } else {
            for (int i = 0; i < Verwalter.getRemoveElements().size(); i++) {
                for (int g = 0; g < favmarkers.size(); g++) {
                    if (Verwalter.getRemoveElements().get(i).equals(favmarkers.get(g).getId())) {
                        markers.get(i).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }
                }
            }
        }
    }
*/
    /**
     * @param marker hier wird der aktuell angeklickte marker übergeben
     * @brief die funktion ist dafür da um vom aktuellen marker die markerid zu bekommen.
     */
    public void addfavorite(@NonNull Marker marker) {
        markerid = marker.getId();
        tempmarker = marker;
        Log.d("markerid", String.valueOf(markerid));
    }
    /**
     * @param view ist die Basisklasse für Widgets, die verwendet werden, um interaktive UI-Komponenten (Schaltflächen, Textfelder usw.) zu erstellen..
     * @brief hier wird die distanz von der aktuellen position des nutzers zu den marker errechnet und dann entschieden in welcher umgebung per spinner
     * die marker angezeigt werden sollen.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnertext = parent.getItemAtPosition(position).toString();
        String[] output = spinnertext.split(" ");
        LatLng mitte = new LatLng(48.442398, 9.659075);
        int km = Integer.parseInt(output[0]);
        double tempdouble = km * 1000;
        boolean temp;
        drawCircle(mitte,tempdouble);
        if (output[0] != null && km != 0) {
            for (int a = 0; a < markers.size(); a++) {
                double distance = SphericalUtil.computeDistanceBetween(markers.get(a).getPosition(), mitte);
                if (distance > km * 1000) {
                    markers.get(a).setVisible(false);
                } else {
                    markers.get(a).setVisible(true);
                }
            }
        }
                if (km==0) {
                for (int b = 0; b < markers.size(); b++) {
                    markers.get(b).setVisible(true);
            }
        }
    }
    /**
     * @param point ist die übergabe der position des nutzers.
     * @param rad ist der radius vom kreis.
     * @brief hier wird ein kreis erstellt.
     */
    private void drawCircle(LatLng point, double rad){

        if(drawncircle!=null){
            drawncircle.remove();
        }
        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();

        // Specifying the center of the circle
        circleOptions.center(point);

        // Radius of the circle
        circleOptions.radius(rad);

        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);

        // Fill color of the circle
        circleOptions.fillColor(0x30ff0000);

        // Border width of the circle
        circleOptions.strokeWidth(2);

        // Adding the circle to the GoogleMap
        drawncircle =mMap.addCircle(circleOptions);

    }
    /**
     * @brief wenn im spinner nichts ausgewählt ist wird diese funktion aufgerufen und es soll nichts passieren.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

