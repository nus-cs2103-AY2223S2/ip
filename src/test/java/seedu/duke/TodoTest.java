package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testUnmarkedTodo() {
        TaskList taskList = new TaskList();
        Todo.runTodo(taskList, "yeet");
        assertEquals("[T][ ] yeet", taskList.get(0).toString());
    }

    @Test
    public void testMarkedTodo() {
        TaskList taskList = new TaskList();
        Todo.runTodo(taskList, "eat some bread");
        TaskList.markTask(taskList, "1");
        assertEquals("[T][X] eat some bread", taskList.get(0).toString());
    }
}
