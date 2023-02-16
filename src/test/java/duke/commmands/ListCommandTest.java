package duke.commmands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.TaskList;
import duke.commands.ListCommand;
import duke.tasks.TodoTask;

public class ListCommandTest {
    static final String NORMAL_INPUT = "list";
    static final String SIMPLE_TASK_NAME = "simpleTask";
    static final String CORRECT_TASK_OUTPUT = "Current tasks in list:\n1. [Todo] [ ] simpleTask";

    @Test
    void execute_simpleInput_outputsCorrectly() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TodoTask(SIMPLE_TASK_NAME));

        ListCommand listCommand = new ListCommand(NORMAL_INPUT);
        assertEquals(CORRECT_TASK_OUTPUT, listCommand.execute(tasks));
    }
}
