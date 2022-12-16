import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

public class Javapad extends JFrame {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem n, open, save, exit;

    public Javapad() {
        super("Javapad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(640, 480));
        buildGUI();
        pack();
        setVisible(true);
    }

    class MenuListener implements ActionListener {
        private JFileChooser fc;
        private JTextArea area;

        public MenuListener(JTextArea area) {
            fc = new JFileChooser();
            this.area = area;
        }

        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == n) {
                area.setText("");
            } else if(e.getSource() == open) {
                int returnVal = fc.showOpenDialog(Javapad.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileReader in = new FileReader(fc.getSelectedFile());
                        char[] data = new char[(int) fc.getSelectedFile().length()];
                        in.read(data);
                        in.close();
                        area.setText(new String(data));
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            } else if(e.getSource() == save) {
                int returnVal = fc.showSaveDialog(Javapad.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    // try to save file
                }
            } else if(e.getSource() == exit) {
                System.exit(0);
            }
        }
    }

    private void buildGUI() {
        Container container = this.getContentPane();

        menuBar = new JMenuBar();
        container.add(menuBar, BorderLayout.NORTH);

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        n = new JMenuItem("New");
        fileMenu.add(n);
        fileMenu.addSeparator();

        open = new JMenuItem("Open");
        fileMenu.add(open);
        fileMenu.addSeparator();

        save = new JMenuItem("Save");
        fileMenu.add(save);
        fileMenu.addSeparator();

        exit = new JMenuItem("Exit");
        fileMenu.add(exit);

        JTextArea edit = new JTextArea(30,20);
        edit.setFont(new Font("Menlo", Font.PLAIN, 14));

        JScrollPane sp = new JScrollPane(edit);
        sp.setPreferredSize(new Dimension(450, 100));
        container.add(sp, BorderLayout.CENTER);

        MenuListener ml = new MenuListener(edit);
        n.addActionListener(ml);
        open.addActionListener(ml);
        save.addActionListener(ml);
        exit.addActionListener(ml);
    }

    public static void main(String[] args) {
        Javapad jp = new Javapad();
    }
}