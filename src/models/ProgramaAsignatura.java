/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Miguel
 */
public class ProgramaAsignatura {
    private int id;
    private int programaId;
    private int asignaturaId;

    public ProgramaAsignatura(int id, int programaId, int asignaturaId) {
        this.id = id;
        this.programaId = programaId;
        this.asignaturaId = asignaturaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    
}
