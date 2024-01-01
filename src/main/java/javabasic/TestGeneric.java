package javabasic;

public class TestGeneric<T> {
    private T myField;
    public T getMyField() {
        return myField;
    }
    public void setMyField(T value) {
        this.myField = value;
    }
    public static void main(String[] args) {
        TestGeneric<String> stringObj = new TestGeneric<>();
        stringObj.setMyField("Hello, my generic");
        System.out.println(stringObj.getMyField());

        TestGeneric<Integer> integerObject = new TestGeneric<>();
        integerObject.setMyField(42);
        System.out.println(integerObject.getMyField());
    }
}
