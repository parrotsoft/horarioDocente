/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.Programa;

/**
 *
 * @author Miguel
 */
public interface IProgramaDao {
    public boolean save(Programa data);
    public List<Programa> list();
    public boolean update(Programa data);
    public boolean delete(Programa data);      
}
