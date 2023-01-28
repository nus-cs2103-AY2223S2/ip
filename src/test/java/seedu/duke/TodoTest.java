package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void printTask_newTask_success() {
        assertEquals("[T][ ] eat", new Todo(0, "eat").printTask());
    }
}
