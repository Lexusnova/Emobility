package com.example.emobility.classes;
/**
 * @class Favourite
 * @brief Hier werden die Favourite Objekte erstellt.
 */
public class Favourite {
    private static int count = 0;
    private int favouriteID;   // ID
    private int ladesauleID; // Ladesäule der Betroffen ist
    /**
     * @param ladesauleID ist eine übergabe des wertes der markerid.
     * @brief Zuständig für das anlegen eines Favourite Objektes(konstruktor)
     */
    public Favourite(int ladesauleID) {
        this.favouriteID = count;
        this.ladesauleID = ladesauleID;
        count++;
    }

    public int getFavouriteID() {
        return favouriteID;
    }

    public void setFavouriteID(int favouriteID) {
        this.favouriteID = favouriteID;
    }

    public int getLadesauleID() {
        return ladesauleID;
    }

    public void setLadesauleID(int ladesauleID) {
        this.ladesauleID = ladesauleID;
    }
}
