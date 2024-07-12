import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
public class login extends JFrame{
    private JButton login;
    private JPanel panel1;
    private JTextField user;
    private JTextField pass;


    public login() {
        super("Ventana de Login");
        setContentPane(panel1);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verificarDatos();
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
    public void verificarDatos() throws SQLException {
        String usering= user.getText();
        String passing= pass.getText();
        String url="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user="root";
        String password="";
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM USUARIO WHERE username = ? AND password = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, usering);
        pstmt.setString(2, passing);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            JOptionPane.showMessageDialog(null,"La información ingresada es correcta.");
            registro vregis= new registro();
            vregis.iniciar();
            dispose();

        } else {
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos.");
        }
        rs.close();
        pstmt.close();
        connection.close();
    }
}
