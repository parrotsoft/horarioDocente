/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import connection.Conexion;
import idao.IDocenteDao;
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
import models.Docente;

/**
 *
 * @author Miguel
 */
public class DocenteDaoImpl implements IDocenteDao {

    @Override
    public boolean save(Docente data) {
        boolean guardar = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call guardar_docente(?,?,?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            
            stmt.setInt(1, data.getIdentificacion());
            stmt.setInt(2, data.getUsuarioId());
            stmt.setInt(3, data.getTipoDocumento());
            stmt.setInt(4, data.getProfesionId());
            stmt.setString(5, data.getNombres());
            stmt.setString(6, data.getApellidos());
            stmt.setString(7, data.getFechaNacimiento());
            stmt.setString(8, data.getCorreo());
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
    public List<Docente> list() {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        List<Docente> listaData = new ArrayList<Docente>();
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery("call listar_docentes");
            while (rs.next()) {
                Docente docente = new Docente();
                docente.setId(rs.getInt(1));
                docente.setIdentificacion(rs.getInt(2));
                docente.setUsuarioId(rs.getInt(3));
                docente.setTipoDocumento(rs.getInt(4));
                docente.setProfesionId(rs.getInt(5));
                docente.setNombres(rs.getString(6));
                docente.setApellidos(rs.getString(7));
                docente.setFechaNacimiento(rs.getString(8));
                docente.setCorreo(rs.getString(9));
                docente.setTipoDocumentoText(rs.getString(10));
                docente.setProfesionText(rs.getString(11));
                listaData.add(docente);
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
    public boolean update(Docente data) {
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call actualizar_docente(?,?,?,?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            
            stmt.setInt(1,data.getId());
            stmt.setInt(2, data.getIdentificacion());
            stmt.setInt(3, data.getUsuarioId());
            stmt.setInt(4, data.getTipoDocumento());
            stmt.setInt(5, data.getProfesionId());
            stmt.setString(6, data.getNombres());
            stmt.setString(7, data.getApellidos());
            stmt.setString(8, data.getFechaNacimiento());
            stmt.setString(9, data.getCorreo());
            
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
    public boolean delete(Docente data) {
        boolean eliminado = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call eliminar_docente(?)}";
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
