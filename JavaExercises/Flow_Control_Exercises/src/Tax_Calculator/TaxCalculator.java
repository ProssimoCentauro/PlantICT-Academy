public class TaxCalculator {

    public static double CalculateTax(double amount) {
        if (amount < 0 || Double.isNaN(amount) || Double.isInfinite(amount))
            return -1;

        double res;
        if (amount == 0)
            res = 0.0;
        else if (amount <= 10000)
            res = (amount * 10) / 100;
        else if (amount <= 20000)
            res = 1000 + (((amount - 10000) * 7) / 100);
        else if (amount <= 30000)
            res = 1700 + (((amount - 20000) * 5) / 100);
        else
            res = 2200 + (((amount - 30000) * 3) / 100);
        return (res);
    }

    public static void main(String[] args) {
        System.out.println(CalculateTax(8000));     // 800.0
        System.out.println(CalculateTax(15000));    // 1000 + 350 = 1350.0
        System.out.println(CalculateTax(25000));    // 1000 + 700 + 250 = 1950.0
        System.out.println(CalculateTax(40000));    // 1000 + 700 + 500 + 300 = 2500.0
        System.out.println(CalculateTax(-500));     // -1
    }
}