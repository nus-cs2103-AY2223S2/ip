package ui;

import org.junit.jupiter.api.Test;

import ui.TextUi;

import static org.junit.jupiter.api.Assertions.*;


public class TextUiTest {
    @Test
    public void testGetUserInput() {
        TextUi ui = new TextUi("name");

        assertTrue(ui.isEnd("bye"));
        assertFalse(ui.isEnd("bye-bye"));
    }
}
