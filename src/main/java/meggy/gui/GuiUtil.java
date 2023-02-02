package meggy.gui;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/** Class that stores all GUI-related constants statically. */
public class GuiUtil {
    static final Image USER_PROF_PIC;
    static final Image MEGGY_PROF_PIC;

    static {
        final InputStream userImageIn = MainWindow.class.getResourceAsStream("/images/User.jpg");
        USER_PROF_PIC = userImageIn == null ? new WritableImage(1, 1) : new Image(userImageIn);
        final InputStream dukeImageIn = MainWindow.class.getResourceAsStream("/images/Meggy.png");
        MEGGY_PROF_PIC = dukeImageIn == null ? new WritableImage(1, 1) : new Image(dukeImageIn);
    }

    /** @deprecated Class stores all values statically should not be initialized. */
    private GuiUtil() {
    }
}
