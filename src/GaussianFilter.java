import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class GaussianFilter implements ActionListener {

    private View view;

    GaussianFilter(View view) {
        super();
        this.view = view;
    }

    public void actionPerformed(ActionEvent e)
    {
        BufferedImage oldImage = this.view.getInputFile();
        int width = oldImage.getWidth();
        int height = oldImage.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int mask[] = {1, 2, 1, 2, 4, 2, 1, 2, 1};
        int sumMask = 16;

        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                int[] neighbours = {
                        oldImage.getRGB(x - 1, y - 1),
                        oldImage.getRGB(x, y - 1),
                        oldImage.getRGB(x + 1, y - 1),
                        oldImage.getRGB(x - 1, y),
                        oldImage.getRGB(x, y),
                        oldImage.getRGB(x + 1, y),
                        oldImage.getRGB(x - 1, y + 1),
                        oldImage.getRGB(x, y + 1),
                        oldImage.getRGB(x + 1, y + 1),
                };

                int[] R = new int[9];
                int[] G = new int[9];
                int[] B = new int[9];

                for (int i = 0; i < neighbours.length; i++) {
                    Color color = new Color(neighbours[i]);
                    R[i] = color.getRed() * mask[i];
                    G[i] = color.getGreen() * mask[i];
                    B[i] = color.getBlue() * mask[i];
                }

                int sumR = 0;
                int sumG = 0;
                int sumB = 0;

                for (int i = 0; i < 9; i++) {
                    sumR += R[i];
                    sumG += G[i];
                    sumB += B[i];
                }

                int finalR = sumR / sumMask;
                int finalG = sumG / sumMask;
                int finalB = sumB / sumMask;

                Color target = new Color(finalR, finalG, finalB);
                newImage.setRGB(x, y, target.getRGB());
            }
        }

        this.view.displayImage(newImage);
    }

}