import java.awt.*;
import java.awt.image.BufferedImage;

public class Pixelate extends Filter {

    Pixelate(App app) {
        super(app);
    }

    @Override
    public BufferedImage transformImage(final BufferedImage oldImage) {
        BufferedImage newImage = new BufferedImage(oldImage.getWidth(), oldImage.getHeight(), oldImage.getType());
        int xBlocks = (int) Math.ceil((double) oldImage.getWidth() / (double) 8);
        int yBlocks = (int) Math.ceil((double) oldImage.getHeight() / (double) 5);

        for (int x = 0; x < xBlocks; x++) {
            for (int y = 0; y < yBlocks; y++) {
                int blockX = x * 8;
                int blockY = y * 5;
                int blockWidth = Math.min(8, oldImage.getWidth() - blockX);
                int blockHeight = Math.min(5, oldImage.getHeight() - blockY);

                int redTotal = 0;
                int greenTotal = 0;
                int blueTotal = 0;

                for (int i = blockX; i < blockX + blockWidth; i++) {
                    for (int j = blockY; j < blockY + blockHeight; j++) {
                        Color color = new Color(oldImage.getRGB(i, j));
                        redTotal += color.getRed();
                        greenTotal += color.getGreen();
                        blueTotal += color.getBlue();
                    }
                }

                int numPixels = blockWidth * blockHeight;
                int redAverage = redTotal / numPixels;
                int greenAverage = greenTotal / numPixels;
                int blueAverage = blueTotal / numPixels;

                for (int i = blockX; i < blockX + blockWidth; i++) {
                    for (int j = blockY; j < blockY + blockHeight; j++) {
                        Color color = new Color(redAverage, greenAverage, blueAverage);
                        newImage.setRGB(i, j, color.getRGB());
                    }
                }
            }
        }
        return newImage;
    }
}
