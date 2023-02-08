package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.storage.TaskList;

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
