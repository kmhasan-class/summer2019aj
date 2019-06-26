/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambda.expressions.demo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;

// functional interface
interface A {
    void doSomething();
}

// Not a functional interface
interface B {
    void doSomething();
    void doNothing();
}

// functional interface
@FunctionalInterface
interface C {
    void doSomething();
    default void doNothing() {
        System.out.println("Not doing anything at all, really");
    }
}

@FunctionalInterface
interface ArithmeticOperation {
    public int apply(int a, int b);
}

class Operation {
    public int add(int a, int b) {
        return a + b;
    }
    
    public int subtract(int a, int b) {
        return a - b;
    }
}
/**
 *
 * @author kmhasan
 */
public class LambdaExpressionsDemo {
    // Inner class
    class StudentComparatorByIdAsc implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return (int) (o1.getId() - o2.getId());
        }

    }

    class StudentComparatorByIdDesc implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return (int) (o2.getId() - o1.getId());
        }
    }

    class StudentComparatorByGpaAsc implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return (int) (o1.getGpa() * 100 - o2.getGpa() * 100);
        }
    }

    public void integerArraySorting() {
        System.out.println("\nInteger sorting");
        int array[] = {35, 10, 3, 9, 4, 12, 15, 3, 6, 61};
        System.out.println("Before sorting");
        System.out.println(Arrays.toString(array));
        Arrays.sort(array);
        System.out.println("After sorting");
        System.out.println(Arrays.toString(array));
    }

    public void stringArraySorting() {
        System.out.println("\nString sorting");
        String array[] = {"35", "10", "3", "9", "4", "12", "15", "3", "6", "61"};
        System.out.println("Before sorting");
        System.out.println(Arrays.toString(array));
        Arrays.sort(array);
        System.out.println("After sorting");
        System.out.println(Arrays.toString(array));
    }

    public void studentArraySorting() {
        System.out.println("\nStudent sorting");
        Student array[] = {
            new Student(4642l, "Abul", LocalDate.of(1998, Month.AUGUST, 12), 2.45),
            new Student(4612l, "Babul", LocalDate.of(1999, Month.APRIL, 10), 3.22),
            new Student(5742l, "Ratul", LocalDate.of(2000, Month.MARCH, 12), 3.75),
            new Student(3992l, "Putul", LocalDate.of(2000, Month.DECEMBER, 30), 2.99),};
        System.out.println("Before sorting");
        for (Student student : array) {
            System.out.println(student);
        }
//        Arrays.sort(array, new StudentComparatorByIdAsc());
//        Arrays.sort(array, new StudentComparatorByIdDesc());
//        Arrays.sort(array, new StudentComparatorByGpaAsc());

        // Anonymous Inner Class
        Arrays.sort(array, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) (o2.getGpa() * 100 - o1.getGpa() * 100);
            }
        });
        
        // Lambda expression (version 1)
        Arrays.sort(array, 
            (Student o1, Student o2) -> {
                return (int) (o2.getGpa() * 100 - o1.getGpa() * 100);
            }
        );
        
        // Lambda expression (version 2)
        Arrays.sort(array, 
            (o1, o2) -> {
                return (int) (o2.getGpa() * 100 - o1.getGpa() * 100);
            }
        );
        
        // Lambda expression (version 3)
        Arrays.sort(array, (o1, o2) -> (int) (o2.getGpa() * 100 - o1.getGpa() * 100));

        // Lambda expression (version 4)
        Arrays.sort(array, Comparator.comparing(Student::getGpa).reversed());
        
        
        System.out.println("After sorting");
        for (Student student : array) 
            System.out.println(student);
        
    }

    public LambdaExpressionsDemo() {
        System.out.println("Hello AJ!");
//        integerArraySorting();
//        stringArraySorting();
//        studentArraySorting();
//        operationsDemo();
          threadDemo();
    }

    public void operationsDemo() {
        Operation operation = new Operation();
        System.out.println("2 + 10 = " + operation.add(2, 10));
        
        ArithmeticOperation adder = (a, b) -> a + b;
        
        System.out.println("2 + 10 = " + adder.apply(2, 10));
        
        int x = 2;
        int y = 10;
        
        System.out.printf("%d + %d = %d\n", x, y, calculate(x, y, (a, b) -> a + b));
        System.out.printf("%d - %d = %d\n", x, y, calculate(x, y, (a, b) -> a - b));
        System.out.printf("%d + %d * %d = %d\n", x, x, y, calculate(x, y, (a, b) -> a + a * b));
    }
    
    public int calculate(int a, int b, ArithmeticOperation operation) {
        return operation.apply(a, b);
    }
    
    class MyThread extends Thread {
        String label;
        
        public MyThread(String label) {
            this.label = label;
        }
        
        @Override
        public void run() {
            for (int i = 1; i <= 1000; i++)
                System.out.printf("%10s %4d\n", label, i);
        }
    }
    
    class YourThread implements Runnable {
        String label;
        
        public YourThread(String label) {
            this.label = label;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 100000; i++) {
                System.out.printf("%10s %4d %.5f\n", label, i, Math.sqrt(i));
            }
        }
        
    }
    
    
    public void threadDemo() {
//        MyThread threadA = new MyThread("A");
//        MyThread threadB = new MyThread("B");
        Thread threadA = new Thread(new YourThread("A"));
        Thread threadB = new Thread(new YourThread("B"));
        Thread threadC = new Thread(() -> {
                String label = "C";
                for (int i = 1; i <= 1000; i++)
                    System.out.printf("%10s %4d\n", label, i);
            });
        threadA.start();
        threadB.start();
        threadC.start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new LambdaExpressionsDemo();
    }

}
