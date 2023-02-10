package duke;

import duke.commands.tasks.Task;
import duke.commands.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void add() {
        TaskList tasks = new TaskList();
        Task toAdd = new ToDo("go to class");
        tasks.add(toAdd);
        assertEquals(toAdd.toString(), tasks.get(0).toString());
    }

    @Test
    void remove() {
    }

    @Test
    void size() {
    }

    @Test
    void get() {
    }

    @Test
    void testToString() {
    }
}