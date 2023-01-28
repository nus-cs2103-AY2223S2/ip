package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddEventCommandTest {

    TaskList tasks;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList();
    }

    @Test
    public void eventTest() {
        Command addEventTest = new AddEventCommand("event testing /from 2023-01-27 /to 2023-01-29");
        addEventTest.execute(tasks);
        String expected = "[E][ ] testing (from: Jan 27 2023 to: Jan 29 2023)";
        assertEquals(expected, tasks.getTask(0).toString());
    }
}
