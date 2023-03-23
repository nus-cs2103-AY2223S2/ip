package duke.ui;

import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ListCellWrapper extends ListCell<Map.Entry<Boolean, String>> {
    private final Image userImage;
    private final Image dukeImage;
    private final ImageView imageView;
    private final VBox vBox;
    private final Label label;

    public ListCellWrapper(Image userImage, Image dukeImage) {
        this.userImage = userImage;        
        this.dukeImage = dukeImage;
        this.imageView = new ImageView();
        this.imageView.setPreserveRatio(true);
        imageView.setFitHeight(120);
        
        this.label = new Label();
        this.vBox = new VBox(label, imageView);
        this.vBox.maxWidthProperty().bind(imageView.fitWidthProperty());

        this.setFont(new Font(this.getFont().getName(), 18));
        this.setGraphicTextGap(20);
        this.setWrapText(true);

        label.setFont(new Font(this.getFont().getName(), 25));
        label.setTextAlignment(TextAlignment.CENTER);
    }

    @Override
    protected void updateItem(Map.Entry<Boolean, String> item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            imageView.setImage(item.getKey() ? userImage : dukeImage);
            label.setText(String.format("%s said:", item.getKey() ? "You" : "Duke"));

            setAlignment(item.getKey() ? Pos.CENTER_LEFT : Pos.CENTER_RIGHT);
            setContentDisplay(item.getKey() ? ContentDisplay.LEFT : ContentDisplay.RIGHT);
            setGraphic(vBox);
            setText(item.getValue());
        }
    }
}
