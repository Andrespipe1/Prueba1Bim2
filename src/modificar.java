import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class modificar extends JFrame{
    private JButton regresarButton;
    private JPanel panel1;

    public modificar() {
        super("Modificar");
        setContentPane(panel1);
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
//    public void modificarDatos() throws SQLException {
//        int cedula = Integer.parseInt(ced.getText());
//        String nombre = nom.getText();
//        String apellido = ape.getText();
//        String telefono = tele.getText();
//        String edad = eda.getText();
//        String descripcion = deen.getText();
//        Connection connection = conexion();
//        String sql = "UPDATE PACIENTE SET nombre = ?, apellido = ?, telefono = ?, edad = ?, descripcion_enfermedad = ? WHERE n_historial_clinico = ?;";
//        PreparedStatement pstmt = connection.prepareStatement(sql);
//        pstmt.setString(1, nombre);
//        pstmt.setString(2, apellido);
//        pstmt.setString(3, telefono);
//        pstmt.setString(4, edad);
//        pstmt.setString(5, descripcion);
//        pstmt.setInt(6, cedula);
//        int rowsAffected = pstmt.executeUpdate();
//        if (rowsAffected > 0) {
//            JOptionPane.showMessageDialog(null, "Paciente modificado exitosamente");
//        } else {
//            JOptionPane.showMessageDialog(null, "No se encontró un registro con ese número de historial clínico");
//        }
//        pstmt.close();
//        connection.close();
//    }

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
