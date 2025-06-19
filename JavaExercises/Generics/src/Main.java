public class Main {
    public static void main(String[] args) {
        MyGeneric<String, Integer> genClass = new MyGeneric<>("test", 42);
        genClass.setT("Java Language!");
        System.out.println("T value: " + genClass.getT());
        int i = genClass.getV();
        System.out.println("V value: " + i);
        i = genClass.square();
        System.out.println("V square: " + i);
    }
}
