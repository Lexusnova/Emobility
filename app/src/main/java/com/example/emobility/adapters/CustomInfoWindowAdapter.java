package com.example.emobility.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.emobility.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
/**
 * @class CustomInfoWindowAdapter
 * @brief Hier handel es sich um ein fenster der mit bestimmten daten erstellt wird nachdem man auf ein marker klickt.
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
    private final View mWindow;
    private Context mContext;
    /**
     * @param context die übergabe des context interface
     * @brief hier wird das fenster mit den gegebenen daten erstellt.
     */
    public CustomInfoWindowAdapter(Context context){
       mContext = context;
       mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window,null);
    }
    /**
     * @param marker die übergabe des aktuell ausgewählten markers(snippet übergabe & title)
     * @param view View ist die Basisklasse für Widgets, die verwendet werden, um interaktive UI-Komponenten (Schaltflächen, Textfelder usw.) zu erstellen.
     * @brief der Funktion werden vom marker daten übergeben die dann im fenster gesetzt werden.
     */
    private void rendowWindowText(Marker marker, View view){
        String title = marker.getTitle();
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
        if(!title.equals("")){
            tvTitle.setText(title);
        }
           String snippet  = marker.getSnippet();
        TextView tvSnippet = (TextView) view.findViewById(R.id.snippet);

        if(!snippet.equals("")){
            tvSnippet.setText(snippet);
        }
    }
    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        rendowWindowText(marker,mWindow);
        return mWindow;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        rendowWindowText(marker,mWindow);
        return mWindow;
    }
}
