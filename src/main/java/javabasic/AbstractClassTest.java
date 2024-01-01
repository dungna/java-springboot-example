package javabasic;

abstract class Shape {
    abstract void draw();
    void display() {
        System.out.println("This is a shape.");
    }
}

class Rangle extends Shape {
    @Override
    void draw() {
        System.out.println("Rangle extends from Shape class");
    }
}
class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Cirle extends from Shape class");
    }
}
public class AbstractClassTest {
    public static void main(String[] args) {
        Shape r = new Rangle();
        r.draw();
        r.display();

        Shape c = new Circle();
        c.draw();
        c.display();
    }
}
