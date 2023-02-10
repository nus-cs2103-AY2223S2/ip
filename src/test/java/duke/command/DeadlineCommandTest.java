package duke.command;

import duke.storage.Storage;
import duke.storage.StubStorage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.ui.StubUi;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DeadlineCommandTest {
    @Test
    public void run_emptyTaskList_addDeadlineToTaskList() {
        String description = "deadline description";
        String place = "deadline place";
        String by = "13/10/1999 13:00";
        String[] args = { description, place, by };

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        DeadlineCommand command = new DeadlineCommand();

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        Assertions.assertEquals(1, taskList.getTotalTasks());
        Assertions.assertEquals(TaskType.DEADLINE, taskList.getTask(1).getTypeOfTask());
        Assertions.assertEquals(description, taskList.getTask(1).getDescription());

        LocalDateTime byDateTime = ((Deadline) taskList.getTask(1)).getBy();
        Assertions.assertEquals(13, byDateTime.getDayOfMonth());
        Assertions.assertEquals(10, byDateTime.getMonthValue());
        Assertions.assertEquals(1999, byDateTime.getYear());
        Assertions.assertEquals(13, byDateTime.getHour());
        Assertions.assertEquals(0, byDateTime.getMinute());
    }
}
