package ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TextUiTest {
    @Test
    public void testGetUserInput() {
        TextUi ui = new TextUi("name");

        assertTrue(ui.isEnd("bye"));
        assertFalse(ui.isEnd("bye-bye"));
    }
}
