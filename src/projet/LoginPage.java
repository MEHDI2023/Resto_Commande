package projet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class LoginPage extends JFrame implements ActionListener {
    Conneccion conn = new Conneccion();
     Label titleLabel, userLabel, passwordLabel;
     TextField userField;
     JPasswordField passwordField;
     Button loginButton, cancelButton;

    public LoginPage() {
        this.setTitle("Restaurant Soltana");
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        

        titleLabel = new Label("Login Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));


        userLabel = new Label("Username:");
        userField = new TextField(20);

        passwordLabel = new Label("Password:");
        passwordField = new JPasswordField(20);

        loginButton = new Button("Login");
        loginButton.addActionListener(this);

        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);

        Panel panel0 = new Panel(new FlowLayout());
        panel0.setBackground(new Color(200,250,150));
        Panel panel = new Panel(new GridLayout(6,1));
        
        panel0.add(titleLabel);
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(cancelButton);
        
        getContentPane().add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == loginButton) {
            String query = "SELECT * FROM user WHERE name='" + userField.getText() + "' AND password='" + String.valueOf(passwordField.getPassword())+ "'";

            ResultSet rs;
            Statement statement;
            try {
                statement = conn.laConnection().createStatement();
                rs = statement.executeQuery(query);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful");
                    dispose();
                    Commande c = new Commande();
                    c.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password");
                }
            } catch (SQLException ex) {
            	JOptionPane.showMessageDialog(null,"Erreur !",null,JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            
            dispose();
            System.exit(0);
        }
    }





}