import java.awt.image.BufferedImage;

public class Rotate90CWFilter extends Filter {

    Rotate90CWFilter(App app) {
        super(app);
    }

    @Override
    protected BufferedImage transformImage(final BufferedImage oldImage) {
        int width = oldImage.getWidth();
        int height = oldImage.getHeight();
        BufferedImage newImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = oldImage.getRGB(x, y);
                newImage.setRGB(height - y - 1, x, rgb);
            }
        }

        return newImage;
    }
}