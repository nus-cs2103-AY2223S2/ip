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
    /** User profile image. */
    static final Image USER_PROF_PIC;
    /** Chatbot profile image. */
    static final Image MEGGY_PROF_PIC;
    /** Font to display all texts. */
    static final Font SPLAT_FONT;
    /** Corner radius of all dialog box. */
    private static final int DIALOG_BOX_CORNER_RADIUS = 20;
    /** Orange box with round corner. */
    static final Background MEGGY_DIALOG_BG =
            new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(GuiUtil.DIALOG_BOX_CORNER_RADIUS), null));
    /** Size of default image if preset image reading fails. */
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
        final InputStream fontStream = MainApplication.class.getResourceAsStream("/fonts/Splatoon2.otf");
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
