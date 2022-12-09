import java.util.Arrays;

public class IntegerSet {
    private int[] a = new int[101];

    public IntegerSet() {}

    public void insert(int i) {
        a[i] = 1;
    }
    public void delete(int i) {
        a[i] = 0;
    }
    @Override
    public String toString() {
        String s = new String();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1)
                s = s.concat(i + " ");
        }
        return s;
    }
    public IntegerSet union(IntegerSet is) {
        IntegerSet union = new IntegerSet();
        for (int i = 0; i <= 100; i++) {
            if (this.contains(i) || !this.contains(i) && is.contains(i))
                union.insert(i);
        }

        return union;
    }
    public IntegerSet intersection(IntegerSet is) {
        IntegerSet intersect = new IntegerSet();
        for (int i = 0; i <= 100; i++) {
            if (this.contains(i) && is.contains(i))
                intersect.insert(i);
        }

        return intersect;
    }
    public IntegerSet difference(IntegerSet is) {
        IntegerSet diff = new IntegerSet();
        for (int i = 0; i <= 100; i++) {
            if (this.contains(i) && !is.contains(i))
                diff.insert(i);
        }

        return diff;
    }
    public boolean equals(IntegerSet is) {
        for (int i = 0; i <= 100; i++) {
            if ((this.contains(i) && !is.contains(i)) || (is.contains(i) && !this.contains(i)))
                return false;
        }
        return true;
    }

    // Utils
    public boolean contains(int i) {
        return a[i] == 1;
    }
}
