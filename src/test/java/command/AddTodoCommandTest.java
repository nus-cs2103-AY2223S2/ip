package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chad.command.AddTodoCommand;
import chad.command.Command;
import chad.storage.TaskList;

public class AddTodoCommandTest {

    private TaskList tasks;

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
