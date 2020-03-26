/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.Rol;

/**
 *
 * @author Miguel
 */
public interface IRolDao {
    public boolean save(Rol data);
    public List<Rol> list();
    public boolean update(Rol data);
    public boolean delete(Rol data);     
}
