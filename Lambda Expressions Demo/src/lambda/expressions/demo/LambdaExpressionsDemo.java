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
        integerArraySorting();
        stringArraySorting();
        studentArraySorting();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new LambdaExpressionsDemo();
    }

}
