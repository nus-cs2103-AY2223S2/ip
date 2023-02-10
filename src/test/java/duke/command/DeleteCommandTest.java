package duke.command;

import duke.storage.Storage;
import duke.storage.StubStorage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.StubUi;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DeleteCommandTest {
    @Test
    public void run_threeItemTaskListDeleteFirst_firstTaskDeleted() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1", "place 1"));
        tasks.add(new ToDo("task 2", "place 2"));
        tasks.add(new ToDo("task 3", "place 3"));

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        DeleteCommand command = new DeleteCommand();

        String[] args = { "1" };

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        Assertions.assertEquals(2, taskList.getTotalTasks());
        Assertions.assertEquals("task 2", taskList.getTask(1).getDescription());
    }

    @Test
    public void run_threeItemTaskListDeleteMiddle_middleTaskDeleted() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1", "place 1"));
        tasks.add(new ToDo("task 2", "place 2"));
        tasks.add(new ToDo("task 3", "place 3"));

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        DeleteCommand command = new DeleteCommand();

        String[] args = { "2" };

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        Assertions.assertEquals(2, taskList.getTotalTasks());
        Assertions.assertEquals("task 3", taskList.getTask(2).getDescription());
    }

    @Test
    public void run_threeItemTaskListMarkDelete_lastTaskDeleted() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1", "place 1"));
        tasks.add(new ToDo("task 2", "place 2"));
        tasks.add(new ToDo("task 3", "place 3"));

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        DeleteCommand command = new DeleteCommand();

        String[] args = { "3" };

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        Assertions.assertEquals(2, taskList.getTotalTasks());
        Assertions.assertEquals("task 2", taskList.getTask(2).getDescription());
    }
}
