package modelo;

import modelo.Estadopoliza;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Welcome extends JFrame {
    //atriv monos en la silla 
    JMenuBar mb;
    JMenu archivo, bd;
    JMenuItem bd_C, bd_R, bd_U, bd_D;

    public JLabel lbl, lb_id, lb_nom, lb_desc, lb_act, lb_can, lb_ven;
    public JTextField id, nombre, descripcion, activa, cancelada, vencida;
    public JButton btn_alta, btn_consulta, btn_actualizar, btn_eliminar, btn_buscar_id;
    Estadopoliza poliza;
      
    Welcome() {
        this.setBounds(150, 50, 850, 650);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        this.setBackground(Color.yellow);
        this.setResizable(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Menú superior 
        this.mb = new JMenuBar(); //elemento 
        this.setJMenuBar(mb);
        this.archivo = new JMenu("Archivo");
        this.mb.add(this.archivo);
        
        this.bd = new JMenu("Base de datos");
        this.mb.add(bd);
        //crear cubo de minecra
        this.bd_C = new JMenuItem("C-Crear");
        this.bd.add(this.bd_C);
        this.bd_C.addActionListener((var e) -> insertar());
        //consulta xd
        this.bd_R = new JMenuItem("R-Consultar");
        this.bd.add(this.bd_R);
        this.bd_R.addActionListener((var e) -> consultar());
        //no sirve 
        this.bd_U = new JMenuItem("U- Mejorar");
        this.bd.add(this.bd_U);
        this.bd_U.addActionListener((var e) -> actualizar());
        
        this.bd_D = new JMenuItem("D- Borrar");
        this.bd.add(this.bd_D);
        this.bd_D.addActionListener((var e) -> eliminar());

        this.setLayout(null);
        this.lbl = new JLabel("POOT LARA JOSE ADRIAN - CRUD Estado de Póliza");
        this.lbl.setBounds(250, 15, 400, 25);

        this.lb_id = new JLabel("ID");
        this.lb_id.setBounds(180, 55, 100, 25);
        this.id = new JTextField("");
        this.id.setBounds(290, 55, 230, 28);
        this.add(this.id);

        this.lb_nom = new JLabel("Nombre");
        this.lb_nom.setBounds(180, 95, 100, 25);
        this.nombre = new JTextField("");
        this.nombre.setBounds(290, 95, 230, 28);

        this.lb_desc = new JLabel("Descripción");
        this.lb_desc.setBounds(180, 135, 100, 25);
        this.descripcion = new JTextField("");
        this.descripcion.setBounds(290, 135, 230, 28);

        this.lb_act = new JLabel("Activa");
        this.lb_act.setBounds(180, 175, 100, 25);
        this.activa = new JTextField("");
        this.activa.setBounds(290, 175, 230, 28);

        this.lb_can = new JLabel("Cancelada");
        this.lb_can.setBounds(180, 215, 100, 25);
        this.cancelada = new JTextField("");
        this.cancelada.setBounds(290, 215, 230, 28);

        this.lb_ven = new JLabel("Vencida");
        this.lb_ven.setBounds(180, 255, 100, 25);
        this.vencida = new JTextField("");
        this.vencida.setBounds(290, 255, 230, 28);

        int anchoBtn = 110;
        int altoBtn = 38;

        this.btn_consulta = new JButton("Consultar BD");
        this.btn_consulta.setBounds(90, 310, anchoBtn, altoBtn);

        this.btn_alta = new JButton("Alta BD");
        this.btn_alta.setBounds(210, 310, anchoBtn, altoBtn);

        this.btn_actualizar = new JButton("Actualizar");
        this.btn_actualizar.setBounds(330, 310, anchoBtn, altoBtn);

        this.btn_buscar_id = new JButton("Buscar ID");
        this.btn_buscar_id.setBounds(450, 310, anchoBtn, altoBtn);

        this.btn_eliminar = new JButton("Eliminar ;v");
        this.btn_eliminar.setBounds(570, 310, anchoBtn, altoBtn);

        // Listeners
        this.btn_consulta.addActionListener((var e) -> consultar());
        this.btn_alta.addActionListener((var e) -> insertar());
        this.btn_actualizar.addActionListener((e) -> actualizar());
        this.btn_buscar_id.addActionListener((var e) -> buscarPorId());
        this.btn_eliminar.addActionListener((e) -> eliminar());

        // Agregar componentes a la ventana
        this.add(lb_id);
        this.add(btn_eliminar);
        this.add(btn_buscar_id);
        this.add(btn_actualizar);
        this.add(btn_alta);
        this.add(lb_desc);
        this.add(descripcion);
        this.add(lb_nom);
        this.add(nombre);
        this.add(lb_act);
        this.add(activa);
        this.add(lb_can);
        this.add(cancelada);
        this.add(lb_ven);
        this.add(vencida);
        this.add(lbl);
        this.add(btn_consulta);

        this.setVisible(true);
    }

    public void buscarPorId() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/2026TI32?useUnicode=true&characterEncoding=utf8", "root", "");
            PreparedStatement ps = cnx.prepareStatement("select * from estado_poliza where id = ?");
            ps.setInt(1, Integer.parseInt(this.id.getText()));
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.nombre.setText(rs.getString("nombre"));
                this.descripcion.setText(rs.getString("descripcion"));
                this.activa.setText(rs.getString("activa"));
                this.cancelada.setText(rs.getString("cancelada"));
                this.vencida.setText(rs.getString("vencida"));
                JOptionPane.showMessageDialog(rootPane, "Póliza encontrada con éxito");
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se encontró ninguna póliza con ese ID");
            }
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, "Ingresa un ID numérico válido");
        }
    }

    public void actualizar() {
        try {
            Connection cnx;
            PreparedStatement ps;
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/2026TI32?useUnicode=true&characterEncoding=utf8", "root", "");
            ps = cnx.prepareStatement("update estado_poliza set descripcion = ?, nombre = ?, activa = ?, cancelada = ?, vencida = ? where id = ?");
            
            ps.setString(1, this.descripcion.getText());
            ps.setString(2, this.nombre.getText());
            ps.setString(3, this.activa.getText());
            ps.setString(4, this.cancelada.getText());
            ps.setString(5, this.vencida.getText());
            ps.setInt(6, Integer.parseInt(this.id.getText()));
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Registro actualizado correctamente");
            this.consultar();
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        }
    }
    //METODOS DE DATOS VOID
      //metodos |   1Q11Q
    public void consultar() {
        try {
            Connection cnx;
            PreparedStatement ps;
            ResultSet rs;
            
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/2026TI32?useUnicode=true&characterEncoding=utf8", "root", "");
            System.out.println("Conexion exitosa");

            ps = cnx.prepareStatement("select * from estado_poliza");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("nombre") + " | " + rs.getString("descripcion") + " | Activa: " + rs.getString("activa") + " | Vencida: " + rs.getString("vencida"));
            }
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error conexion: " + ex.getMessage());
        }
    }

    public void insertar() {
        try {
            Connection cnx;
            PreparedStatement ps;

            cnx = DriverManager.getConnection("jdbc:mysql://localhost/2026TI32?useUnicode=true&characterEncoding=utf8", "root", "");
            ps = cnx.prepareStatement("insert into estado_poliza (descripcion, nombre, activa, cancelada, vencida) values (?, ?, ?, ?, ?)");
            
            ps.setString(1, this.descripcion.getText());
            ps.setString(2, this.nombre.getText());
            ps.setString(3, this.activa.getText());
            ps.setString(4, this.cancelada.getText());
            ps.setString(5, this.vencida.getText());

            ps.execute();

            JOptionPane.showMessageDialog(rootPane, "Registro guardado exitosamente");
            this.descripcion.setText("");
            this.nombre.setText("");
            this.activa.setText("");
            this.cancelada.setText("");
            this.vencida.setText("");
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar: " + ex.getMessage());
        }
    }

    public void eliminar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/2026TI32?useUnicode=true&characterEncoding=utf8", "root", "");
            PreparedStatement ps = cnx.prepareStatement("delete from estado_poliza where id = ?");

            ps.setInt(1, Integer.parseInt(this.id.getText()));
            int result = ps.executeUpdate();

            if (result > 0) {
               JOptionPane.showMessageDialog(rootPane, "Registro eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se encontró el ID especificado");
            }
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, "Ingresa un ID válido");
        }
    }
   //dt prm   //negrooooooooooooo
    public static void main(String[] args) {
        new Welcome();//ins+
    }
}
//public modificar un coche  de metodo