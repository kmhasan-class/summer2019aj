package histogram;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.*;
import javax.imageio.ImageIO;

public class Histogram {

    private int[] splitRGB(int rgb) {
        int colors[] = new int[3];
        for (int i = 0; i < 3; i++) {
            colors[i] = (rgb >> (8 * i)) & 0xFF;
        }
        return colors;
    }

    private int rgbToGrayscale(int colors[]) {
        return (int) (colors[2] * 0.2126 + colors[1] * 0.7152 + colors[0] * 0.0722);
    }

    public int[] calculateHistogram(String filename) {
        int histogram[] = new int[256];
        try {
            File file = new File(filename);
            BufferedImage image = ImageIO.read(file);

            int width = image.getWidth();
            int height = image.getHeight();
            for (int c = 0; c < width; c++) {
                for (int r = 0; r < height; r++) {
                    int grayscale = rgbToGrayscale(splitRGB(image.getRGB(c, r)));
                    histogram[grayscale]++;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Histogram.class.getName()).log(Level.SEVERE, null, ex);
        }
        return histogram;
    }

    class ParallelHistogram implements Callable<int[]> {

        private BufferedImage image;
        private int widthStart;
        private int widthStop;

        public ParallelHistogram(BufferedImage image, int widthStart, int widthStop) {
            this.widthStart = widthStart;
            this.widthStop = widthStop;
            this.image = image;
        }

        @Override
        public int[] call() throws Exception {
            int histogram[] = new int[256];
            int height = image.getHeight();
            for (int c = widthStart; c < widthStop; c++) {
                for (int r = 0; r < height; r++) {
                    int grayscale = rgbToGrayscale(splitRGB(image.getRGB(c, r)));
                    histogram[grayscale]++;
                }
            }
            return histogram;
        }
    }

    public int[] parallelCalculateHistogram(String filename) {
        int histogram[] = new int[256];
        // instantiate objects of ParallelHistogram from here, combine the results and return
        try {
            File file = new File(filename);
            BufferedImage image = ImageIO.read(file);
            
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            
            ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);
            
            int width = image.getWidth();
            int dw = width / availableProcessors;

            List<Future<int[]>> futureResultList = new ArrayList<>();
            
            for (int i = 0; i < availableProcessors; i++) {
                ParallelHistogram parallelHistogram = null;
                if (i != availableProcessors - 1)
                    parallelHistogram = new ParallelHistogram(image, i * dw, (i + 1) * dw);
                else parallelHistogram = new ParallelHistogram(image, i * dw, width);
                Future<int[]> result = executorService.submit(parallelHistogram);
                futureResultList.add(result);
            }
            
            executorService.shutdown();
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Histogram.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            for (int i = 0; i < futureResultList.size(); i++)
                try {
                    int partialHistogram[] = futureResultList.get(i).get(20, TimeUnit.SECONDS);
                    printHistogram(partialHistogram, "Par" + i, 0, 5);
                    for (int h = 0; h < partialHistogram.length; h++)
                        histogram[h] += partialHistogram[h];
                } catch (InterruptedException ex) {
                    Logger.getLogger(Histogram.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(Histogram.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TimeoutException ex) {
                    Logger.getLogger(Histogram.class.getName()).log(Level.SEVERE, null, ex);
                }
        } catch (IOException ex) {
            Logger.getLogger(Histogram.class.getName()).log(Level.SEVERE, null, ex);
        }
        return histogram;
    }
    
    public void printHistogram(int histogram[], String label, int start, int end) {
        System.out.print(label + ":");
        for (int i = start; i < end; i++) {
            System.out.print(" (" + i + ":" + histogram[i] + ")");
        }
        System.out.println();
    }

    public Histogram() {
        /*
        System.out.println("Started calculating the histogram");
        long startTime = System.currentTimeMillis();
        int histogram[] = calculateHistogram("harley-davidson.jpg");
        long stopTime = System.currentTimeMillis();
        System.out.printf("Time taken: %.3f seconds\n", (stopTime - startTime) / 1000.0);
//        for (int i = 0; i < histogram.length; i++) {
//            System.out.printf("%3d %d\n", i, histogram[i]);
//        }
        printHistogram(histogram, "Seq0", 0, 5);

        startTime = System.currentTimeMillis();
        histogram = parallelCalculateHistogram("harley-davidson.jpg");
        stopTime = System.currentTimeMillis();
        System.out.printf("Time taken: %.3f seconds\n", (stopTime - startTime) / 1000.0);
        
        printHistogram(histogram, "ParX", 0, 5);
*/
    }

    public static void main(String[] args) {
        new Histogram();
    }
}
