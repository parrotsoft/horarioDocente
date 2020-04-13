/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import connection.Conexion;
import idao.IDiaDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Dia;

/**
 *
 * @author Miguel
 */
public class DiaDaoImpl implements IDiaDao {

    @Override
    public boolean save(Dia data) {
        boolean guardar = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call guardar_dia(?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setString(1, data.getDescripcion());
            stmt.setBoolean(2, data.isEstado());
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
    public List<Dia> list() {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        List<Dia> listaData = new ArrayList<Dia>();
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery("call listar_dias");
            while (rs.next()) {
                Dia dia = new Dia();
                dia.setId(rs.getInt(1));
                dia.setDescripcion(rs.getString(2));
                listaData.add(dia);
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
    public boolean update(Dia data) {
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call actualizar_dia(?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1,data.getId());
            stmt.setString(2, data.getDescripcion());
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
    public boolean delete(Dia data) {
        boolean eliminado = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call eliminar_dia(?)}";
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
    
}
