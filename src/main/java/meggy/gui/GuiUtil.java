package meggy.gui;

import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/** Class that stores all GUI-related constants statically. */
public class GuiUtil {
    static final Image USER_PROF_PIC;
    static final Image MEGGY_PROF_PIC;
    static final int DIALOG_BOX_CORNER_RADIUS = 20;
    static final Background MEGGY_DIALOG_BG =
            new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(GuiUtil.DIALOG_BOX_CORNER_RADIUS), null));
    static final Font SPLAT_FONT;
    private static final int DEFAULT_IMG_SIZE = 1;

    static {
        // Load pics
        final InputStream userImageIn = MainWindow.class.getResourceAsStream("/images/User.jpg");
        USER_PROF_PIC = userImageIn == null ? new WritableImage(DEFAULT_IMG_SIZE, DEFAULT_IMG_SIZE)
                : new Image(userImageIn);
        final InputStream dukeImageIn = MainWindow.class.getResourceAsStream("/images/Meggy.png");
        MEGGY_PROF_PIC = dukeImageIn == null ? new WritableImage(DEFAULT_IMG_SIZE, DEFAULT_IMG_SIZE)
                : new Image(dukeImageIn);

        // Load fonts
        final InputStream fontStream = MainApplication.class.getResourceAsStream("/fonts/Splatfont2.ttf");
        if (fontStream == null) {
            SPLAT_FONT = null;
        } else {
            SPLAT_FONT = Font.loadFont(fontStream, 12);
            try {
                fontStream.close();
            } catch (IOException ignored) {
            } // We can't help if close() fails.
        }
    }

    /** @deprecated Class stores all values statically should not be initialized. */
    private GuiUtil() {
    }
}
