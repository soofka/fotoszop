import java.awt.*;
import java.awt.image.BufferedImage;

public class SetHueToCyan extends Filter {

    SetHueToCyan(App app) {
        super(app);
    }

    @Override
    protected BufferedImage transformImage(final BufferedImage oldImage) {
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

                float[] hsb = Color.RGBtoHSB(r, g, b, null); // Getting HSB values
                hsb[0] = (float) (180.0 / 360.0); // Setting first HSB value (Hue) to cyan
                Color target = Color.getHSBColor(hsb[0], hsb[1], hsb[2]); // Back to RGB

                newImage.setRGB(x, y, target.getRGB());
            }
        }

        return newImage;
    }
}
