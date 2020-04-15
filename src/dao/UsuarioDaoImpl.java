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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public int login(Usuario data) {
        int loginOk = 0;
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.conectart();
            String sql = "SELECT * FROM usuarios WHERE  usuario = '"+data.getUsuario()+"' AND clave = '"+data.getClave()+"';";
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                loginOk = rs.getInt(1);
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
    
    public Usuario getUsuario(int id) {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            String query = "{call get_usuario(?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getInt(1));
                user.setNombres(rs.getString(5));
                user.setApellidos(rs.getString(6));
                user.setCorreo(rs.getString(7));
                user.setFecha_nacimiento(rs.getString(8));
                return user;
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(ProgramaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
