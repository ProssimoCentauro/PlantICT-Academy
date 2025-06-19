public class MyClass {
    static int x;
    static int y;

    static {
        System.out.println("static block");
        x = 10;
        y = x * 10;
    }
}
