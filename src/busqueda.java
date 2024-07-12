import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class busqueda extends JFrame{
    private JTextField nhis;
    private JPanel panel1;
    private JButton buscar;
    private JLabel datos;
    private JButton regresarButton;

    public busqueda() {
        super("Ventana de busqueda");
        setContentPane(panel1);
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
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registro vregis= new registro();
                vregis.iniciar();
                dispose();
            }
        });
    }
    public void iniciar(){
        setVisible(true);
        setSize(600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public Connection conexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user="root";
        String password="";
        return DriverManager.getConnection(url,user,password);
    }

    public void buscarDatos() throws SQLException {
        int historial = Integer.parseInt(nhis.getText());
        Connection connection = conexion();
        String sql = "Select * from PACIENTE where n_historial_clinico=?;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, historial);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String cedula = rs.getString("cedula");
            String histori = rs.getString("n_historial_clinico");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String telefono = rs.getString("telefono");
            String edad = rs.getString("edad");
            String desc = rs.getString("descripcion_enfermedad");

            datos.setText("Cedula: "+cedula+" N° historial Clinico"+histori+"\n Nombre: "+nombre+" "+apellido+" Telf: "+telefono+" Edad: "+edad+" Enfermedad: "+desc);
            setSize(1000,500);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un registro con ese código");
        }
        rs.close();
        pstmt.close();
        connection.close();
    }
}

