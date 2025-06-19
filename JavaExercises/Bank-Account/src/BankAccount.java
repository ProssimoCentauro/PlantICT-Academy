public class BankAccount {
    private double balance;

    BankAccount( ) {
        this.balance = 0;
        System.out.println("New empty bank account created!");
    }

    BankAccount(double balance) {
        System.out.println("New bank account created! FIRST DEPOSIT: $" + balance);
        this.balance = balance;
    }

    public double getBalance( ) {
        return this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
        System.out.println("Deposited $" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawn $" + amount);
        }
        else {
            System.out.println("Insufficient balance!");
        }
    }
}
