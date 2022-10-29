package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import models.Contact;
import models.CustomButton;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyContacts {

	private JFrame frame;
	private JButton btnNewButton;
	private boolean logged;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyContacts window = new MyContacts();
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
	public MyContacts() {
		logged = false;
		initialize();
	}

	public MyContacts(boolean logged) {
		this.logged = logged;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 796, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new JLabel(new ImageIcon(MyContacts.class.getResource("/assets/background.png"))));

		JPanel grid = new JPanel();
		JScrollPane scrollPane = new JScrollPane(grid);
		btnNewButton = new JButton("Sair");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setIcon(
				new ImageIcon(((new ImageIcon(MyContacts.class.getResource("/assets/exit.png"))).getImage())
						.getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH)));

		grid.setLayout(new GridLayout(0, 1, 0, 0));
		grid.setBackground(Color.white);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement stmt = c.createStatement();
			String sql = "Select * from contact";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setNome(rs.getString("nome"));
				contact.setSobrenome(rs.getString("sobrenome"));
				contact.setFoto(Toolkit.getDefaultToolkit().createImage(rs.getBytes("foto")));
				contact.setTelefone1(rs.getString("telefone1"));
				contact.setTelefone2(rs.getString("telefone2"));
				contact.setEndereço(rs.getString("endereco"));
				contact.setEmail(rs.getString("email"));
				contact.setNotas(rs.getString("notas"));

				grid.add(new CustomButton().main(contact, frame));
			}
			stmt.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		JButton btnAdicionarNovoContato = new JButton("Adicionar novo");
		btnAdicionarNovoContato.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddContact.main();
				frame.dispose();

			}
		});

		btnAdicionarNovoContato.setIcon(
				new ImageIcon(((new ImageIcon(MyContacts.class.getResource("/assets/196436-200.png"))).getImage())
						.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		JLabel lblNewLabel = new JLabel(logged ? "Nome" : "Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
								.addGap(23)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
										.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
										.addComponent(btnAdicionarNovoContato, GroupLayout.DEFAULT_SIZE, 264,
												Short.MAX_VALUE))
								.addGap(39)));
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addGap(97)
												.addComponent(lblNewLabel)
												.addGap(18)
												.addComponent(btnAdicionarNovoContato)
												.addPreferredGap(ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
												.addComponent(btnNewButton))
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
								.addContainerGap()));
		frame.getContentPane().setLayout(groupLayout);
	}
}
