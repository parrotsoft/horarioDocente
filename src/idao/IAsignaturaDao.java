/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.Asignatura;
/**
 *
 * @author Miguel
 */
public interface IAsignaturaDao {
    public boolean save(Asignatura data);
    public List<Asignatura> list();
    public boolean update(Asignatura data);
    public boolean delete(Asignatura data);
}
