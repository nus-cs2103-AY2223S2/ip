package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void test() {
        Todo todo1 = new Todo("homework");
        assertEquals("[T][ ] homework", todo1.toString());

        Todo todo2 = new Todo("watch lecture recording");
        todo2.markAsDone();
        assertEquals("[T][X] watch lecture recording", todo2.toString());

        Todo todo3 = new Todo("");
        assertEquals("[T][ ] ", todo3.toString());
    }
}
