package com.example.emobility.classes;
/**
 * @class Ladesaeulen
 * @brief Alle Ladesaeulen Objekte werden hier erstellt.
 */
public class Ladesaeulen {
    private int ladesauleID;
    private String betreiber;
    private String strasse;
    private String hausnummer;
    private int postleitzahl;
    private String ort;
    private double breitengrad;
    private double laengengrad;
    private int ladepunkte;
    private String fastcharging;
    private boolean funktionierbar;
    private boolean visibleInRange;
    private static int incrementer = 0;
    /**
     * @brief Zuständig für das anlegen eines Ladesaeulen Objektes(konstruktor)
     */
    public Ladesaeulen() {
        this.betreiber = null;
        this.strasse = null;
        this.hausnummer = null;
        this.postleitzahl = 0;
        this.ort = null;
        this.breitengrad = 0;
        this.laengengrad = 0;
        this.ladepunkte = 0;
        this.fastcharging = null;
        this.funktionierbar = true;
        this.visibleInRange = true;
        this.ladesauleID = incrementer;
        incrementer++;
    }

    public String getBetreiber() {
        return betreiber;
    }

    public void setBetreiber(String betreiber) {
        this.betreiber = betreiber;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(int postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public String getFastcharging() {
        return fastcharging;
    }

    public void setFastcharging(String fastcharging) {
        this.fastcharging = fastcharging;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public double getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(double breitengrad) {
        this.breitengrad = breitengrad;
    }

    public double getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(double laengengrad) {
        this.laengengrad = laengengrad;
    }

    public boolean getFunktionierbar() {
        return funktionierbar;
    }

    public void setFunktionierbar(boolean funktionierbar) {
        this.funktionierbar = funktionierbar;
    }

    public boolean getVisibleInRange() {
        return visibleInRange;
    }

    public void setVisibleInRange(boolean visibleInRange) {
        this.visibleInRange = visibleInRange;
    }

    public int getLadesauleID() {
        return ladesauleID;
    }

    public void setLadesauleID(int ladesauleID) {
        this.ladesauleID = ladesauleID;
    }

    public int getLadepunkte() {
        return ladepunkte;
    }

    public void setLadepunkte(int ladepunkte) {
        this.ladepunkte = ladepunkte;
    }
}