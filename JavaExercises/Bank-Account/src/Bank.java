public class Bank
{
    public static void main(String[] args)
    {
        BankAccount account1 = new BankAccount(100);
        BankAccount account2 = new BankAccount(400);

        account1.deposit(100);
        account1.withdraw(2000);
        account1.withdraw(50);

        account2.deposit(5000);
        account2.withdraw(10000);
        account2.withdraw(2000);

        System.out.println("Account1 balance: $" + account1.getBalance());
        System.out.println("Account2 balance: $" + account2.getBalance());
    }
}