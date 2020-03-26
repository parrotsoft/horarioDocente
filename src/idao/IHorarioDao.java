/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.Horario;

/**
 *
 * @author Miguel
 */
public interface IHorarioDao {
    public boolean save(Horario data);
    public List<Horario> list();
    public boolean update(Horario data);
    public boolean delete(Horario data);    
}
