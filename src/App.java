import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class App {

    private JFrame frame;
    private BufferedImage inputImage = null;
    private ImageIcon displayedImage;
    private JMenu imageMenu;
    private JMenuBar menuBar;
    private JLabel label;

    final private int width = 640;
    final private int height = 480;

    App() {

    }

    public void run() {

        initWindow();

        addFilter(GrayscaleFilter.class, "(G)rayscale", KeyEvent.VK_G);
        addFilter(HueToGreenFilter.class, "Set hue to green (L)", KeyEvent.VK_L);
        addFilter(RemoveRedFilter.class, "Remove (R)ed", KeyEvent.VK_R);
        addFilter(SaturationIncrease.class, "(S)aturation Increase", KeyEvent.VK_S);
        addFilter(FlipHorizontal.class, "Flip (H)orizontal", KeyEvent.VK_H);

        setInputImage("lekcja.jpg");

    }

    private void initWindow() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("Fotoszop");

        this.menuBar = new JMenuBar();

        this.imageMenu = new JMenu("(I)mage");
        this.imageMenu.setMnemonic(KeyEvent.VK_I);
        this.menuBar.add(imageMenu);

        this.frame.setJMenuBar(menuBar);

        try {
            this.frame.setIconImage(ImageIO.read(new File("logo.png")));
        } catch(IOException e) {
            System.out.println(e);
        }

        this.displayedImage = new ImageIcon();
        this.label = new JLabel(this.displayedImage);
        this.frame.getContentPane().add(label, BorderLayout.CENTER);

        this.frame.pack();
        this.frame.setVisible(true);
    }
    public void setInputImage(String path) {
        try {
            this.inputImage = ImageIO.read(new File(path));
            displayImage(inputImage);
            this.frame.pack();
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public BufferedImage getInputImage() {
        return inputImage;
    }

    public void displayImage(BufferedImage image) {
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        this.displayedImage.setImage(scaledImage);
    }


    private <T extends Filter> void addFilter(Class<T> filterClass, String name, int key) {
        JMenuItem filterMenuItem = new JMenuItem(name, key);
        imageMenu.add(filterMenuItem);
        try {
            filterMenuItem.addActionListener(filterClass.getDeclaredConstructor(new Class[]{App.class}).newInstance(this));
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
