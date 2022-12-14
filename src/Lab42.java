// Lab 42
// Name: Tom Arklie
// Student Number: 20149129

public class Lab42 {

    public static void main(String args[]) {

        Account A[] = new Account[2];

        A[0] = new Saving();
        A[1] = new Checking();

        A[0].deposit(3000.00);
        A[1].deposit(2000.00);
        System.out.println("Interest from savings: " + A[0].dailyInterest());
        System.out.println("Interest from checking: " + A[1].dailyInterest());
        A[0].show();
        A[1].show();

        System.out.println();

        A[0].withdraw(2200.00);
        A[1].withdraw(1500.00);
        System.out.println("Interest from savings: " + A[0].dailyInterest());
        System.out.println("Interest from checking: " + A[1].dailyInterest());
        A[0].show();
        A[1].show();
    }

}

abstract class Account {

    protected double amount;

    public Account() {

        amount = 0.0;
    }

    public void deposit(double m) {

        amount += m;
    }

    public void withdraw(double m) {

        if (amount >= m) {
            amount -= m;
        }

    }

    abstract public void show();
    abstract public double dailyInterest();

}

class Saving extends Account {

    public double dailyInterest() {

        double interest = 0.06 * amount;
        deposit(interest);
        return interest;
    }

    public void show() {
        System.out.println("Savings account balance: " + amount);
    }
}


class Checking extends Account {

    public double dailyInterest() {

        double interest = 0;
        if (amount >= 1000) {
            interest = 0.03 * amount;
        }

        deposit(interest);
        return interest;
    }

    public void show() {
        System.out.println("Checking account balance: " + amount);
    }
}
