/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.Disponibilidad;

/**
 *
 * @author Miguel
 */
public interface IDisponibilidadDao {
    public boolean save(Disponibilidad data);
    public List<Disponibilidad> list();
    public boolean update(Disponibilidad data);
    public boolean delete(Disponibilidad data);  
}
