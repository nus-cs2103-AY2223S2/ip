package duke.ui;

import java.util.LinkedList;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class ListViewWrapper {
    private final static Image DUKE_IMAGE = new Image(ListCellWrapper.class.getResource("/dukeImage.jpg").toString());
    private final static Image USER_IMAGE = new Image(ListCellWrapper.class.getResource("/userImage.jpg").toString());;
    private final ListView<Map.Entry<Boolean, String>> listView;
    private final ObservableList<Map.Entry<Boolean, String>> text = FXCollections.observableList(new LinkedList<>());

    public ListViewWrapper(ListView<Map.Entry<Boolean, String>> listView) {
        this.listView = listView;
        this.listView.setItems(text);
        this.listView.setCellFactory(lst -> {
            ListCellWrapper cell = new ListCellWrapper(DUKE_IMAGE, USER_IMAGE);
            cell.prefWidthProperty().bind(listView.widthProperty().subtract(2));
            return cell;
        });
    }

    public void writeAsDuke(String value) {
        text.add(Map.entry(false, value));
        this.listView.scrollTo(text.size() - 1);
    }

    public void writeAsUser(String value) {
        text.add(Map.entry(true, value));
        this.listView.scrollTo(text.size() - 1);
    }
}
