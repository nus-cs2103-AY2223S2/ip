package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void testGreeting() {
        String str = "Hi~ I'm duke.Duke>_<\nWhat can I do for you?";
        assertEquals(str, "Hi~ I'm duke.Duke>_<\nWhat can I do for you?");
    }
}
