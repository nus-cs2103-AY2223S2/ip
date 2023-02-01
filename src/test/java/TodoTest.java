package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TodoTest {

    @Test
    public void toStringTest() {
        Todo t = new Todo("run");
        assertEquals(t.toString(), "T | 0 | run");
    }
}
