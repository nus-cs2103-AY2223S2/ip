package duke.command;

import duke.storage.Storage;
import duke.storage.StubStorage;
import duke.task.*;
import duke.ui.StubUi;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ListCommandTest {
    @Test
    public void run_threeItemsTaskList_listAllThreeItems() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("description 1", "place 1"));
        tasks.add(new ToDo("description 2", "place 2"));
        tasks.add(new ToDo("description 3", "place 3"));

        String[] args = new String[] { };

        StubUi ui = new StubUi();
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        ListCommand command = new ListCommand();

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        // Check if the tasks are listed correctly in order.
        for (int i = 0; i < tasks.size(); i++) {
            // UI buffer starts at index 1 because the "Here are your tasks in you list, Sir:"
            // is printed first.
            Assertions.assertEquals(
                    String.format("%d. %s", i + 1, tasks.get(i)),
                    ui.getBuffer().get(i + 1)
            );
        }
    }
}
