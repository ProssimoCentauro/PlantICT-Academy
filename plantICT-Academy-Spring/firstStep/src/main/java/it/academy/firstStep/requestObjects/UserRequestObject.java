package it.academy.firstStep.requestObjects;

import it.academy.firstStep.rolesEnum.Roles;

public class UserRequestObject {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    //private Roles role;

    public UserRequestObject(String name, String surname, String username, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
    //    this.role = role;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
   /*public Roles getRole() {
        return role;
    }*/

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
   /* public void setRole(Roles role) {
        this.role = role;
    }*/
}
