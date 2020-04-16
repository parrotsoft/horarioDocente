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
public class Horario {
    private int id;
    private int usuarioId;
    private int diaId;
    private int salonId;
    private int programaId;
    private int asignaturaId;
    private int docenteId;
    private int anno;
    private int semestre;
    private String horaInicial;
    private String horaFinal;
    private boolean estado;
    private int disponibilidadId;
    private String programa;
    private String asignatura;
    private String salon;
    private String dia;
    private String nombre;
    private String apellido;
    
    public Horario() {
        
    }

    public Horario(int id, int usuarioId, int diaId, int salonId, int programaId, int asignaturaId, int docenteId, int anno, int semestre, String horaInicial, String horaFinal, boolean estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.diaId = diaId;
        this.salonId = salonId;
        this.programaId = programaId;
        this.asignaturaId = asignaturaId;
        this.docenteId = docenteId;
        this.anno = anno;
        this.semestre = semestre;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getDiaId() {
        return diaId;
    }

    public void setDiaId(int diaId) {
        this.diaId = diaId;
    }

    public int getSalonId() {
        return salonId;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
    }

    public int getProgramaId() {
        return programaId;
    }

    public void setProgramaId(int programaId) {
        this.programaId = programaId;
    }

    public int getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(int asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getDisponibilidadId() {
        return disponibilidadId;
    }

    public void setDisponibilidadId(int disponibilidadId) {
        this.disponibilidadId = disponibilidadId;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
    
}
