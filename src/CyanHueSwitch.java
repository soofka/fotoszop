import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class CyanHueSwitch implements ActionListener {

    private View view;

    CyanHueSwitch(View view) {
        super();
        this.view = view;
    }

    public void actionPerformed(ActionEvent e)
    { 
        BufferedImage oldImage = this.view.getInputFile();
        int width = oldImage.getWidth();
        int height = oldImage.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        float hue = 0.5f;
        for(int y = 0; y < width; y++) {
            for(int x = 0; x < height; x++) {
                int RGB = oldImage.getRGB(x,y);
                int R = (RGB >> 16) & 0xff;
                int G = (RGB >> 8) & 0xff;
                int B = (RGB) & 0xff;
                float HSV[] = new float[3];
                Color.RGBtoHSB(R, G, B, HSV);
                newImage.setRGB(x, y, Color.getHSBColor(hue, HSV[1], HSV[2]).getRGB());
            }
        }

        this.view.displayImage(newImage);
    }

}