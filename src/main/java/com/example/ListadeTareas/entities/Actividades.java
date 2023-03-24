package com.example.ListadeTareas.entities;

import jakarta.persistence.*;

@Entity
public class Actividades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean realizado;
    private String actividad;
    private String hora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    public Actividades(){}
    public Actividades(String actividad, String hora, boolean realizado, Usuarios usuario){
        this.actividad = actividad;
        this.hora = hora;
        this.realizado = realizado;
        this.usuario = usuario;
    }
    public Actividades(Long id,String actividad, String hora, boolean realizado, Usuarios usuario){
        this.id = id;
        this.actividad = actividad;
        this.hora = hora;
        this.realizado = realizado;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Usuarios getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Actividades{" +
                "id=" + id +
                ", realizado=" + realizado +
                ", actividad='" + actividad + '\'' +
                ", hora='" + hora + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
