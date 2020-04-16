/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import connection.Conexion;
import idao.IHorarioDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Docente;
import models.Horario;

/**
 *
 * @author Miguel
 */
public class HorarioDaoImpl implements IHorarioDao {

    @Override
    public boolean save(Horario data) {
        boolean guardar = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call guardar_horario(?,?,?,?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            
            stmt.setInt(1, data.getDocenteId());
            stmt.setInt(2, data.getProgramaId());
            stmt.setInt(3, data.getAsignaturaId());
            stmt.setInt(4, data.getSalonId());
            stmt.setInt(5, data.getDiaId());
            stmt.setInt(6, data.getDisponibilidadId());
            stmt.setInt(7, data.getAnno());
            stmt.setString(8, data.getHoraInicial());
            stmt.setString(9, data.getHoraFinal());
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
    public List<Horario> list() {
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;
        
        List<Horario> listaData = new ArrayList<Horario>();
        
        try {
            con = Conexion.conectart();
            stm = (Statement) con.createStatement();
            rs = stm.executeQuery("call listar_horario");
            while (rs.next()) {
                Horario horario = new Horario();
                horario.setId(rs.getInt(1));
                horario.setDocenteId(rs.getInt(2));
                horario.setProgramaId(rs.getInt(3));
                horario.setAsignaturaId(rs.getInt(4));
                horario.setSalonId(rs.getInt(5));
                horario.setDiaId(rs.getInt(6));
                horario.setDisponibilidadId(rs.getInt(7));
                horario.setAnno(rs.getInt(8));
                horario.setHoraInicial(rs.getString(9));
                horario.setHoraFinal(rs.getString(10));
                horario.setNombre(rs.getString(11));
                horario.setApellido(rs.getString(12));
                horario.setPrograma(rs.getString(13));
                horario.setAsignatura(rs.getString(14));
                horario.setSalon(rs.getString(15));
                horario.setDia(rs.getString(16));
                listaData.add(horario);
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
    public boolean update(Horario data) {
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call actualizar_horario(?,?,?,?,?,?,?,?,?,?)}";
            PreparedStatement stmt = con.prepareCall(query);
            
            stmt.setInt(1,data.getId());
            stmt.setInt(2, data.getDocenteId());
            stmt.setInt(3, data.getProgramaId());
            stmt.setInt(4, data.getAsignaturaId());
            stmt.setInt(5, data.getSalonId());
            stmt.setInt(6, data.getDiaId());
            stmt.setInt(7, data.getDisponibilidadId());
            stmt.setInt(8, data.getAnno());
            stmt.setString(9, data.getHoraInicial());
            stmt.setString(10, data.getHoraFinal());
            
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
    public boolean delete(Horario data) {
        boolean eliminado = false;
        Statement stm = null;
        Connection con = null;
        try {
            con = Conexion.conectart();
            String query = "{call eliminar_horario(?)}";
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
