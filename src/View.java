import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class View {

    private JFrame frame;
    private BufferedImage inputFile = null;
    private ImageIcon image;

    View() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("Fotoszop");

        try {
            this.inputFile = ImageIO.read(new File("lekcja.jpg"));
            this.frame.setIconImage(ImageIO.read(new File("logo.png")));
        } catch(IOException e) {
            System.out.println(e);
        }

        JMenuBar menuBar = new JMenuBar();

        JMenu imageMenu = new JMenu("(I)mage");
        imageMenu.setMnemonic(KeyEvent.VK_I);
        menuBar.add(imageMenu);

        // ZADANIE 1: dodaj przycisk do menu, korzystając z przykładu poniżej
        // dodaj do przycisku własną nazwę i przycisk
        // niech użycie przysisku tworzy obiekt twojej klasy
        JMenuItem grayScaleFilterMenuItem = new JMenuItem("(G)rayscale", KeyEvent.VK_G);
        JMenuItem greenHueFilterMenuItem = new JMenuItem("Set hue to green", KeyEvent.VK_G);
        JMenuItem removeRedFilterMenuItem = new JMenuItem("Remove (R)ed", KeyEvent.VK_R);
        JMenuItem SaturationIncreaseMenuItem = new JMenuItem("(S)aturation Increase", KeyEvent.VK_S);

        grayScaleFilterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        greenHueFilterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        removeRedFilterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK
        SaturationIncreaseMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));

        imageMenu.add(grayScaleFilterMenuItem);
        imageMenu.add(greenHueFilterMenuItem);
        imageMenu.add(removeRedFilterMenuItem);
        imageMenu.add(SaturationIncreaseMenuItem);

        grayScaleFilterMenuItem.addActionListener(new GrayscaleFilter(this));
        greenHueFilterMenuItem.addActionListener(new HueToGreenFilter(this));
        removeRedFilterMenuItem.addActionListener(new RemoveRedFilter(this));
        SaturationIncreaseMenuItem.addActionListener(new SaturationIncrease(this));

        this.frame.setJMenuBar(menuBar);

        this.image = new ImageIcon();
        JLabel label = new JLabel(this.image);
        this.frame.getContentPane().add(label, BorderLayout.CENTER);
        this.displayImage(this.inputFile);

        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void displayImage(BufferedImage originalImage) {
        Image image = originalImage.getScaledInstance(640, 480, Image.SCALE_SMOOTH);
        this.image.setImage(image);
    }

    public BufferedImage getInputFile() {
        return this.inputFile;
    }

    public void setInputFile(BufferedImage inputFile) {
        this.inputFile = inputFile;
    }

}