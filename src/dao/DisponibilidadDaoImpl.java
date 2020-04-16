/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import connection.Conexion;
import idao.IDisponibilidadDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Disponibilidad;
import models.Rol;

/**
 *
 * @author Miguel
 */
public class DisponibilidadDaoImpl implements IDisponibilidadDao {

    @Override
    public boolean save(Disponibilidad data) {
        boolean guardar = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call guardar_disponibilidad(?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1, data.getDiaId());
            stmt.setInt(2, data.getDocenteId());
            stmt.setString(3, data.getHoraInicial());
            stmt.setString(4, data.getHoraFinal());
            stmt.setString(5, data.getComentario());
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
    public List<Disponibilidad> list() {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        List<Disponibilidad> listaData = new ArrayList<Disponibilidad>();
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery("call listar_disponibilidades");
            while (rs.next()) {
                Disponibilidad disponibilidad = new Disponibilidad();
                disponibilidad.setId(rs.getInt(1));
                disponibilidad.setDiaId(rs.getInt(2));
                disponibilidad.setDocenteId(rs.getInt(3));
                disponibilidad.setHoraInicial(rs.getString(4));
                disponibilidad.setHoraFinal(rs.getString(5));
                disponibilidad.setComentario(rs.getString(6));
                disponibilidad.setDia(rs.getString(7));
                listaData.add(disponibilidad);
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
    
    public List<Disponibilidad> listForDocente(int id) {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        List<Disponibilidad> listaData = new ArrayList<Disponibilidad>();
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            String query = "{call list_por_docente(?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Disponibilidad disponibilidad = new Disponibilidad();
                disponibilidad.setId(rs.getInt(1));
                disponibilidad.setDiaId(rs.getInt(2));
                disponibilidad.setDocenteId(rs.getInt(3));
                disponibilidad.setHoraInicial(rs.getString(4));
                disponibilidad.setHoraFinal(rs.getString(5));
                disponibilidad.setComentario(rs.getString(6));
                disponibilidad.setDia(rs.getString(7));
                listaData.add(disponibilidad);
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
    public boolean update(Disponibilidad data) {
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call actualizar_disponibilidad(?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            stmt.setInt(1, data.getId());
            stmt.setInt(2, data.getDiaId());
            stmt.setInt(3, data.getDocenteId());
            stmt.setString(4, data.getHoraInicial());
            stmt.setString(5, data.getHoraFinal());
            stmt.setString(6, data.getComentario());
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
    public boolean delete(Disponibilidad data) {
        boolean eliminado = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call eliminar_disponibilidad(?)}";
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
