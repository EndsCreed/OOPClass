import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.*;

public class MineSweeper {
    public static Cell[][] field;

    public static void main(String[] args) {
        MSweep ms = new MSweep();
    }
}

class MSweep extends JFrame {
    private int size;
    private int diffMult;
    private int mines;
    private int nonMines;
    private Cell[][] field;

    public MSweep() {
        super("MineSweeper");
        this.size = 10;
        initField();
        this.diffMult = 1;
        buildGUI();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public MSweep(int size) {
        super("Minesweeper");
        this.size = size;
        initField();
        this.diffMult = 1;
        buildGUI();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public MSweep(int size, int diffMult) {
        super("Minesweeper");
        this.size = size;
        initField();
        this.diffMult = diffMult;
        buildGUI();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void initField() {
        this.field = new Cell[size][size];
    }

    public void buildGUI() {
        // Make Pane
        Container ct = getContentPane();
        ct.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Set base constraints
        c.ipady = 0;
        c.ipadx = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;

        // Start loop for inserting cells
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                JButton currentButton = new JButton(" ");
                if (calcMine()) {
                    field[x][y] = new Cell(x, y, currentButton, true);
                    System.out.println("Mine at: " + x + ", " + y);
                } else {
                    field[x][y] = new Cell(x, y, currentButton, false);
                    this.nonMines++;
                }
                currentButton = getCell(x, y).getButton();
                currentButton.addActionListener(new CellListener(getCell(x, y)));
                c.gridx = x;
                c.gridy = y;
                ct.add(currentButton, c);
            }
        }
        MineSweeper.field = this.field;
    }

    // Getters
    public Cell getCell(int x, int y) {
        return this.field[x][y];
    }

    // Logic
    private boolean calcMine() {
        Random ran = new Random();
        double variance = (double) ran.nextInt(5) / 100;
        double percentMines = ((0.05 * diffMult) + variance);
        double test = ran.nextDouble();
        if (test < percentMines) {
            this.mines++;
            return true;
        }
        // System.out.println("Double genned: " + test + "\nChecked against: " + percentMines);
        return false;
    }
}

class Cell {
    private int x;
    private int y;
    private JButton button;
    private boolean mine = false;
    private boolean popped = false;

    public Cell(int x, int y, JButton in) {
        this.x = x;
        this.y = y;
        this.button = in;
        this.mine = false;
    }
    public Cell(int x, int y, JButton in, boolean mine) {
        this.x = x;
        this.y = y;
        this.button = in;
        this.mine = mine;
    }

    public int mineSweep() throws ArrayIndexOutOfBoundsException {
        int mines = 0;
        for (int relX = -1; relX < 2; relX++) {
            for (int relY = -1; relY < 2; relY++) {
                try {
                    if (MineSweeper.field[x + relX][y + relY].isMine())
                        mines++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                    // System.out.println("Tried to access [ " + x+relX + ", " + y+relY);
                }
            }
        }
        return mines;
    }
    public boolean isSafe() { // Safe being no near mines.
        for (int relX = -1; relX < 2; relX++) {
            for (int relY = -1; relY < 2; relY++) {
                try {
                    if (MineSweeper.field[x + relX][y + relY].isMine())
                        return false;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                    //System.out.println("Tried to access [ " + x+relX + ", " + y+relY);
                }
            }
        }
        return true;
    }

    public void pop() { this.popped = true; }

    // Getters
    public boolean isMine() { return mine; }
    public JButton getButton() { return this.button; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isPopped() { return popped; }
}

class CellListener implements ActionListener {
    private Cell in;

    public CellListener(Cell in) {
        this.in = in;
    }
    @Override
    public void actionPerformed(ActionEvent e) throws ArrayIndexOutOfBoundsException {
        if (in.isMine())
            System.exit(0);
        if (in.isPopped())
            return;
        // Otherwise scan around for mines and set display appropriately.
        in.pop();
        in.getButton().setText(in.mineSweep() + "");
        if (in.mineSweep() == 0) {
            int x = in.getX();
            int y = in.getY();
            for (int relX = -1; relX < 2; relX++) {
                for (int relY = -1; relY < 2; relY++) {
                    try {
                        if (MineSweeper.field[x + relX][y + relY].isSafe() &&
                            !MineSweeper.field[x + relX][y + relY].isPopped())
                            MineSweeper.field[x + relX][y + relY].getButton().doClick();
                    } catch (ArrayIndexOutOfBoundsException ignored) {}
                }
            }
        }
    }
}
