/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.Dia;

/**
 *
 * @author Miguel
 */
public interface IDiaDao {
    public boolean save(Dia data);
    public List<Dia> list();
    public boolean update(Dia data);
    public boolean delete(Dia data);   
}
