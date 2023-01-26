package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testMarkTask() {
        Todo todo = new Todo("read newspaper");
        todo.mark();
        assertEquals("[T][X] read newspaper", todo.toString());
    }

}
