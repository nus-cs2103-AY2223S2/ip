package book.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Class that encapsulates the {@code DialogBox} used in the {@code Gui} class.
 */
public class DialogBox extends HBox {
    /** {@code ImageView} image of the {@code DialogBox}. */
    private ImageView image;
    /** {@code Label} containing {@code String} text of the {@code DialogBox}. */
    private Label label;
    /** {@code Color} of line borders used. */
    private Color lineColor = Color.BLACK;

    /**
     * Initializes a {@code DialogBox} instance with the given {@code ImageView} image and
     * {@code Label} containing the {@code String} text, and handles the formatting and layout.
     *
     * @param image {@code ImageView} image used in the {@code DialogBox}.
     * @param label {@code Label} containing text used in the {@code DialogBox}.
     */
    public DialogBox(ImageView image, Label label) {
        this.image = image;
        this.label = label;
        this.label.setPadding(new Insets(0.0, 0.0, 0.0, Gui.EDGE_INSETS));
        this.label.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
        this.label.setWrapText(true);
        this.label.setMinSize(
                Gui.GUI_WIDTH - Gui.MARGIN_WIDTH - (2 * Gui.EDGE_INSETS),
                Gui.LINE_SPACING);
        this.label.setBorder(new Border(new BorderStroke(
                Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, this.lineColor,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(Gui.EDGE_INSETS),
                Insets.EMPTY)));
        this.image.setFitWidth(Gui.MARGIN_WIDTH);
        this.image.setFitHeight(Gui.LINE_SPACING);
        this.setAlignment(Pos.TOP_LEFT);
        this.setBorder(new Border(new BorderStroke(
                this.lineColor,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(Gui.EDGE_INSETS))));
        this.getChildren().addAll(image, label);
    }
}
