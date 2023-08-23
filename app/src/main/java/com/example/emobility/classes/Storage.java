package com.example.emobility.classes;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * @class Storage
 * @brief Storage ist zuständig für die allgemeine Verwaltung von der Nutzerdaten
 * Im Zukunft ist es möglich hier eine Datenbank anzuwenden
 */
public class Storage {
    private List<User> userList = new ArrayList<>();

    public Storage() {
        //empty
    }

    /**
     * @brief Sucht nach User und Password ob es übereinstimmt
     * @param username Übergeben Username
     * @param password Übergeben Password
     * @return Benutzer gefunden Default Wert ist Benutzer nicht gefunden
     */
    public boolean checkUser(String username, String password) {
        boolean flagFound = false;
        Log.d("storageUser", username + ":" + password);
        String tempUser = null;
        String tempPassword = null;
        for(int i = 0; i < userList.size(); i++) {
             Log.d("StorageUser", userList.get(i).getUsername() + ":" + userList.get(i).getPassword() );
             //Überprüft ob Username und Password identisch sind, wenn ja Gefunden
             if (userList.get(i).getUsername().contains(username) && userList.get(i).getPassword().contains(password)) {
                Log.d("StorageUser", "User has been found");
                flagFound = true;
                break;
            }
        }
        if (flagFound == false) {
            Log.d("USER","User can't be found");
        }
        return flagFound; //default return value ist false
    }

    /**
     * @brief Schaut nach dem Name des Users
     * @param name Name des Developer
     * @return Gefunden true else Nicht Gefunden false
     */
    public boolean isItDeveloper(String name) {
        boolean foundDeveloper = false;
        for(int i = 0; i < userList.size(); i++) {
            if (userList.get(i) instanceof Developer && userList.get(i).getUsername().contains(name) && userList.get(i).getUserView() == false) {
                foundDeveloper = true;
                break;
            }
        }
        return foundDeveloper;
    }

    public void setDevelopersList(Developer developer) {
        this.userList.add(developer);
    }

    public void setUserList(User user) {
        this.userList.add(user);
    }
}
