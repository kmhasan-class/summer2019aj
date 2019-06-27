package histogram;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.*;
import javax.imageio.ImageIO;

public class Histogram {
    private int[] splitRGB(int rgb) {
        int colors[] = new int[3];
        for (int i = 0; i < 3; i++)
            colors[i] = (rgb >> (8 * i)) & 0xFF;
        return colors;
    }
    
    private int rgbToGrayscale(int colors[]) {
        return (int) (colors[2] * 0.2126 + colors[1] * 0.7152 + colors[0] * 0.0722);
    }

    private int[] calculateHistogram(String filename) {
        int histogram[] = new int[256];
        try {
            File file = new File(filename);
            BufferedImage image = ImageIO.read(file);
            
            int width = image.getWidth();
            int height = image.getHeight();
            for (int c = 0; c < width; c++)
                for (int r = 0; r < height; r++) {
                    int grayscale = rgbToGrayscale(splitRGB(image.getRGB(c, r)));
                    histogram[grayscale]++;
                }
        } catch (IOException ex) {
            Logger.getLogger(Histogram.class.getName()).log(Level.SEVERE, null, ex);
        }
        return histogram;
    }
    
    public Histogram() {
        long startTime = System.currentTimeMillis();
        int histogram[] = calculateHistogram("harley-davidson.jpg");
        long stopTime = System.currentTimeMillis();
        System.out.printf("Time taken: %.3f seconds\n", (stopTime - startTime) / 1000.0);
        for (int i = 0; i < histogram.length; i++)
            System.out.printf("%3d %d\n", i, histogram[i]);
    }

    public static void main(String[] args) {
        new Histogram();
    }
}
