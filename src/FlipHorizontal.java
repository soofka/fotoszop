import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;


public class FlipHorizontal extends Filter {

    FlipHorizontal(App app) {
        super(app);
    }

    @Override
    protected BufferedImage transformImage(final BufferedImage oldImage)
    {
        int width = oldImage.getWidth();
        int height = oldImage.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                newImage.setRGB(width - x - 1, y, oldImage.getRGB(x, y));
            }
        }

        return newImage;
    }

}