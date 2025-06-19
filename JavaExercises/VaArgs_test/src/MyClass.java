public class MyClass {
    public void VaArgs(int ... args) {
        System.out.println("number of args: " + args.length);
        for (int num : args){
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public void VaArgs(boolean ... args) {
        System.out.println("number of args: " + args.length);
        for (boolean bool : args){
            System.out.print(bool + " ");
        }
        System.out.println();
    }
}