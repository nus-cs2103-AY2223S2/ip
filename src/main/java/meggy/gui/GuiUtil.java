package meggy.gui;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/** Class that stores all GUI-related constants statically. */
public class GuiUtil {
    static final Image USER_PROF_PIC;
    static final Image MEGGY_PROF_PIC;
    static final int DIALOG_BOX_CORNER_RADIUS = 20;
    static final Background MEGGY_DIALOG_BG =
            new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(GuiUtil.DIALOG_BOX_CORNER_RADIUS), null));
    private static final int DEFAULT_IMG_SIZE = 1;

    static {
        final InputStream userImageIn = MainWindow.class.getResourceAsStream("/images/User.jpg");
        USER_PROF_PIC = userImageIn == null ? new WritableImage(DEFAULT_IMG_SIZE, DEFAULT_IMG_SIZE)
                : new Image(userImageIn);
        final InputStream dukeImageIn = MainWindow.class.getResourceAsStream("/images/Meggy.png");
        MEGGY_PROF_PIC = dukeImageIn == null ? new WritableImage(DEFAULT_IMG_SIZE, DEFAULT_IMG_SIZE)
                : new Image(dukeImageIn);
    }

    /** @deprecated Class stores all values statically should not be initialized. */
    private GuiUtil() {
    }
}
