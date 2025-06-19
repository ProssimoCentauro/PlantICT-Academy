public class Main {
    static int myMethod(int a[], IMyInterface2 mi) {
        return mi.process(a);
    }

    public static void main(String[] args) {
        IMyInterface mi = (n) -> n > 0;
        System.out.println("mi.check: " + mi.check(11));

        mi = (n) -> n % 2 == 0;
        System.out.println("mi.check: " + mi.check(11));

        int arr[] = {10, 5, 20};
        int res;
        res = myMethod(arr, (n) -> {
            int i = 0;
            for (int value : n)
                i += value;
            return (i);
        });
        System.out.println("mi.process: " + res);
    }
}
