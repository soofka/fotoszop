import java.awt.*;
import java.awt.image.BufferedImage;

public class RemoveGreen extends Filter {

    RemoveGreen(App app) {
        super(app);
    }

    @Override
    protected BufferedImage transformImage(final BufferedImage oldImage) {
        int width = oldImage.getWidth();
        int height = oldImage.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int oldRGB = oldImage.getRGB(x, y);
                int alpha = (oldRGB >> 24) & 0xFF;
                int red = (oldRGB >> 16) & 0xFF;
                int green = 0;
                int blue = oldRGB & 0xFF;

                int newRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
                newImage.setRGB(x, y, newRGB);
            }
        }

        return newImage;
    }
}
