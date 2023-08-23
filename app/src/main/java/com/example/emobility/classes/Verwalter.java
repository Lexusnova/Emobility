package com.example.emobility.classes;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;


/**
 * @class Verwalter
 * @brief Der Verwalter ist zuständig für die Datenübergaben von Activits und Fragments.
 * Sie wird genutzt um Inhalt von Fragments zu Fragment zu übergeben.
 */
public class Verwalter {
    private static ArrayList<Ladesaeulen> ladesaeule = new ArrayList<>();
    private static ArrayList<Report> reportListe = new ArrayList<>();
    private static ArrayList<Favourite> favouritenListe = new ArrayList<>();
    private static boolean alreadyInit;
    private static ArrayList<Marker> favmarkers = new ArrayList<>();
    private static ArrayList<String> removeElements = new ArrayList<>();

    public Verwalter () {

    }


    /**
     * @brief Diese Funktion überprüft die Aktuelle Anzahl an Ladesaäulen, existiert sie, dann wir dein Report erstellt.
     * Die Ladesaäule wird dann als Defekt markiert.
     * @param leadsaeuleID Die LadesäuleID von der Ladesäule Liste auf Google Maps wird übergeben
     * @param funktioniert Beschreibt ob die Ladesäule noch funktioniert
     * @param isInRange Auf der Map werden die Marker grau gekenntlicht gemacht
     * @param report Ist der Report die in der Arrayliste gespeichert wird.
     */

    public static void sendReport(int leadsaeuleID ,boolean funktioniert, boolean isInRange, Report report) {
       for (int i = 0; i < ladesaeule.size(); i++) {
           if (ladesaeule.get(i).getLadesauleID() == leadsaeuleID) {
               ladesaeule.get(i).setFunktionierbar(false);
               ladesaeule.get(i).setVisibleInRange(false);
               reportListe.add(report);
               break;
           }
       }
    }

    /**
     * @brief Übergibt die Favouriten Objekt und speichert es in den Container.
     * @param favourite Favourite Objekt das den Ladesaulen ID beinhaltet und wird dann
     */
    public static void sendFavourite(Favourite favourite) {
        for (int i = 0; i < ladesaeule.size(); i++) {
            if (ladesaeule.get(i).getLadesauleID() == favourite.getLadesauleID()) {
                favouritenListe.add(favourite);
                break;
            }
        }
    }


    /**
     * @brief hier kommen die ganzen Setter und Getter Functions
     */
    public static ArrayList<String> getRemoveElements() {
        return removeElements;
    }

    public static void setRemoveElements(ArrayList<String> removeElements) {
        Verwalter.removeElements = removeElements;
    }


    public static ArrayList<Marker> getFavmarkers() {
        return favmarkers;
    }

    public static void setFavmarkers(ArrayList<Marker> favmarkers) {
        Verwalter.favmarkers = favmarkers;
    }



    public static ArrayList<Report> getReportListe() {
        return reportListe;
    }

    public static void setReportListe(ArrayList<Report> reportListe) {
        Verwalter.reportListe = reportListe;
    }

    public static void removeItem(int position) {
        Verwalter.reportListe.remove(position);
    }

    public static ArrayList<Ladesaeulen> getLadesaeule() {
        return ladesaeule;
    }

    public static void setLadesaeule(ArrayList<Ladesaeulen> ladesaeule) {
        Verwalter.ladesaeule = ladesaeule;
    }

    public static void pushLadesaeule(Ladesaeulen ladesaeulen) {
        Verwalter.ladesaeule.add(ladesaeulen);
    }


    public static ArrayList<Favourite> getFavouritenListe() {
        return favouritenListe;
    }

    public static void setFavouritenListe(ArrayList<Favourite> favouritenListe) {
        Verwalter.favouritenListe = favouritenListe;
    }

    public static boolean getAlreadyInit() {
        return alreadyInit;
    }

    public static void setAlreadyInit(boolean alreadyInit) {
        Verwalter.alreadyInit = alreadyInit;
    }



}
