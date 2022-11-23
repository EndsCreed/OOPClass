public class MyInteger {

    private int number;


    public MyInteger() {
        this.number = 0;
    }

    public MyInteger(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }

    public boolean isEven() {
        return this.number % 2 == 0;
    }

    public boolean isOdd() {
        return !isEven();
    }

    public boolean isPrime() {
        if (this.number < 2)
            return false;
        if (this.number == 2 || this.number == 3)
            return true;

        for (int i = 2; i <= Math.sqrt(this.number); i++) {
            if (this.number % i == 0)
                return false;
        }
        return true;
    }

    public boolean isPerfect() {
        if (this.number < 6) // Less than the first perfect number
            return false;

        int facs = 0;
        int loop = 1;
        while (loop < this.number) {
            if (this.number % loop == 0) {
                System.out.println(loop + " is a factor of " + this.number);
                facs = facs + loop;
            }
            loop++;
        }
        System.out.println("Factors added to: " + facs);
        if (facs == this.number)
            return true;
        return false;
    }

    public boolean isPerfectSquare() {
        int root = (int) Math.sqrt(this.number);
        if (root*root == this.number)
            return true;
        return false;
    }

    public void primeFactors1() {
        /*
        2 and 3 cannot have prime factors so just ignore those. this.number must be greater than 3 to execute. Loop must start checking for factors and 2 for this.number = 4 to work.

        Prime factors are two prime numbers that can multiplied together to get the desired number

        eg:
        12. 12 / 2 = 6. 6 is not prime. so, we attempt to divide by a prime to get it. 6 / 2 = 3.
        Prime numbers are 2 * 2 * 3.

        105. 105 / 3 = 35. 35 / 5 = 7.
        Prime numbers are 3 * 5 * 7


         */

        if (this.number < 4) {
            System.out.println(this.number + " has no prime factors other than 1 and itself.");
            return;
        }

        StringBuilder facs = new StringBuilder(); //facs = facs + loop + " * ";
        int loop = 2;
        while (loop < this.number/2) { // Keep looping until we get to 1/2 of the number we are finding prime factors for
            System.out.println("----loop: " + loop + " ----");
            if (isPrime(loop) && this.number % loop == 0) { // Find prime numbers and check if they are a factor
                facs.append(loop).append(" * ");
                int innerloop = loop - 1;
                int currentNum = this.number;
                while (!isPrime((currentNum / innerloop))) {// As long as the result from the last quotient and the last prime isn't prime,
                    innerloop++;
                    while (!isPrime(innerloop))
                        innerloop++;
                    System.out.println("----innerLoop: " + innerloop + " ----");
                    if (innerloop >= currentNum) {
                        System.out.println("Something went wrong!");
                        System.out.println("this.number: " + this.number + "\nloop: " + loop + "\ncurrentNum: " + currentNum + "\ninnerLoop: " + innerloop);
                        return;
                    }
                    currentNum = currentNum / innerloop;
                    facs.append(innerloop).append(" * ");
                }
                facs.append(currentNum/innerloop);
            }
            loop++;
        }
        System.out.println(facs);
    }

    public void primeFactors() {
        int num = this.number;
        StringBuilder facs = new StringBuilder();
        if (num < 4) {
            System.out.println(this.number + " has no prime factors other than 1 and itself.");
            return;
        }
        int i = 2;
        while (i < num/2) { // look for the prime factors of the current number.
            if (isPrime(i) && num % i == 0) { // Check if the current loop is prime and divides evenly into the current number
                facs.append(i).append(" * "); // Append the current loop into the string as it evenly divides and is prime.
                int q = num / i; // Since we have found a prime, we need to break the quotient of the above step into primes.
                if (!isPrime(q)) { // If the quotient is not a prime...
                    num = q; // Set the number we are breaking apart to the quotient.
                    i = 2; // Reset the number we are trying to divide with back to 2. This is because we are now trying to break a new number apart.
                } else { // If the quotient was actually found to be prime then we are done!
                    facs.append(q); // Add the quotient to the end of the string of prime factors.
                    break; // Break out of the while loop since we have found the last prime factor!
                }
            } else // In the event to current loop isn't a prime and isn't evenly divisible into the number we are breaking apart....
                i++; // we will simply increase the number we are dividing by 1, so we can keep checking for divisors to break the number apart with.
        }
        System.out.println(facs); // Print out the final result.
    }

    //Util Method NOT PART OF MAIN LAB
    public boolean isPrime(int num) {
        if (num < 2) {
            System.out.println("isPrime: " + num + " is not prime");
            return false;
        }
        if (num == 2 || num == 3) {
            System.out.println("isPrime: " + num + " is prime");
            return true;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                System.out.println("isPrime: " + num + " is not prime");
                return false;
            }
        }
        System.out.println("isPrime: " + num + " is prime");
        return true;
    }
}
