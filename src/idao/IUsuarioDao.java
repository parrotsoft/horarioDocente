/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;
import models.Usuario;

/**
 *
 * @author Miguel
 */
public interface IUsuarioDao {
    public boolean save(Usuario data);
    public List<Usuario> list();
    public boolean update(Usuario data);
    public boolean delete(Usuario data);     
}
