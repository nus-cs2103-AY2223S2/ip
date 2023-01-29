package duke.command;

import duke.storage.Storage;
import duke.storage.StubStorage;
import duke.task.*;
import duke.ui.StubUi;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MarkCommandTest {
    @Test
    public void run_threeItemTaskListMarkFirst_firstTaskMarkDone() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1"));
        tasks.add(new ToDo("task 2"));
        tasks.add(new ToDo("task 3"));

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        MarkCommand command = new MarkCommand();

        String[] args = { "1" };

        Assertions.assertDoesNotThrow(() -> { command.run(args, ui, taskList, storage); });

        Assertions.assertEquals(true, taskList.getTask(1).isDone());
    }

    @Test
    public void run_threeItemTaskListMarkMiddle_middleTaskMarkDone() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1"));
        tasks.add(new ToDo("task 2"));
        tasks.add(new ToDo("task 3"));

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        MarkCommand command = new MarkCommand();

        String[] args = { "2" };

        Assertions.assertDoesNotThrow(() -> { command.run(args, ui, taskList, storage); });

        Assertions.assertEquals(true, taskList.getTask(2).isDone());
    }

    @Test
    public void run_threeItemTaskListMarkLast_lastTaskMarkDone() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1"));
        tasks.add(new ToDo("task 2"));
        tasks.add(new ToDo("task 3"));

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        MarkCommand command = new MarkCommand();

        String[] args = { "3" };

        Assertions.assertDoesNotThrow(() -> { command.run(args, ui, taskList, storage); });

        Assertions.assertEquals(true, taskList.getTask(3).isDone());
    }
}
