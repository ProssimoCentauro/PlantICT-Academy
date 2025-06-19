public class AClass {
    private String Ast;

    AClass(String a) {
        this.Ast = a;
        System.out.println("Created Aclass with string: " + this.Ast);
    }

    String getAst() {
        return this.Ast;
    }
}
