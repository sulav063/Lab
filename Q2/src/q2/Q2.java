package q2;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Q2 extends JFrame implements ActionListener {

    JTextField id, user;
    JPasswordField pass, repass;
    JRadioButton male, female;
    JCheckBox java, php;
    JComboBox<String> country;
    JButton submit, reset;

    Q2() {

        setLayout(null);

        id = new JTextField();
        user = new JTextField();
        pass = new JPasswordField();
        repass = new JPasswordField();

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(male); bg.add(female);

        java = new JCheckBox("Java");
        php = new JCheckBox("PHP");

        country = new JComboBox<>(new String[]{"Nepal", "India", "China"});

        submit = new JButton("Submit");
        reset = new JButton("Reset");

        JLabel l1 = new JLabel("ID");
        JLabel l2 = new JLabel("User");
        JLabel l3 = new JLabel("Pass");
        JLabel l4 = new JLabel("Repass");

        l1.setBounds(30,30,80,30); id.setBounds(120,30,120,30);
        l2.setBounds(30,70,80,30); user.setBounds(120,70,120,30);
        l3.setBounds(30,110,80,30); pass.setBounds(120,110,120,30);
        l4.setBounds(30,150,80,30); repass.setBounds(120,150,120,30);

        male.setBounds(120,190,70,30);
        female.setBounds(200,190,80,30);

        java.setBounds(120,230,60,30);
        php.setBounds(190,230,60,30);

        country.setBounds(120,270,120,30);

        submit.setBounds(50,320,100,30);
        reset.setBounds(170,320,100,30);

        add(l1); add(id);
        add(l2); add(user);
        add(l3); add(pass);
        add(l4); add(repass);
        add(male); add(female);
        add(java); add(php);
        add(country);
        add(submit); add(reset);

        submit.addActionListener(this);
        reset.addActionListener(this);

        setSize(350,420);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {

            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/q2", "root", "Mp40awm@#");

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO student VALUES(?,?,?,?,?,?)");

                ps.setInt(1, Integer.parseInt(id.getText()));
                ps.setString(2, user.getText());
                ps.setString(3, new String(pass.getPassword()));
                ps.setString(4, male.isSelected() ? "Male" : "Female");
                ps.setString(5,
                        (java.isSelected() ? "Java " : "") +
                        (php.isSelected() ? "PHP" : ""));
                ps.setString(6, country.getSelectedItem().toString());

                ps.executeUpdate();

                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM student");

                while (rs.next()) {
                    System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
                }

                JOptionPane.showMessageDialog(this, "Inserted");

                con.close();

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        if (e.getSource() == reset) {
            id.setText("");
            user.setText("");
            pass.setText("");
            repass.setText("");
        }
    }

    public static void main(String[] args) {
        new Q2();
    }
}