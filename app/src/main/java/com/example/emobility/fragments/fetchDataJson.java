package com.example.emobility.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emobility.classes.Ladesaeulen;
import com.example.emobility.classes.Verwalter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @class Ladesaeulen
 * @brief es werden die Ladesaeulen von der json file in /assets in das ladesaeulen objekt geladen.
 */
public class fetchDataJson {
    Context context;
    ArrayList<Ladesaeulen> saeulen = new ArrayList<>();
    String data = "";
    String line;
    int ladesaeuleID = 0;

    /**
     * @param context die übergabe des context interface
     * @brief ruft loadsaeulen auf um die ladesaeulen von der json zu lesen.
     */
    public fetchDataJson(Context context) {
        this.context = context;
        loadSaeulen();
    }
    /**
     * @brief hier werden die ladesaeulen aus der json file als objekt gespeichert und in ein array gepushed.
     */
    public void loadSaeulen() {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("saulen.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("Ladesaeulen");

            Log.d("ladesize", String.valueOf(jsonArray.length()));
            for (int i = 0; i < 50; i++) {
                JSONObject ladesaeule = jsonArray.getJSONObject(i);
                String betreibername = ladesaeule.getString("Betreiber");
                String strassename = ladesaeule.getString("Strasse");
                String hausnummername = ladesaeule.getString("Hausnummer");
                int plzname = ladesaeule.getInt("Postleitzahl");
                String ortname = ladesaeule.getString("Ort");
                String breitengradname = ladesaeule.getString("Breitengrad");
                double doublebreitengradname = Double.parseDouble(breitengradname.replace(',', '.'));
                String laengengradname = ladesaeule.getString("Laengengrad");
                double doublelaengengradname = Double.parseDouble(laengengradname.replace(',', '.'));
                String fastcharging = ladesaeule.getString("Art der Ladeeinrichung");
                int ladep = ladesaeule.getInt("Anzahl Ladepunkte");
                Ladesaeulen saeule = new Ladesaeulen();
                saeule.setBetreiber(betreibername);
                saeule.setStrasse(strassename);
                saeule.setHausnummer(hausnummername);
                saeule.setPostleitzahl(plzname);
                saeule.setOrt(ortname);
                saeule.setBreitengrad(doublebreitengradname);
                saeule.setLaengengrad(doublelaengengradname);
                saeule.setFastcharging(fastcharging);
                saeule.setLadepunkte(ladep);
                saeule.setVisibleInRange(true);
                saeule.setFunktionierbar(true);
                saeule.setLadesauleID(ladesaeuleID);
                ladesaeuleID++;
                saeulen.add(saeule);
                Log.d("saulesize", String.valueOf(saeulen.size()));
            }
            Verwalter.setLadesaeule(saeulen);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
