package rick.gui;

import java.io.IOException;
import java.io.InputStream;

import javafx.scene.text.Font;
import rick.MainApp;

/**
 * Represents the interface that loads font resources from the `resources`
 * folder.
 *
 * @author SeeuSim
 *         AY22/23S2-CS2103T
 */
public class FontLoader {
    private static double fontSize = 12.0;
    private static final String FONT_FOLDER = "/fonts/Open_Sans/static";

    /**
     * Represents the style of font to return.
     */
    enum FontStyle {
        BOLD("/OpenSans/OpenSans-Bold.ttf"),
        ITALIC("/OpenSans/OpenSans-Regular.ttf"),
        LIGHT("/OpenSans/OpenSans-Light.ttf"),
        MEDIUM("/OpenSans/OpenSans-Medium.ttf"),
        REGULAR("/OpenSans/OpenSans-Italic.ttf");

        private final String path;

        /**
         * Initialises the Enum value with the given path.
         * @param path The file path to the font file.
         */
        FontStyle(String path) {
            this.path = path;
        }

        /**
         * Generates and returns the font file path value.
         * @return The font file path.
         */
        public String getPath() {
            return FONT_FOLDER + this.path;
        }
    }

    /**
     * Sets the new font size for the returned font.
     *
     * @param newSize The new font size. Sizes between 10 and 40 are accepted.
     */
    public static void setFontSize(double newSize) {
        assert newSize >= 10;
        assert newSize <= 40;
        fontSize = newSize;
    }

    /**
     * Loads the font file and returns it for use.
     *
     * @param style The desired font style.
     * @return The font instance for use.
     */
    public static Font getFont(FontStyle style) {
        InputStream fontStream = MainApp.class.getResourceAsStream(style.getPath());
        try {
            if (fontStream != null) {
                Font displayFont = Font.loadFont(fontStream, fontSize);
                fontStream.close();
                return displayFont;
            }
        } catch (IOException err) {
            System.err.println(err.getMessage());
            System.exit(1);
        }
        return null;
    }
}
