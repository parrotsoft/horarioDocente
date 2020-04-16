/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AsignaturaDaoImpl;
import dao.DiaDaoImpl;
import dao.DisponibilidadDaoImpl;
import dao.DocenteDaoImpl;
import dao.HorarioDaoImpl;
import dao.ProgramaDaoImpl;
import dao.SalonDaoImpl;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Asignatura;
import models.Dia;
import models.Disponibilidad;
import models.Docente;
import models.Horario;
import models.Profesion;
import models.Programa;
import models.Salon;
import models.TipoDocumento;
import vista.Horarios;

/**
 *
 * @author miguel
 */
public class HorarioController implements ActionListener {
    
    Horarios viewHorarios = new Horarios();
    HorarioDaoImpl daoHorario = new HorarioDaoImpl();
    Horario horarioEdit = new Horario();
    
    List<Docente> docentes;
    DocenteDaoImpl daoDocente = new DocenteDaoImpl();
    
    List<Programa> programas;
    ProgramaDaoImpl daoPrograma = new ProgramaDaoImpl();
    
    List<Asignatura> asignaturas;
    AsignaturaDaoImpl daoAsignatura = new AsignaturaDaoImpl();
    
    List<Salon> salones;
    SalonDaoImpl daoSalon = new SalonDaoImpl();
    
    List<Dia> dias;
    DiaDaoImpl daoDias = new DiaDaoImpl();
    
    List<Disponibilidad> disponibilidades;
    DisponibilidadDaoImpl daoDisponibilidad = new DisponibilidadDaoImpl();
    
    public HorarioController(Horarios viewHorarios) {
        this.viewHorarios = viewHorarios;
        
        this.docentes = daoDocente.list();
        this.setComboBoxDocentes();
        
        this.programas = daoPrograma.list();
        this.setComboBoxProgramas();
        
        this.asignaturas = daoAsignatura.list();
        this.setComboBoxAsignaturas();
        
        this.salones = daoSalon.list();
        this.setComboBoxSalones();
        
        this.dias = daoDias.list();
        this.setComboBoxDias();
        
        this.viewHorarios.btnNuevo.addActionListener(this);
        this.viewHorarios.btnCancelar.addActionListener(this);
        this.viewHorarios.btnEliminar.addActionListener(this);
        this.viewHorarios.btnGuardar.addActionListener(this);
        
        // this.setTable();
        this.btnEnable(true);
        
        this.viewHorarios.txtProfesor.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                int index = viewHorarios.txtProfesor.getSelectedIndex();
                if (index != -1) {
                    Docente docente = docentes.get(index);
                    disponibilidades = daoDisponibilidad.listForDocente(docente.getId());
                    setComboBoxDisponibilidades();
                }        
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewHorarios.btnNuevo) {
            this.btnEnable(false);
        }
        
        if (e.getSource() == this.viewHorarios.btnCancelar) {
            this.btnEnable(true);
        }
        
