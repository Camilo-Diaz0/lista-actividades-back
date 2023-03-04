package com.example.ListadeTareas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Usuarios{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String apellido;
    private String correo;
    private String password;
    private String roles;

    private Usuarios(){}
    /*public Usuarios(String username,String apellido,String correo, String password){
        this.username = username;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        roles = "ROlE_USER";
    }*/
    public Usuarios(String username,String apellido,String correo, String password, String roles){
        this.username = username;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String nombre) {
        this.username = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getRoles(){
        return roles;
    }
    public void setRoles(String roles){
        this.roles = roles;
    }
}
