package com.example.emobility.activitys;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.emobility.R;
import com.example.emobility.classes.Developer;
import com.example.emobility.classes.Storage;
import com.example.emobility.classes.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @class LoggingActivity
 * @brief Ist zu zuständig für das Logging des Users
 * MainAcitvity --> First Layout to show
 */
public class LoggingActivity extends AppCompatActivity {
    private Storage storage;
    private Button button1;
    public static final String EXTRA_MESSAGE = "";

    /**
     * @brief Datei eingelesen und Storage erstellt. Username und Password eingabe und schaut in System ob es vorhanden ist.
     * @param savedInstanceState Status wird hier weitergeben
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        storage = new Storage();
        readFromFile(); //Die Daten werden hier eingelesen aus des CSV Datei
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v){
                String usernameTemp = username.getText().toString();
                String passwordtemp = password.getText().toString();
                if(storage.checkUser(usernameTemp, passwordtemp)) { //If true Logging in
                    System.out.println("User has been found.");
                    Toast toast = Toast.makeText(LoggingActivity.this,"User has been found", Toast.LENGTH_SHORT);
                    toast.show();
                    if(storage.isItDeveloper(usernameTemp)) {
                        openMainAcitvity();
                    } else {
                        openMainAcitvity();
                    }

                } else {
                    Toast toast = Toast.makeText(LoggingActivity.this, "User can't be found", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    /**
     * @brief Die MainActivty wird hier geöffnet
     */
    public void openMainAcitvity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Message", EXTRA_MESSAGE);
        startActivity(intent);
    }

    /**
     * @brief Die csv Datei storage_user.csv aus der Ordner res/raw wird hier eingelesen
     * Liest den Inhaltet aus CSV Datei und ladet es In Storage User Container hinein
     */
    public void readFromFile() {
        boolean loaded = true;
        String line = ""; // eingelesene csvZeile
        String delimiter = ";"; // Trennzeichen
        File myFile; // Fileobjekt
        // Positionen innerhalb des CSV-Datei

        final int userViewPos = 6; // Position in der csvZeile für Developer Rechte
        try {
            // lokale Datei ansprechen
            //InputStream in = context.getResources().openRawResource(R.raw.storageUser);
            //myFile = new File(String.valueOf(this.getClass().getClassLoader().getResourceAsStream(file)));
            //Environment.getExternalStorageDirectory().getPath() + );
            //new File(getApplicationContext().getExternalFilesDir("NZSE").getPath() + "/" + csvFile);
            //FileInputStream fIn = new FileInputStream(myFile);
            //InputStreamReader isr = new InputStreamReader(fIn, "ISO_8859-1");//"Windows-1252");// StandardCharsets.UTF_16);
            InputStreamReader isr = new InputStreamReader(LoggingActivity.this.getResources().openRawResource(R.raw.storage_user), "ISO_8859-1");//"
            BufferedReader myReader = new BufferedReader(isr);


            System.out.println("csv Einlesen starten ");
            System.out.println("... die ersten Zeilen überlesen");

            String[] user = null;
            String username;
            String password;
            String firstname;
            String surname;
            int age;
            int id;
            boolean userView;

            myReader.readLine(); // Erste Zeile überspringen
            while ((line = myReader.readLine()) != null) // Dateiende
            {
                user = line.split(delimiter); //Inhalt wird hier gespeichert

                id = Integer.parseInt(user[0]);
                //Speichert die Werte in die Parameter
                username = user[1];
                password = user[2];
                firstname = user[3];
                surname = user[4];
                age = Integer.parseInt(user[5]);
                userView = Boolean.parseBoolean(user[6]);
                if (user[userViewPos].contains("true")) { // Überprüft ob der User ein Developer ist oder nicht
                    User user1 = new User(id,username,password,firstname,surname,age,userView);
                    storage.setUserList(user1);
                } else {
                    Developer developer = new Developer(id,username,password,firstname,surname,age,userView);
                    storage.setUserList(developer);
                }
            } // while
            myReader.close();

        } catch (Exception e) { //Catch Exception
            Log.d("loadTAG",e.getMessage());
            e.printStackTrace();
        }
    } // csvRead

}