package javabasic;

interface ITestInterface {
    void display();
}

class TestInterface implements ITestInterface {
    public void display() {
        System.out.println("Hello interface");
    }
    public static void main(String[] args) {
        TestInterface t = new TestInterface();
        t.display();
    }
}
