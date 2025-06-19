public class BClass extends AClass {
    String Bst;

    BClass(String a, String b) {
        super(a);
        this.Bst = b;
        System.out.println("Created BClass with string: " + this.Bst);
    }
}
