public class AClass {
    String st;
    boolean bl;

    AClass() {
        this.st = "Java Language!";
        System.out.println("Created SuperClass");
        System.out.println("SuperClass string: " + this.st);
    };

    AClass( String st, boolean bl) {
        this.st = st;
        this.bl = bl;
    }

    public void ViewAttr( ) {
        System.out.println("SuperClass Attributes: " + st + " " + bl);
    }
}