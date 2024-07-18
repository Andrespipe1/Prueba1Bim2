import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class modificar extends JFrame{
    private JButton regresarButton;
    private JPanel panel1;
    private JTextField nom;
    private JTextField ape;
    private JTextField tele;
    private JTextField eda;
    private JTextField deen;
    private JTextField ced;
    private JLabel datos;
    private JButton buscar;
    private JButton modificar;
    private JTextField cedb;
    private JTextField nhis;

    public modificar() {
        super("Modificar");
        setContentPane(panel1);
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busqueda vbus = new busqueda();
                vbus.iniciar();
                dispose();
            }
        });
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    modificarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void buscarDatos() throws SQLException {
        int cedula = Integer.parseInt(cedb.getText());
        Connection connection = conexion();
        String sql = "SELECT * FROM PACIENTE WHERE cedula = ?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, cedula);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String cedul = rs.getString("cedula");
            String histori = rs.getString("n_historial_clinico");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String telefono = rs.getString("telefono");
            String edad = rs.getString("edad");
            String desc = rs.getString("descripcion_enfermedad");

            datos.setText("Cédula: " + cedul + " N° Historial Clínico: " + histori + "\nNombre: " + nombre + " " + apellido + " Telf: " + telefono + " Edad: " + edad + " Enfermedad: " + desc);
            nom.setText(nombre);
            ape.setText(apellido);
            tele.setText(telefono);
            eda.setText(edad);
            deen.setText(desc);
            setSize(1000, 500);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un registro con ese número de historial clínico");
        }
        rs.close();
        pstmt.close();
        connection.close();
    }
    public void modificarDatos() throws SQLException {
        String cedula = ced.getText();
        int histo = Integer.parseInt(nhis.getText());
        String nombre = nom.getText();
        String apellido = ape.getText();
        String telefono = tele.getText();
        int edad = Integer.parseInt(eda.getText());
        String descripcion = deen.getText();
        Connection connection = conexion();
        String sql = "UPDATE PACIENTE SET cedula=?,n_historial_clinico= ?,nombre = ?, apellido = ?, telefono = ?, edad = ?, descripcion_enfermedad = ? WHERE cedula= ?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,cedula);
        pstmt.setInt(2,histo);
        pstmt.setString(3,nombre);
        pstmt.setString(4,apellido);
        pstmt.setString(5,telefono);
        pstmt.setInt(6,edad);
        pstmt.setString(7,descripcion);
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Paciente modificado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un registro con ese número de historial clínico");
        }
        pstmt.close();
        connection.close();
    }

    public Connection conexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user="root";
        String password="";
        return DriverManager.getConnection(url,user,password);
    }
    public void iniciar(){
        setVisible(true);
        setSize(600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
