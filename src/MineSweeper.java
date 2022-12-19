import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.*;

public class MineSweeper {
    public static Cell[][] field;
    public static int cellsPopped = 0;
    public static int nonMines = 0;

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

    public MSweep(String state) {
        super("Game Over!");
        buildGUI(state);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(200, 200);
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
        c.insets = new Insets(0, 0, 0, 0);
        c.fill = GridBagConstraints.BOTH;

        // Start loop for inserting cells
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                JButton currentButton = new JButton("  ");
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
        MineSweeper.nonMines = this.nonMines;
    }

    public void buildGUI(String gameState) {
        if (gameState.equalsIgnoreCase("win")) {
            Container ct = getContentPane();
            ct.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            // General Constraints
            c.anchor = GridBagConstraints.CENTER;
            c.insets = new Insets(5, 5, 5, 5);
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = 0;
            JLabel results = new JLabel("You lost :(");
            if (gameState.equalsIgnoreCase("win")) {
                results.setText("You won!");
            }
            ct.add(results, c);
        }
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
        if (in.isMine()) {
            MSweep lost = new MSweep("lost");
            return;
        }
        if (in.isPopped())
            return;
        // Otherwise scan around for mines and set display appropriately.
        in.pop();
        MineSweeper.cellsPopped++;
        in.getButton().setText(in.mineSweep() + "");
        // Check for game win
        if (MineSweeper.cellsPopped == MineSweeper.nonMines) {
            MSweep win = new MSweep("win");
            return;
        }
        if (in.mineSweep() == 0) {
            int x = in.getX();
            int y = in.getY();
            for (int relX = -1; relX < 2; relX++) {
                for (int relY = -1; relY < 2; relY++) {
                    try {
                        if (!MineSweeper.field[x + relX][y + relY].isPopped() && !MineSweeper.field[x + relX][y + relY].isMine())
                            // if the cell we're checking has 0 mines around it (As per the if statement above the for loops)
                            // isn't popped, and isn't a mine. it can be clicked.
                            // This allows for all 0s and 1 layer of non 0s to be shown.
                            MineSweeper.field[x + relX][y + relY].getButton().doClick();
                    } catch (ArrayIndexOutOfBoundsException ignored) {}
                }
            }
        }
    }
}
