import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JButton regularVndButton;
    private JButton specialVndButton;

    public Main() {
        setTitle("Vending Machine Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 150));
        setLayout(new FlowLayout());
        JLabel prompt = new JLabel("Please select a Vending Machine to Test:");
        add(prompt);
        regularVndButton = new JButton("Regular Vending Machine");
        specialVndButton = new JButton("Special Vending Machine");

        regularVndButton.addActionListener(this);
        specialVndButton.addActionListener(this);

        add(regularVndButton);
        add(specialVndButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == regularVndButton) {
            Gui mainGui = new Gui();
            mainGui.Display();
        } else if (e.getSource() == specialVndButton) {
            SpcGui mainGui = new SpcGui();
            mainGui.Display();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}
