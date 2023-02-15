package gui;

import javafx.scene.control.Label;

/**
 * Helps to create message box.
 */
public class LabelHelper {
    /**
     * Constructs a message box from text.
     * @param s Text to show.
     * @return Desired Label object.
     */
    public static Label from(String s) {
        Label p = new Label(s);
        p.setWrapText(true);
        return p;
    }
}
