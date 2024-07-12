import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
public class registro extends JFrame{
    private JTextField ced;
    private JPanel panel1;
    private JButton registrar;
    private JTextField ape;
    private JTextField nhis;
    private JTextField nom;
    private JTextField eda;
    private JTextField telf;
    private JTextField denf;
    private JButton buscar;

    public registro() {
        super("Ventana Registro");
        setContentPane(panel1);
        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ingresarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busqueda vbus=new busqueda();
                vbus.iniciar();
                dispose();
            }
        });
    }
    public void iniciar(){
        setVisible(true);
        setSize(700,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public Connection conexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user="root";
        String password="";
        return DriverManager.getConnection(url,user,password);
    }
    public void ingresarDatos() throws SQLException {
        String cedula=ced.getText();
        int historial = Integer.parseInt(nhis.getText());
        String nombre = nom.getText();
        String apellido = ape.getText();
        String telefono = telf.getText();
        int edad= Integer.parseInt(eda.getText());
        String descrEnfe=denf.getText();
        Connection connection= conexion();
        String sql = "Insert into PACIENTE (cedula,n_historial_clinico,nombre,apellido,telefono,edad,descripcion_enfermedad) values (?,?,?,?,?,?,?);";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,cedula);
        pstmt.setInt(2,historial);
        pstmt.setString(3,nombre);
        pstmt.setString(4,apellido);
        pstmt.setString(5,telefono);
        pstmt.setInt(6,edad);
        pstmt.setString(7,descrEnfe);

        int rowAffect = pstmt.executeUpdate();
        if(rowAffect > 0){
            JOptionPane.showMessageDialog(null,"Registro insertado correctamente");
        }
        pstmt.close();
        connection.close();
    }
}