        if (e.getSource() == this.viewHorarios.btnEliminar) {
            int input = JOptionPane.showConfirmDialog(null, 
                "Confirma la eliminaciòn de del registro?", "Seleccione una opcion...",JOptionPane.YES_NO_OPTION);
            if (input == 0) {
                if (this.daoHorario.delete(horarioEdit)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado de forma correcta!");
                    this.setTable();
                    this.btnEnable(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: no se puedo eliminar el registro!");
                }
            }
        }
        
        if (e.getSource() == this.viewHorarios.btnGuardar) {
            
            if (this.validateField()) {
                Docente docente = this.docentes.get(this.viewHorarios.txtProfesor.getSelectedIndex());
                Programa programa = this.programas.get(this.viewHorarios.txtPrograma.getSelectedIndex());
                Asignatura asignatura = this.asignaturas.get(this.viewHorarios.txtAsignatura.getSelectedIndex());
                Salon salon = this.salones.get(this.viewHorarios.txtSalon.getSelectedIndex());
                Dia dia = this.dias.get(this.viewHorarios.txtDia.getSelectedIndex());
                Disponibilidad disponibilidad = this.disponibilidades.get(this.viewHorarios.txtDisponibilidad.getSelectedIndex());
                
                if (horarioEdit.getId() != 0) {
                    horarioEdit.setDocenteId(docente.getId());
                    horarioEdit.setProgramaId(programa.getId());
                    horarioEdit.setAsignaturaId(asignatura.getId());
                    horarioEdit.setSalonId(salon.getId());
                    horarioEdit.setDiaId(dia.getId());
                    horarioEdit.setDisponibilidadId(disponibilidad.getId());
                    horarioEdit.setAnno(Integer.parseInt(this.viewHorarios.txtAno.getText()));
                    horarioEdit.setHoraInicial(this.viewHorarios.txtHoraInicial.getText());
                    horarioEdit.setHoraFinal(this.viewHorarios.txtHoraFinal.getText());
                    if (this.daoHorario.update(horarioEdit)) {
                        JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta!");
                        this.setTable();
                        this.btnEnable(true);
                    }
                } else {
                    Horario horario = new Horario();
                    horario.setDocenteId(docente.getId());
                    horario.setProgramaId(programa.getId());
                    horario.setAsignaturaId(asignatura.getId());
                    horario.setSalonId(salon.getId());
                    horario.setDiaId(dia.getId());
                    horario.setDisponibilidadId(disponibilidad.getId());
                    horario.setAnno(Integer.parseInt(this.viewHorarios.txtAno.getText()));
                    horario.setHoraInicial(this.viewHorarios.txtHoraInicial.getText());
                    horario.setHoraFinal(this.viewHorarios.txtHoraFinal.getText());
                    if (this.daoHorario.save(horario)) {
                        JOptionPane.showMessageDialog(null, "Registro almacenado de forma correcta!");
                        this.setTable();
                        this.btnEnable(true);
                    }
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Por favor verificar los todos los campos son requeridos...s");
            }
        }
    }
    
    private void setTable() {
        String[] cols = { "Profesor", "Programa", "Asignatura", "Salon", "Hora Inicial", "Hora Final", "Dia" };
        this.viewHorarios.tblData.setModel(new DefaultTableModel(null, cols));
        List<Horario> lista = this.daoHorario.list();
        DefaultTableModel model = (DefaultTableModel) this.viewHorarios.tblData.getModel();
        Object[] fila = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = lista.get(i).getDocenteId();
            fila[1] = lista.get(i).getProgramaId();
            fila[2] = lista.get(i).getAsignaturaId();
            fila[3] = lista.get(i).getSalonId();
            fila[4] = lista.get(i).getHoraInicial();
            fila[5] = lista.get(i).getHoraFinal();
            fila[6] = lista.get(i).getDiaId();
            model.addRow(fila);
        }
       
        this.viewHorarios.tblData.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    int linea = table.getSelectedRow();
                    horarioEdit = lista.get(linea);
                    btnEnable(false);
                    
                    viewHorarios.txtProfesor.setSelectedIndex(getPositionDocente(horarioEdit.getDocenteId()));
                    viewHorarios.txtPrograma.setSelectedIndex(getPositionPrograma(horarioEdit.getProgramaId()));
                    viewHorarios.txtAsignatura.setSelectedIndex(getPositionAsignatura(horarioEdit.getAsignaturaId()));
                    viewHorarios.txtSalon.setSelectedIndex(getPositionSalon(horarioEdit.getSalonId()));
                    viewHorarios.txtDia.setSelectedIndex(getPositionDia(horarioEdit.getDiaId()));
                    viewHorarios.txtDisponibilidad.setSelectedIndex(getPositionDisponibilidad(horarioEdit.getDisponibilidadId()));
                    viewHorarios.txtAno.setText(""+horarioEdit.getAnno());
                    viewHorarios.txtHoraInicial.setText(horarioEdit.getHoraInicial());
                    viewHorarios.txtHoraFinal.setText(horarioEdit.getHoraFinal());
                    
                    viewHorarios.btnEliminar.setEnabled(true);
                }
            }
        });
    }
    
    private void btnEnable(boolean enabled) {
        this.viewHorarios.txtProfesor.setSelectedIndex(-1);
        this.viewHorarios.txtPrograma.setSelectedIndex(-1);
        this.viewHorarios.txtAsignatura.setSelectedIndex(-1);
        this.viewHorarios.txtSalon.setSelectedIndex(-1);
        this.viewHorarios.txtDia.setSelectedIndex(-1);
        this.viewHorarios.txtDisponibilidad.setSelectedIndex(-1);
        this.viewHorarios.txtAno.setText("");
        this.viewHorarios.txtHoraInicial.setText("");
        this.viewHorarios.txtHoraFinal.setText("");
        
        this.viewHorarios.btnNuevo.setEnabled(enabled);
        this.viewHorarios.btnCancelar.setEnabled(!enabled);
        this.viewHorarios.btnEliminar.setEnabled(false);
        this.viewHorarios.btnGuardar.setEnabled(!enabled);
        
        this.viewHorarios.txtProfesor.setEnabled(!enabled);
        this.viewHorarios.txtPrograma.setEnabled(!enabled);
        this.viewHorarios.txtAsignatura.setEnabled(!enabled);
        this.viewHorarios.txtSalon.setEnabled(!enabled);
        this.viewHorarios.txtDia.setEnabled(!enabled);
        this.viewHorarios.txtDisponibilidad.setEnabled(!enabled);
        this.viewHorarios.txtAno.setEnabled(!enabled);
        this.viewHorarios.txtHoraInicial.setEnabled(!enabled);
        this.viewHorarios.txtHoraFinal.setEnabled(!enabled);
    }
    
    private void setComboBoxDocentes() {
        for (int i = 0; i < this.docentes.size(); i++) {
            String item = this.docentes.get(i).getNombres() + " " + this.docentes.get(i).getApellidos();
            this.viewHorarios.txtProfesor.addItem(item);
        }
    }
    
    private int getPositionDocente(int id) {
        int index = -1;
        for (int i = 0; i < docentes.size(); i++) {
            if (docentes.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
    
    private void setComboBoxProgramas() {
        for (int i = 0; i < this.programas.size(); i++) {
            String item = this.programas.get(i).getDescripcion();
            this.viewHorarios.txtPrograma.addItem(item);
        }
    }
    
    private int getPositionPrograma(int id) {
        int index = -1;
        for (int i = 0; i < programas.size(); i++) {
            if (programas.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
    
    private void setComboBoxAsignaturas() {
        for (int i = 0; i < this.asignaturas.size(); i++) {
            String item = this.asignaturas.get(i).getDescripcion();
            this.viewHorarios.txtAsignatura.addItem(item);
        }
    }
    
    private int getPositionAsignatura(int id) {
        int index = -1;
        for (int i = 0; i < asignaturas.size(); i++) {
            if (asignaturas.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
    
    private void setComboBoxSalones() {
        for (int i = 0; i < this.salones.size(); i++) {
            String item = this.salones.get(i).getDescripcion();
            this.viewHorarios.txtSalon.addItem(item);
        }
    }
    
    private int getPositionSalon(int id) {
        int index = -1;
        for (int i = 0; i < salones.size(); i++) {
            if (salones.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
    
    private void setComboBoxDias() {
        for (int i = 0; i < this.dias.size(); i++) {
            String item = this.dias.get(i).getDescripcion();
            this.viewHorarios.txtDia.addItem(item);
        }
    }
    
    private int getPositionDia(int id) {
        int index = -1;
        for (int i = 0; i < dias.size(); i++) {
            if (dias.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
    
    private void setComboBoxDisponibilidades() {
        for (int i = 0; i < this.disponibilidades.size(); i++) {
            String item = this.disponibilidades.get(i).getDia() 
                    + " de " + this.disponibilidades.get(i).getHoraInicial() 
                    + " a " + this.disponibilidades.get(i).getHoraFinal();
            this.viewHorarios.txtDisponibilidad.addItem(item);
        }
    }
    
    private int getPositionDisponibilidad(int id) {
        int index = -1;
        for (int i = 0; i < disponibilidades.size(); i++) {
            if (disponibilidades.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }
    
    private boolean validateField() {
        if ((this.viewHorarios.txtProfesor.getSelectedIndex() != -1) && 
            (this.viewHorarios.txtPrograma.getSelectedIndex() != -1) && 
            (this.viewHorarios.txtAsignatura.getSelectedIndex() != -1) &&
            (this.viewHorarios.txtSalon.getSelectedIndex() != -1) &&
            (this.viewHorarios.txtDia.getSelectedIndex() != -1) &&
            (this.viewHorarios.txtDisponibilidad.getSelectedIndex() != -1) &&
            (this.viewHorarios.txtAno.getText().length() > 0) &&
            (this.viewHorarios.txtHoraInicial.getText().length() > 0) &&
            (this.viewHorarios.txtHoraFinal.getText().length() > 0)) {
             return true;
        } else {
            return false;
        }
    }
    
    
    
}
