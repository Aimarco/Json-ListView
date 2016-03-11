package com.example.furwin.modfieapp_demo;

/**
 * Created by Aimar on 08/03/2016.
 */
public class Persona{
    private Name nombre;
    private Location location;
    private Pictures picture;
    private String genero,email,username,phone;

    public Name getNombre() {

        return nombre;
    }

    public void setNombre(Name nombre) {
        this.nombre = nombre;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Pictures getPicture() {
        return picture;
    }

    public void setPicture(Pictures picture) {
        this.picture = picture;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
