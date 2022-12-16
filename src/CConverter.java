import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class CConverter extends JFrame {

    public CConverter() {
        super("Cobalt Converter");
        buildGUI();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        CConverter cc = new CConverter();
    }

    private void buildGUI() {
        // Making Pane
        Container ct = getContentPane();
        ct.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Setting Base Constraints
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);

        JLabel coL = new JLabel("Amount of Cobalt:");
        JTextField coIn = new JTextField(12);
        JLabel yrL = new JLabel("Number of Years:");
        JTextField yrIn = new JTextField(12);
        JButton comp = new JButton("Compute");
        JButton quit = new JButton("Quit");
        JLabel res = new JLabel("Amount Left:");

        // Adding Label Components
        c.gridx = 0; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST; c.ipady = 10;
        ct.add(coL, c);
        c.gridy = 1;
        ct.add(yrL, c);

        // Adding TextField Components
        c.gridx = 1; c.gridy = 0; c.anchor = GridBagConstraints.EAST;
        ct.add(coIn, c);
        c.gridy = 1;
        ct.add(yrIn, c);

        // Adding buttons
        c.gridx = 0; c.gridy = 2; c.anchor = GridBagConstraints.CENTER;
        ct.add(comp, c);
        c.gridx = 1;
        ct.add(quit, c);

        // Adding bottom result
        c.gridx = 0; c.gridy = 3; c.gridwidth = 2; c.anchor = GridBagConstraints.SOUTH;
        ct.add(res, c);


    }

}

class ComputeListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0); // Placeholder to test. This will compute later.
    }
}

class QuitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}