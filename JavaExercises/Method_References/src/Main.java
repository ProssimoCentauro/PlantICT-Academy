public class Main {

    static boolean test(IMyInterface mi, String s1, String s2) {
        return (mi.check(s1, s2));
    }

    //METHOD REFERENCES ARE A COMPACT FORM OF INSTANTIATING A FUNCTIONAL INTERFACE
    //IMyInterface mi = MyClass::contains

    public static void main(String[] args) {
        boolean b = test(MyClass::contains, "Java Language", "Language");
        System.out.println(b);

        b = test(MyClass::are_equals, "Java", "Python");
        System.out.println(b);
    }
}
