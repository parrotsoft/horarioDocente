/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.Salon;

/**
 *
 * @author Miguel
 */
public interface ISalonDao {
    public boolean save(Salon data);
    public List<Salon> list();
    public boolean update(Salon data);
    public boolean delete(Salon data);       
}
