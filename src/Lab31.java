public class Lab31 {
    public static void main(String[] args) {
        IntegerSet set = new IntegerSet();
        IntegerSet is = new IntegerSet();
        set.insert(4);
        set.insert(7);
        set.insert(2);
        set.insert(100);
        set.insert(57);
        set.insert(0);
        is.insert(1);
        is.insert(2);
        is.insert(4);
        is.insert(34);
        is.insert(78);
        System.out.println(set.toString() + "\n" + is.toString());
        set.delete(57);
        System.out.println(set.toString() + "\n" + is.toString());
        IntegerSet union1 = set.union(is);
        IntegerSet union2 = is.union(set);
        IntegerSet intersect = set.intersection(is);
        IntegerSet diff1 = set.difference(is);
        IntegerSet diff2 = is.difference(set);
        System.out.println("Unions:\n" + union1.toString() + "\n" + union2.toString());
        System.out.println("Intersection:\n" + intersect.toString());
        System.out.println("differences:\n" + diff1.toString() + "\n" + diff2.toString());
        System.out.println(union1.equals(union2) + " " + intersect.equals(diff1));
    }
}