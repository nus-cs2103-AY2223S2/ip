package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TaskListTest {
    private final String description = "Hello World";
    private final TaskList tasks = new TaskList();

    @Test
    public void getTasks() {
        // A unit test for TaskList#getTasks

        // automatically verify the response
        assertEquals(tasks.getTasks(),
                new ArrayList<Task>());
    }

    @Test
    public void size() {
        // A unit test for TaskList#size

        // automatically verify the response
        assertEquals(tasks.size(), 0);
    }

    @Test
    public void add() {
        // A unit test for TaskList#add
        // test setup
        Task task = new Task(description);
        tasks.add(task);

        // automatically verify the response
        assertEquals(tasks.getTasks().get(0), task);
    }

    @Test
    public void delete() throws DukeException {
        // A unit test for TaskList#delete
        // test setup
        Task task = new Task(description);
        tasks.add(task);

        // automatically verify the response
        assertEquals(tasks.delete(1), task);
    }

    @Test
    public void find() throws DukeException {
        // A unit test for TaskList#find
        // test setup
        Task task = new Task(description);
        String newDescription = description.substring(description.length() / 2);

        // automatically verify the response
        assertEquals(tasks.find(description).size(), 0);
        tasks.add(task);
        assertEquals(tasks.find(description).size(), 1);
        assertEquals(tasks.find(newDescription).size(), 1);
    }

    @Test
    public void mark() throws DukeException {
        // A unit test for TaskList#mark
        // test setup
        Task task = new Task(description);
        tasks.add(task);

        // automatically verify the response
        assertEquals(tasks.mark(1),
                tasks.getTasks().get(0));
    }

    @Test
    public void unmark() throws DukeException {
        // A unit test for TaskList#unmark
        // test setup
        Task task = new Task(description);
        tasks.add(task);
        tasks.mark(1);

        // automatically verify the response
        assertEquals(tasks.unmark(1),
                tasks.getTasks().get(0));
    }
}
