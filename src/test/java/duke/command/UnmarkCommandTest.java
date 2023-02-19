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

public class UnmarkCommandTest {
    @Test
    public void run_threeItemTaskListMarkFirst_firstTaskUnmarkDone() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1", "place 1"));
        tasks.add(new ToDo("task 2", "place 2"));
        tasks.add(new ToDo("task 3", "place 3"));
        for (Task task : tasks) {
            task.setIsDone(true);
        }

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        UnmarkCommand command = new UnmarkCommand();

        String[] args = { "1" };

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        Assertions.assertEquals(false, taskList.getTask(1).isDone());
    }

    @Test
    public void run_threeItemTaskListMarkMiddle_middleTaskUnmarkDone() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1", "place 1"));
        tasks.add(new ToDo("task 2", "place 2"));
        tasks.add(new ToDo("task 3", "place 3"));
        for (Task task : tasks) {
            task.setIsDone(true);
        }

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        UnmarkCommand command = new UnmarkCommand();

        String[] args = { "2" };

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        Assertions.assertEquals(false, taskList.getTask(2).isDone());
    }

    @Test
    public void run_threeItemTaskListMarkLast_lastTaskUnmarkDone() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1", "place 1"));
        tasks.add(new ToDo("task 2", "place 2"));
        tasks.add(new ToDo("task 3", "place 3"));
        for (Task task : tasks) {
            task.setIsDone(true);
        }

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        UnmarkCommand command = new UnmarkCommand();

        String[] args = { "3" };

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        Assertions.assertEquals(false, taskList.getTask(3).isDone());
    }
}
