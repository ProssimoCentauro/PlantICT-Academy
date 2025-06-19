public class MyGeneric<T, V extends Number> {
    private T obj;
    private V obj2;

    MyGeneric(T obj, V obj2) {
        this.obj = obj;
        this.obj2 = obj2;
    }
    public void setT(T obj) {
        this.obj = obj;
    }

    public T getT() {
        return obj;
    }

    public void setV(V obj2) {
        this.obj2 = obj2;
    }

    public V getV() {
        return obj2;
    }

    public int square() {
        return (obj2.intValue() * obj2.intValue());
    }
}
