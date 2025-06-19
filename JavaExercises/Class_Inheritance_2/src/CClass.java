public class CClass extends BClass {
    String Cst;

    CClass(String a, String b, String c) {
        super(a, b);
        this.Cst = c;
        System.out.println("Created CClass with string: " + Cst);
    }
}
