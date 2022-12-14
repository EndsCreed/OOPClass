import java.util.Arrays;

public class Lab32 {
    public static void main(String[] args) {
        CA ca = new CA(15, 30);
        System.out.println(ca);
    }
}

class CA {
    private boolean[] cell;
    private int size;
    private int rule;
    private boolean[] ttable;

    public CA(int size, int rule) {
        this.size = size;
        this.rule = rule;
        this.cell = new boolean[size];
        initBool();
        this.ttable = intToBinary(rule);
    }

    private void initBool() {
        for (boolean b : this.cell)
            b = false;
        this.cell[this.size/2] = true;
    }
    private void initBool(boolean[] ba) {
        for (boolean b : ba)
            b = false;
    }

    public boolean[] intToBinary(int num) {
        String s = Integer.toBinaryString(30);
        System.out.println("String binary: " + s);
        boolean[] b = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            b[i] = Character.getNumericValue(s.toCharArray()[i]) == 1;
        }
        return b;
    }

    public String toString() {
        String s = "\nSize: " + this.size + "\nRule: " + this.rule + "\nCell: " + Arrays.toString(this.cell) + "\nttable: " + Arrays.toString(this.ttable);
        return s;
    }
}
