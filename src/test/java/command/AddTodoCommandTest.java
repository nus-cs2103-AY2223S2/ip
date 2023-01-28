package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTodoCommandTest {

    TaskList tasks;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList();
    }

    @Test
    public void todoTest() {
        Command addTodoTest = new AddTodoCommand("todo testing");
        addTodoTest.execute(tasks);
        String expected = "[T][ ] testing";
        assertEquals(expected, tasks.getTask(0).toString());
    }
}
