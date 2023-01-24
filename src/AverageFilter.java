import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class AverageFilter implements ActionListener {

    private View view;

    AverageFilter(View view) {
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
                Color c1 = new Color(oldImage.getRGB(x-1, y-1));
                Color c2 = new Color(oldImage.getRGB(x-1, y));
                Color c3 = new Color(oldImage.getRGB(x-1, y+1));
                Color c4 = new Color(oldImage.getRGB(x, y-1));
                Color c5 = new Color(oldImage.getRGB(x, y+1));
                Color c6 = new Color(oldImage.getRGB(x+1, y-1));
                Color c7 = new Color(oldImage.getRGB(x+1, y));
                Color c8 = new Color(oldImage.getRGB(x+1, y+1));

                int[] arrR = {c1.getRed(), c2.getRed(), c3.getRed(), c4.getRed(), c5.getRed(), c6.getRed(), c7.getRed(), c8.getRed()};
                int[] arrG = {c1.getGreen(), c2.getGreen(), c3.getGreen(), c4.getGreen(), c5.getGreen(), c6.getGreen(), c7.getGreen(), c8.getGreen()};
                int[] arrB = {c1.getBlue(), c2.getBlue(), c3.getBlue(), c4.getBlue(), c5.getBlue(), c6.getBlue(), c7.getBlue(), c8.getBlue()};

                int sumR = 0;
                int sumG = 0;
                int sumB = 0;

                for (int i = 0; i < 8; i++) {
                    sumR += arrR[i];
                    sumG += arrG[i];
                    sumB += arrB[i];
                }

                int avgR = sumR/8;
                int avgG = sumG/8;
                int avgB = sumB/8;

                Color color = new Color(avgR, avgG, avgB);
                newImage.setRGB(x, y, color.getRGB());
            }
        }

        this.view.displayImage(newImage);
        System.out.println("median done");
    }

}