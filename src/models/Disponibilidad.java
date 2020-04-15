/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Time;

/**
 *
 * @author Miguel
 */
public class Disponibilidad {
    private int id;
    private int diaId;
    private int docenteId;
    private String horaInicial;
    private String horaFinal;
    private String comentario;
    private boolean estado;
    private String dia;
    
    public Disponibilidad() {
        
    }

    public Disponibilidad(int id, int diaId, int docenteId, String horaInicial, String horaFinal, String comentario, boolean estado) {
        this.id = id;
        this.diaId = diaId;
        this.docenteId = docenteId;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.comentario = comentario;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiaId() {
        return diaId;
    }

    public void setDiaId(int diaId) {
        this.diaId = diaId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
    
    
}
