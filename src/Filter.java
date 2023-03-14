import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.logging.LogRecord;

public abstract class Filter implements ActionListener {

    private App app;
    Filter(App app) {
        super();
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        app.displayImage(transformImage(app.getInputImage()));
    }

    protected abstract BufferedImage transformImage(final BufferedImage oldImage);
}
