package com.example.ListadeTareas.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "unico_nombre",columnNames = ("username")))
public class Usuarios{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private int edad;
    private String correo;
    private String password;
    private String roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Actividades> actividades;

    public Usuarios(){}
    public Usuarios(String username,int edad,String correo, String password, String roles, ArrayList<Actividades> actividades){
        this.username = username;
        this.edad = edad;
        this.correo = correo;
        this.password = password;
        this.roles = roles;
        this.actividades = actividades;
    }
    public Usuarios(Long id,String username,int edad,String correo, String password, String roles, ArrayList<Actividades> actividades){
        this.id = id;
        this.username = username;
        this.edad = edad;
        this.correo = correo;
        this.password = password;
        this.roles = roles;
        this.actividades = actividades;
    }

    public List<Actividades> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividades> actividades) {
        this.actividades = actividades;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", edad='" + edad + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
