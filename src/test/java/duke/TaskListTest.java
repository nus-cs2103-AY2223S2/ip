package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    private final TaskList tl = new TaskList();

    @Test
    public void testFind() {
        tl.addTodo("abc");
        tl.addTodo("123");
        TaskList list = tl.find("1");
        assertEquals(list.getList().get(0).desc, "123");
        assertEquals(list.getList().size(), 1);
    }
}
