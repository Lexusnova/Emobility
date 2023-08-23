package com.example.emobility.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @class Report
 * @brief Übergabe des Reports zu anderen Activity
 */
public class Report implements Parcelable {
    private static int count = 0;
    private int reportID;   // ID
    private String regard;  // Betreff
    private String context; // Inhalt
    private boolean stateOf; // Funktionsfähig oder nicht
    private int ladesauleID; // Ladesäule der Betroffen ist

    /**
     * @brief Erstellen eines Reports
     * @param regard Unsere
     * @param context kontext was für ein Fehler die Ladesäule hat (Message)
     * @param stateOf State von der Ladesäule
     * @param ladesauleID Die ID der Ladesäule
     */
    public Report(String regard, String context, boolean stateOf, int ladesauleID) {
        this.reportID = count;
        this.regard = regard;
        this.context = context;
        this.stateOf = stateOf;
        this.ladesauleID = ladesauleID;
        count++;
    }

    /**
     * @brief Parcelling part
     * @param in Inhalte aus In wird in unseren Attributen hinzugefügt
     */
    protected Report(Parcel in) {
        reportID = in.readInt();
        regard = in.readString();
        context = in.readString();
        stateOf = in.readByte() != 0;
        ladesauleID = in.readInt();
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *
     * @param parcel unser Paracel inhalte wird überschrieben
     * @param i position
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(reportID);
        parcel.writeString(regard);
        parcel.writeString(context);
        parcel.writeByte((byte) (stateOf ? 1 : 0));
        parcel.writeInt(ladesauleID);
    }
    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getRegard() {
        return regard;
    }

    public void setRegard(String regard) {
        this.regard = regard;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public boolean getStateOf() {
        return stateOf;
    }

    public void setStateOf(boolean stateOf) {
        this.stateOf = stateOf;
    }

    public int getLadesauleID() {
        return ladesauleID;
    }

    public void setLadesauleID(int ladesauleID) {
        this.ladesauleID = ladesauleID;
    }

}
