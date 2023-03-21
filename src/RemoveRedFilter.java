import java.awt.*;
import java.awt.image.BufferedImage;

public class RemoveRedFilter extends Filter {

    RemoveRedFilter(App app) {
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
                int rgb = oldImage.getRGB(x, y);
                Color original = new Color(rgb);
                int r = original.getRed();
                int g = original.getGreen();
                int b = original.getBlue();
                Color target = new Color(0, g, b);
                newImage.setRGB(x, y, target.getRGB());
            }
        }

        return newImage;
    }

}