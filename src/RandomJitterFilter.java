import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class RandomJitterFilter extends Filter {

    private int jitterRadius = 30;

    RandomJitterFilter(App app) {
        super(app);
    }

    @Override
    protected BufferedImage transformImage(final BufferedImage oldImage) {
        int width = oldImage.getWidth();
        int height = oldImage.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Random random = new Random();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int offsetX = random.nextInt(jitterRadius * 2 + 1) - jitterRadius;
                int offsetY = random.nextInt(jitterRadius * 2 + 1) - jitterRadius;
                int newX = Math.max(0, Math.min(x + offsetX, width - 1));
                int newY = Math.max(0, Math.min(y + offsetY, height - 1));
                int rgb = oldImage.getRGB(newX, newY);
                newImage.setRGB(x, y, rgb);
            }
        }

        return newImage;
    }
}
