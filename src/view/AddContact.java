package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class AddContact {

    private JFrame frame;
    private JLabel lblNewLabel;
    private JButton btnNewButton_1;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JLabel lblNewLabel1;
    private JButton btnNewButton;
    private JButton btnVoltar;
    private FileInputStream fis;


    /**
     * Launch the application.
     */
    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddContact window = new AddContact();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AddContact() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 532);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        btnNewButton_1 = new JButton("Adicionar foto");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                // filter the files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
                file.addChoosableFileFilter(filter);
                int result = file.showSaveDialog(null);
                // if the user click on save in Jfilechooser
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    lblNewLabel.setIcon(ResizeImage(path));
                    try {
                        fis = new FileInputStream(path);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
                // if the user click on save in Jfilechooser

                else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("No File Select");
                }
            }
        });

        JLabel lblNewLabel3 = new JLabel("Telefone: ");

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        JLabel lblNewLabel4 = new JLabel("Telefone2: ");

        textField_2 = new JTextField();
        textField_2.setColumns(10);

        JLabel lblNewLabel2 = new JLabel("Sobrenome: ");

        textField_3 = new JTextField();
        textField_3.setColumns(10);

        JLabel lblNewLabel5 = new JLabel("Endereço: ");

        textField_4 = new JTextField();
        textField_4.setColumns(10);

        JLabel lblNewLabel6 = new JLabel("Email: ");

        textField_5 = new JTextField();
        textField_5.setColumns(10);

        JLabel lblNewLabel7 = new JLabel("Notas: ");

        textField_6 = new JTextField();
        textField_6.setColumns(10);

        textField_7 = new JTextField();
        textField_7.setColumns(10);

        lblNewLabel1 = new JLabel("Nome: ");

        btnNewButton = new JButton("Confirmar");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
                    String sql = "Insert into contact (nome, sobrenome, foto, telefone1, telefone2, endereco, email, notas) values (?,?,?,?,?,?,?,?)";
                    PreparedStatement pst = c.prepareStatement(sql);
                    pst.setString(1, textField_7.getText());
                    pst.setString(2, textField_3.getText());
                    pst.setBlob(3, fis);
                    pst.setString(4, textField_1.getText());
                    pst.setString(5, textField_2.getText());
                    pst.setString(6, textField_4.getText());
                    pst.setString(7, textField_5.getText());
                    pst.setString(8, textField_6.getText());
                    
                    pst.execute();
                    frame.setVisible(false);
                    MyContacts contacts = new MyContacts(true);
                    contacts.main(null);
                } catch (SQLException | ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        btnVoltar = new JButton("Voltar");
        btnVoltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MyContacts contacts = new MyContacts(true);
                contacts.main(null);
                frame.setVisible(false);
            }
        });

        btnNewButton.setIcon(
                new ImageIcon(((new ImageIcon(AddContact.class.getResource("/assets/confirm.png"))).getImage())
                        .getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
        btnVoltar.setIcon(
                new ImageIcon(((new ImageIcon(AddContact.class.getResource("/assets/back.png"))).getImage())
                        .getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));

        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(150)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(btnNewButton_1, Alignment.LEADING,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                        .addComponent(lblNewLabel, Alignment.LEADING,
                                                                GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(111)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(lblNewLabel1, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblNewLabel2, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblNewLabel3, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblNewLabel4, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblNewLabel5, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblNewLabel6, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblNewLabel7, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(60)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 197,
                                                                Short.MAX_VALUE)
                                                        .addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 197,
                                                                Short.MAX_VALUE)
                                                        .addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 197,
                                                                Short.MAX_VALUE)
                                                        .addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 197,
                                                                Short.MAX_VALUE)
                                                        .addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 197,
                                                                Short.MAX_VALUE)
                                                        .addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 197,
                                                                Short.MAX_VALUE)
                                                        .addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 197,
                                                                Short.MAX_VALUE)))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(37)
                                                .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 202,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 202,
                                                        GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(39, Short.MAX_VALUE)));
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(22)
                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnNewButton_1)
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(lblNewLabel1))
                                        .addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(textField_3, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(9)
                                                .addComponent(lblNewLabel2)))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(lblNewLabel3)))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(lblNewLabel4)))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel5))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(lblNewLabel6)))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(lblNewLabel7)))
                                .addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 64,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 64,
                                                GroupLayout.PREFERRED_SIZE))
                                .addContainerGap()));
        frame.getContentPane().setLayout(groupLayout);
    }

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
}
