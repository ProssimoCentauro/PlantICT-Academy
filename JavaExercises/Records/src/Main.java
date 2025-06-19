record Person(String Name, int BirthYear) {}

record Person2(String Name, int BirthYear) {

    //CANONICAL CONSTRUCTOR REDEFINITION
    public Person2(String Name, int BirthYear) {
        this.Name = Name.toUpperCase();
        this.BirthYear = BirthYear;
    }

    public Person2(int BirthYear) {
        this("Nobody", BirthYear);
    }
}

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Niko Bellic", 1978);
        Person p2 = new Person("Lebon Almic", 1984);
        Person p3 = new Person("Lebon Almic", 1984);
        Person2 p4 = new Person2("Super Mario", 1985);
        Person2 p5 = new Person2(2000);

        System.out.println("p1 info: " + p1.Name() + " " + p1.BirthYear());
        System.out.println("println of p2: " + p2);
        System.out.println("p2 equals p3: " + p2.equals(p3));
        System.out.println(p2.hashCode() + "<-p2  HASH CODES  p3-> " + p3.hashCode());
        System.out.println("p1 hash code: " + p1.hashCode());
        System.out.println("p4 info: " + p4.Name() + " " + p4.BirthYear());
        System.out.println("p5 info: " + p5.Name() + " " + p5.BirthYear());
    }
}
