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
import models.Asignatura;
import models.Usuario;

/**
 *
 * @author Miguel
 */
public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public boolean save(Usuario data) {
        boolean guardar = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call guardar_usuario(?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1, data.getDocenteId());
            stmt.setInt(2, data.getRolId());
            stmt.setString(3, data.getUsuario());
            stmt.setString(4, data.getClave());
            stmt.execute();
            guardar = true;
            con.close();
            stmt.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        } catch(ClassNotFoundException ex) {
            Logger.getLogger(ProgramaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return guardar;
    }

    @Override
    public List<Usuario> list() {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        List<Usuario> listaData = new ArrayList<Usuario>();
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery("call listar_usuarios");
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setDocenteId(rs.getInt(2));
                usuario.setRolId(rs.getInt(3));
                usuario.setUsuario(rs.getString(4));
                usuario.setClave(rs.getString(5));
                usuario.setNombres(rs.getString(6));
                usuario.setApellidos(rs.getString(7));
                usuario.setFecha_nacimiento(rs.getString(8));
                listaData.add(usuario);
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
        return listaData;
    }

    @Override
    public boolean update(Usuario data) {
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call actualizar_usuario(?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            
            stmt.setInt(1,data.getId());
            stmt.setInt(2, data.getDocenteId());
            stmt.setInt(3, data.getRolId());
            stmt.setString(4, data.getUsuario());
            stmt.setString(5, data.getClave());
            stmt.execute();
            actualizar = true;
            con.close();
            stmt.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        } catch(ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(ProgramaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizar;
    }

    @Override
    public boolean delete(Usuario data) {
        boolean eliminado = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call eliminar_usuario(?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1, data.getId());
            stmt.execute();
            eliminado = true;
            con.close();
            stmt.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        } catch(ClassNotFoundException ex) {
            Logger.getLogger(ProgramaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eliminado;
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
                user.setUsuario(rs.getString(2));
                user.setDocenteId(rs.getInt(4));
                user.setRolId(rs.getInt(10));
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
