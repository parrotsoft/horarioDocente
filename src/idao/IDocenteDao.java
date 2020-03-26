/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.Docente;

/**
 *
 * @author Miguel
 */
public interface IDocenteDao {
    public boolean save(Docente data);
    public List<Docente> list();
    public boolean update(Docente data);
    public boolean delete(Docente data);  
}
