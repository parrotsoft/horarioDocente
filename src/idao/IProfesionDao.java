/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.Profesion;

/**
 *
 * @author Miguel
 */
public interface IProfesionDao {
    public boolean save(Profesion data);
    public List<Profesion> list();
    public boolean update(Profesion data);
    public boolean delete(Profesion data);    
}
