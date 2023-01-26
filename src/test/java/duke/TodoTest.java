package duke;

import duke.tasklist.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToStringComplete() {
        Todo task = new Todo("This is Todo.");
        task.changeCompletion();
        String actualOutput = task.toString();
        assertEquals("[T][X] This is Todo.", actualOutput);
    }
}
