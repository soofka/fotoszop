import java.awt.*;
import java.awt.image.BufferedImage;

public class GaussianSharpening extends Filter {

    GaussianSharpening(App app) {
        super(app);
    }

    @Override
    protected BufferedImage transformImage(final BufferedImage oldImage) {
        int width = oldImage.getWidth();
        int height = oldImage.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int mask[][] = {
                {0, -2, 0},
                {-2, 11, -2},
                {0, -2, 0}
        };
        int divisor = 3;

        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                int r = 0;
                int g = 0;
                int b = 0;

                for (int mx = -1; mx <= 1; mx++) {
                    for (int my = -1; my <= 1; my++) {
                        int mwidth = x + mx;
                        int mheight = y + my;
                        int mrgb = oldImage.getRGB(mwidth, mheight);
                        Color moriginal = new Color(mrgb);

                        int mr = moriginal.getRed();
                        int mg = moriginal.getGreen();
                        int mb = moriginal.getBlue();

                        r += (mr * mask[mx + 1][my + 1]);
                        g += (mg * mask[mx + 1][my + 1]);
                        b += (mb * mask[mx + 1][my + 1]);
                    }
                }

                r /= divisor;
                g /= divisor;
                b /= divisor;

                r = truncateColorValue(r);
                g = truncateColorValue(g);
                b = truncateColorValue(b);

                Color newColor = new Color(r, g, b);
                newImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return newImage;
    }

    private int truncateColorValue(int value) {
        if (value < 0) {
            return 0;
        } else if (value > 255) {
            return 255;
        } else {
            return value;
        }
    }
}