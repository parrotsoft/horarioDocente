/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.ProgramaAsignatura;

/**
 *
 * @author Miguel
 */
public interface IProgramaAsignaturaDao {
    public boolean save(ProgramaAsignatura data);
    public List<ProgramaAsignatura> list();
    public boolean update(ProgramaAsignatura data);
    public boolean delete(ProgramaAsignatura data);        
}
