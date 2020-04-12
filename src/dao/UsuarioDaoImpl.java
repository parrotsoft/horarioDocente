/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import connection.Conexion;
import idao.IUsuarioDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import models.Usuario;

/**
 *
 * @author Miguel
 */
public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public boolean save(Usuario data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Usuario data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Usuario data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean login(Usuario data) {
        boolean loginOk = false;
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.conectart();
            String sql = "SELECT COUNT(*) AS i FROM usuarios WHERE  usuario = '"+data.getUsuario()+"' AND clave = '"+data.getClave()+"';";
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                if(rs.getString("i").equals("1")){
                    loginOk = true;
                }
            }
            con.close();
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println("Error: Clase UsuarioDaoImpl");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return loginOk;
    }
    
}
