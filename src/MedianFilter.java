import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class MedianFilter implements ActionListener {

    private View view;

    MedianFilter(View view) {
        super();
        this.view = view;
    }

    public void actionPerformed(ActionEvent e)
    {
        BufferedImage oldImage = this.view.getInputFile();
        int width = oldImage.getWidth();
        int height = oldImage.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                int[] neighbours = {
                        oldImage.getRGB(x - 1, y - 1),
                        oldImage.getRGB(x, y - 1),
                        oldImage.getRGB(x + 1, y - 1),
                        oldImage.getRGB(x - 1, y),
                        oldImage.getRGB(x + 1, y),
                        oldImage.getRGB(x - 1, y + 1),
                        oldImage.getRGB(x, y + 1),
                        oldImage.getRGB(x + 1, y + 1),
                };

                int[] R = new int[8];
                int[] G = new int[8];
                int[] B = new int[8];

                for (int i = 0; i < neighbours.length; i++) {
                    Color color = new Color(neighbours[i]);
                    R[i] = color.getRed();
                    G[i] = color.getGreen();
                    B[i] = color.getBlue();
                }

                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);

                int medianR = (R[3] + R[4]) / 2;
                int medianG = (G[3] + G[4]) / 2;
                int medianB = (B[3] + B[4]) / 2;

                Color target = new Color(medianR, medianG, medianB);
                newImage.setRGB(x, y, target.getRGB());
            }
        }

        this.view.displayImage(newImage);
    }

}