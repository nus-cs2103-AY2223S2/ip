package duke.command;

import duke.storage.Storage;
import duke.storage.StubStorage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.StubUi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FindCommandTest {
    @Test
    public void run_threeItemsTaskList_findDescriptionListThreeItems() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("description 1", "place 1"));
        tasks.add(new ToDo("description 2", "place 2"));
        tasks.add(new ToDo("description 3", "place 3"));

        String[] args = new String[] { "description" };

        StubUi ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        FindCommand command = new FindCommand();

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        // Check if the tasks are listed correctly in order.
        for (int i = 0; i < tasks.size(); i++) {
            // UI buffer starts at index 1 because the "Here are your tasks in you list, Sir:"
            // is printed first.
            Assertions.assertEquals(
                    tasks.get(i).toString(),
                    ui.getBuffer().get(i + 1)
            );
        }
    }

    @Test
    public void run_threeItemsTaskList_find1ListOneItem() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("description 1", "place 1"));
        tasks.add(new ToDo("description 2", "place 2"));
        tasks.add(new ToDo("description 3", "place 3"));

        String[] args = new String[] { "1" };

        StubUi ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        FindCommand command = new FindCommand();

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        // UI buffer starts at index 1 because the "Here are your tasks in you list, Sir:"
        // is printed first.
        Assertions.assertEquals(tasks.get(0).toString(),
                ui.getBuffer().get(1));
    }
}
