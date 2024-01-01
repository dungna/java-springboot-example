import javabasic.Employee;
import javabasic.Puppy;
import javabasic.Test;

public class HelloWorld {
    public static void main(String []args) {
        System.out.println("Hello, world!");

        Test test = new Test();
        test.pupAge();

        // Salary
        Employee emp = new Employee("Nguyen Anh Dung");
        emp.setSalary(1000);
        emp.printEmp();

        // for loop
        int [] numbers = {10, 20, 30, 40, 50};
        for (int x: numbers) {
            System.out.println(x);
        }

        String [] names = {"James", "Andy", "Harry", "Kevin", "Phoebe"};
        for (int fi=1;  fi < 5; fi++) {
            System.out.println("For i: " + fi);
        }

        // do..while
        int dw = 10;
        do {
            System.out.println(dw);
            dw++;
        } while (dw < 20);

        // OOP
        /* Object creation */
        Puppy myPuppy = new Puppy( "tommy" );

        /* Call class method to set puppy's age */
        myPuppy.setAge( 2 );

        /* Call another class method to get puppy's age */
        myPuppy.getAge( );

        /* You can access instance variable as follows as well */
        System.out.println("Variable Value :");
    }
}