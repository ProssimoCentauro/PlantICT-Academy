public class Bclass extends AClass {
    String st;

    Bclass() {
        st = "SubClass";
        super.st = "SuperClass Modified!";
        System.out.println("Created Subclass!");
        System.out.println("Modified SuperClass string with \"super\" keyword!");
    }
}
