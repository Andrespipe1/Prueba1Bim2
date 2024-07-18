package conexion_nube;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class inicio extends JFrame {
    private JButton button1;
    private JPanel panel1;
    private JTextField nom;
    private JTextField ape;
    private JTextField telf;
    private JButton eliminar;
    private JButton buscarButton;
    private JLabel datos;

    public inicio(){
        setContentPane(panel1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ingresarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void iniciar(){
        setVisible(true);
        setSize(600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public Connection conexion() throws SQLException {
        String url="jdbc:mysql://uj0pr9igtrayt0qu:YYEBmH4R9HjmAoJKiKcp@br912c5ufqtvi0nw6l8b-mysql.services.clever-cloud.com:3306/br912c5ufqtvi0nw6l8b";
        String user="uj0pr9igtrayt0qu";
        String password="YYEBmH4R9HjmAoJKiKcp";
        return DriverManager.getConnection(url,user,password);
    }
    public void ingresarDatos() throws SQLException {
        String nombre = nom.getText();
        String apellido = ape.getText();
        String telefono = telf.getText();
        Connection connection= conexion();
        String sql = "Insert into registro (nombre,apellido,telefono) values (?,?,?);";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,nombre);
        pstmt.setString(2,apellido);
        pstmt.setString(3,telefono);
        int rowAffect = pstmt.executeUpdate();
        if(rowAffect > 0){
            JOptionPane.showMessageDialog(null,"Registro insertado correctamente");
        }
        pstmt.close();
        connection.close();
    }
    public void buscarDatos() throws SQLException {
        String nombre = nom.getText();
        Connection connection = conexion();
        String sql = "Select * from registro where nombre=?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, nombre);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {

            String nombree = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String telefono = rs.getString("telefono");


            datos.setText("Nombre: "+nombree+" "+apellido+" Telf: "+telefono);
            setSize(1000,500);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un registro con ese código");
        }
        rs.close();
        pstmt.close();
        connection.close();
    }
    public void eliminarDatos() throws SQLException {
        String nombre = nom.getText();
        Connection connection = conexion();
        String sql = "DELETE FROM registro WHERE nombre = ?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,nombre);
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un registro con ese nombre");
        }
        pstmt.close();
        connection.close();
    }
}
