package gui;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * Helps to give a round-shaped image for avatar purposes.
 */
public class RoundAvatarHelper {
    /**
     * Creates a new avatar component.
     *
     * @param imageView Avatar image.
     */
    public static void clip(ImageView imageView, double radius) {
        Circle clip = new Circle(radius, radius, radius);
        imageView.setClip(clip);
    }
}
