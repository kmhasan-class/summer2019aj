/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executor.demo;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

class YourThread implements Runnable {
    String label;
    int n;
    
    public YourThread(String label, int n) {
        this.label = label;
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 1; i <= n; i++) {
            try {
                System.out.printf("%10s %4d %.5f\n", label, i, Math.sqrt(i));
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(YourThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class YourCalculatingThread implements Callable<Long> {
    private long startingNumber;
    private long endingNumber;

    public YourCalculatingThread(long startingNumber, long endingNumber) {
        this.startingNumber = startingNumber;
        this.endingNumber = endingNumber;
    }
    
    @Override
    public Long call() throws Exception {
        long result = 0;
        for (long i = startingNumber; i <= endingNumber; i++)
            result = result + i;
        return result;
    }
}

/**
 *
 * @author kmhasan
 */
public class ExecutorDemo {

    public ExecutorDemo() {
//        
//        for (int i = 0; i < 50; i++) {
//            Thread t1 = new Thread(new YourThread("A" + i , 5));
        //            t1.start();
//        }
//
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors " + availableProcessors);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(availableProcessors);
        for (int i = 0; i < 3; i++)
            fixedThreadPool.execute(new YourThread("A" + i , 5));
        for (int i = 0; i < 3; i++)
            fixedThreadPool.execute(new YourThread("B" + i , 5));
        for (int i = 0; i < 3; i++)
            fixedThreadPool.submit(new YourThread("C" + i , 5));
        
        Future<Long> result = fixedThreadPool.submit(new YourCalculatingThread(1, 10000000000l));

        System.out.println("Done with submitting tasks");
        fixedThreadPool.shutdown();
        
        try {
            System.out.println("Result is " + result.get(2, TimeUnit.SECONDS));
        } catch (InterruptedException ex) {
            Logger.getLogger(ExecutorDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ExecutorDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ExecutorDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Done with everything");
        
//        fixedThreadPool.execute(new YourThread("C" + 0 , 5));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ExecutorDemo();
    }
    
}
