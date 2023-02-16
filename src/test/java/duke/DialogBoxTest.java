package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;

public class DialogBoxTest {
    @Test
    public void testGetUserDialog() {
        JFXPanel jfxPanel = new JFXPanel();
        DialogBox dialogBox = DialogBox.getUserDialog("test text", new Image("https://picsum.photos/200/300"));
        assertNotNull(dialogBox);
        assertEquals("test text", dialogBox.getDialog().getText());
        assertEquals("label", dialogBox.getDialog().getStyleClass().get(0));
        assertNotNull(dialogBox.getDisplayPicture().getImage());
        assertEquals(Pos.TOP_RIGHT, dialogBox.getAlignment());
        assertTrue(dialogBox.getSpacing() == 15.0);
    }

    @Test
    public void testGetDukeDialog() {
        JFXPanel jfxPanel = new JFXPanel();
        DialogBox dialogBox = DialogBox.getDukeDialog("test text", new Image("https://picsum.photos/200/300"));
        assertNotNull(dialogBox);
        assertEquals("test text", dialogBox.getDialog().getText());
        assertEquals("label", dialogBox.getDialog().getStyleClass().get(0));
        assertNotNull(dialogBox.getDisplayPicture().getImage());
        assertEquals(Pos.TOP_LEFT, dialogBox.getAlignment());
        assertTrue(dialogBox.getSpacing() == 15.0);
        ObservableList<Node> children = dialogBox.getChildren();
        for (int i = 0; i < children.size() / 2; i++) {
            Node left = children.get(i);
            Node right = children.get(children.size() - 1 - i);
            assertNotEquals(left, right);
        }
    }
}
