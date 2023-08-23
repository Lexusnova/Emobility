package com.example.emobility.classes;


/**
 * @class User
 * @brief Ist Unser User der sich für das System anmeldet
 */

public class User
{
    private int id;
    private String username; //Benutzername
    private String password; //
    private String firstName; //Vorname
    private String surname; //Nachname
    private int age; //Alter
    private boolean userView;

    /**
     * @brief Zuständig für das anlegen eines Accounts
     * @param id Id des Users
     * @param username Username
     * @param password Password des Users
     * @param firstName Vorname
     * @param surname Nachname
     * @param age Alter des Benutzer
     * @param userView Benutzer oder Devopler Rechte
     */
    public User(int id,String username, String password, String firstName, String surname, int age, boolean userView) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.userView = userView;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getUserView() {
        return userView;
    }

    public void setUserView(boolean userView) {
        this.userView = userView;
    }

}
