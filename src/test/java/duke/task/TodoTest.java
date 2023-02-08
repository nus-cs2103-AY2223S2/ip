package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void dummyTest() {
        Todo t = new Todo("do work");
        assertEquals("[T][ ] do work ", t.toString());
    }
}
