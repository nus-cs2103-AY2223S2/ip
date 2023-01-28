package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class FormatTest {

    @Test
    public void getCompletionDisplayTest() {
        String completedDisplay = Format.getCompletionDisplay(true);
        String notCompletedDisplay = Format.getCompletionDisplay(false);

        // Test if the method returns "[X]" for completed tasks
        assertEquals("[X]", completedDisplay);

        // Test if the method returns "[ ]" for incomplete tasks
        assertEquals("[ ]", notCompletedDisplay);
    }
}
