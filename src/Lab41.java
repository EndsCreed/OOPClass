// Lab 41
// Name: Tom Arklie
// Student Number: 20149129

public class Lab41 {

    public static void main(String args[]) {

        Employee e = new Employee(1, 100000, "John");
        System.out.println("\nEmployee: " + e);

        Policy p = new Policy(2, 50000, "Linda");
        System.out.println("\nPolicy: " + p);

        Salesperson s = new Salesperson(3, 75000, "Dennis", p);
        System.out.println("\nSalesperson: " + s);
    }

}

class Employee {

    protected int id;
    protected double salary;
    protected String name;

    public Employee(int id, double salary, String name) {

        this.id = id;
        this.salary = salary;
        this.name = name;
    }

    @Override
    public String toString() {
        String s = "\nName: " + this.name + "\nID: " + this.id + "\nSalary: " + this.salary;
        return s;
    }
}

class Policy {

    protected int pid;
    protected double amount;
    protected String beneficiary;

    public Policy(int pid, double amount, String beneficiary) {

        this.pid = pid;
        this.amount = amount;
        this.beneficiary = beneficiary;
    }

    @Override
    public String toString() {
        String s = "\nPID: " + this.pid + "\nAmount: " + this.amount + "\nTo: " + this.beneficiary;
        return s;
    }

}

class Salesperson extends Employee {

    protected Policy pol;

    public Salesperson(int id, double salary, String name, Policy pol) {

        super(id, salary, name);
        this.pol = pol;
    }

    @Override
    public String toString() {
        String sp = super.toString() + "\n-Policy-" + pol.toString();
        return sp;
    }

}