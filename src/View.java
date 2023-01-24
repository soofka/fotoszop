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
    private ImageIcon imageIcon;

    View() {
        try {
            this.inputFile = ImageIO.read(new File("balloons_noisy.png"));
        } catch(IOException e) {
            System.out.println(e);
        }

        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu imageMenu = new JMenu("(I)mage");
        imageMenu.setMnemonic(KeyEvent.VK_I);
        menuBar.add(imageMenu);

        JMenuItem grayScaleFilterMenuItem = new JMenuItem("(G)rayscale", KeyEvent.VK_G);
        grayScaleFilterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        imageMenu.add(grayScaleFilterMenuItem);
        grayScaleFilterMenuItem.addActionListener(new GrayscaleFilter(this));

        JMenuItem medianFilterMenuItem = new JMenuItem("(M)edian", KeyEvent.VK_M);
        medianFilterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        imageMenu.add(medianFilterMenuItem);
        medianFilterMenuItem.addActionListener(new MedianFilter(this));

        JMenuItem averageFilterMenuItem = new JMenuItem("(A)verage", KeyEvent.VK_A);
        averageFilterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
        imageMenu.add(averageFilterMenuItem);
        averageFilterMenuItem.addActionListener(new AverageFilter(this));

        this.frame.setJMenuBar(menuBar);

        this.imageIcon = new ImageIcon();
        JLabel label = new JLabel(this.imageIcon);
        this.frame.getContentPane().add(label, BorderLayout.CENTER);
        this.displayImage(this.inputFile);

        this.frame.pack();
        this.frame.setVisible(true);
    }

    public void displayImage(BufferedImage originalImage) {
        Image image = originalImage.getScaledInstance(640, 480, Image.SCALE_SMOOTH);
        this.imageIcon.setImage(image);
        System.out.println("display done");
    }

    public BufferedImage getInputFile() {
        return this.inputFile;
    }

    public void setInputFile(BufferedImage inputFile) {
        this.inputFile = inputFile;
        System.out.println("set done");
    }

}
