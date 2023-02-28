import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

// ZADANIE 2: zrób kopię tego pliku, nadaj mu własną nazwę
// i zaimplementuj swoje przekształcenie obrazka w metodzie "actionPerformed"
public class SaturationIncrease implements ActionListener {

    private View view;

    SaturationIncrease(View view) {
        super();
        this.view = view;
    }

    public void actionPerformed(ActionEvent e)
    {
        BufferedImage oldImage = this.view.getInputFile();
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
                float[] hsb = Color.RGBtoHSB(r, g, b, null);
                hsb[1] *= 1.5;
                if (hsb[1] > 1) {
                    hsb[1] = 1;
                }
                int newrgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                newImage.setRGB(x, y, newrgb);
            }
        }

        this.view.displayImage(newImage);
    }

}