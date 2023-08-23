package com.example.emobility.classes;


/**
 * @class Developer
 * @brief
 */

public class Developer extends User
{
    /**
     * @param username
     * @param password
     * @param firstName
     * @param surname
     * @param age
     * @brief Zuständig für das anlegen eines Accounts
     */
    public Developer(int id,String username, String password, String firstName, String surname, int age, boolean userView) {
        super(id,username, password, firstName, surname, age,userView);
    }

}
