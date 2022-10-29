package models;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.ContactPage;

public class CustomButton extends Component {

    public Component main(final Contact contact, final JFrame frame) {
        JButton jButton = new JButton();
        jButton.setIcon(new ImageIcon(((contact.getFoto()).getImage())
                .getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

        jButton.setIconTextGap(60);

        jButton.setHorizontalAlignment(SwingConstants.LEFT);
        jButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        jButton.setText(contact.getNome());
        jButton.setBackground(Color.white);
        jButton.setForeground(Color.black);
        jButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        jButton.setAlignmentX(CENTER_ALIGNMENT);

        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ContactPage contacts = new ContactPage(contact);
                System.out.println(contact);
                contacts.initialize();
                frame.dispose();
            }
        });

        return jButton;
    }
}
