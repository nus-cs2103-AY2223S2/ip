package duke.command;

import duke.storage.Storage;
import duke.storage.StubStorage;
import duke.task.TaskType;
import duke.ui.StubUi;
import duke.ui.Ui;
import duke.task.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ToDoCommandTest {
    @Test
    public void run_emptyTaskList_addTodoToTaskList() {
        String description = "todo description";
        String place = "todo place";
        String[] args = { description, place };

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        ToDoCommand command = new ToDoCommand();

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        Assertions.assertEquals(1, taskList.getTotalTasks());
        Assertions.assertEquals(TaskType.TODO, taskList.getTask(1).getTypeOfTask());
        Assertions.assertEquals(description, taskList.getTask(1).getDescription());
    }
}
