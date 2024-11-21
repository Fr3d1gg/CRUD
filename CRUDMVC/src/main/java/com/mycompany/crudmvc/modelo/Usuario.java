package com.mycompany.crudmvc.modelo;
/*
En esta parte creamos la clase usuario,la cual nos va a permitir que el usuario pueda colocar los datos que va a ingresar
a nuestra base de datos,ademas que nos va a permitir manipular los datos a traves de las diferentes vistas.
Como nota adicional,la contraseña podria cifrarse para poder tener mayor seguridad en nuestro proyecto,pero al
ser algo sencillo y practico decidi omitirlo,pero sin dejarlo de mencionar.
*/
public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String pais;
    private String rol;
    private String password;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}