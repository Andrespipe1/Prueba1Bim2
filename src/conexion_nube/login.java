package conexion_nube;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame{
    private JButton loginButton;
    private JPanel panel1;
    private JTextField user;
    private JTextField pass;

    public login() {
        setContentPane(panel1);
        loginButton.addActionListener(new ActionListener() {
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
    public void verificarDatos() throws SQLException {
        String usering= user.getText();
        String passing= pass.getText();
        String url="jdbc:mysql://uj0pr9igtrayt0qu:YYEBmH4R9HjmAoJKiKcp@br912c5ufqtvi0nw6l8b-mysql.services.clever-cloud.com:3306/br912c5ufqtvi0nw6l8b";
        String user="uj0pr9igtrayt0qu";
        String password="YYEBmH4R9HjmAoJKiKcp";
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM acceso WHERE usuario = ? AND password = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, usering);
        pstmt.setString(2, passing);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            JOptionPane.showMessageDialog(null,"Bienvenid@ "+usering);
            inicio vregis= new inicio();
            vregis.iniciar();
            dispose();

        } else {
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos.");
        }
        rs.close();
        pstmt.close();
        connection.close();
    }
    public void iniciar(){
        setVisible(true);
        setSize(600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
