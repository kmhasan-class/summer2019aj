/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streams.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kmhasan
 */
public class StreamsDemo {

    public StreamsDemo() {
        List<Integer> integerList = new ArrayList<>();
//        integerList.add(10);
//        integerList.add(20);
//        integerList.add(40);
//        integerList.add(30);
//        integerList.add(90);
//        integerList.add(60);
//        integerList.add(50);
        
        
        int sum = 0;
        for (Integer number : integerList)
            sum = sum + number;
        System.out.println("Sum " + sum);
        
        sum = integerList.stream().mapToInt(number -> number.intValue()).sum();
        System.out.println("Sum " + sum);
        
        
//        for (int i = 0; i < integerList.size(); i++)
//            System.out.println(integerList.get(i));
//        for (Integer number : integerList)
//            System.out.println(number);
//        int min = Integer.MAX_VALUE;
//        for (Integer number : integerList)
//            if (number < min)
//                min = number;
//        System.out.println("Min " + min);
        Optional<Integer> min = integerList.stream().min(Comparator.comparing(Integer::intValue));
        System.out.println("Min " + min.get());
                
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new StreamsDemo();
    }
    
}
