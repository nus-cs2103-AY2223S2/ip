package duke.command;

import duke.storage.Storage;
import duke.storage.StubStorage;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.ui.StubUi;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventCommandTest {
    @Test
    public void run_emptyTaskList_addEventToTaskList() {
        String description = "event description";
        String place = "event place";
        String from = "13/10/1999 13:00";
        String until = "13/10/1999 13:00";
        String[] args = { description, place, from, until };

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        EventCommand command = new EventCommand();

        Assertions.assertDoesNotThrow(() -> { command.run(ui, taskList, storage, args); });

        Assertions.assertEquals(1, taskList.getTotalTasks());
        Assertions.assertEquals(TaskType.EVENT, taskList.getTask(1).getTypeOfTask());
        Assertions.assertEquals(description, taskList.getTask(1).getDescription());

        LocalDateTime byDateTime = ((Event) taskList.getTask(1)).getFrom();
        Assertions.assertEquals(13, byDateTime.getDayOfMonth());
        Assertions.assertEquals(10, byDateTime.getMonthValue());
        Assertions.assertEquals(1999, byDateTime.getYear());
        Assertions.assertEquals(13, byDateTime.getHour());
        Assertions.assertEquals(0, byDateTime.getMinute());

        LocalDateTime toDateTime = ((Event) taskList.getTask(1)).getTo();
        Assertions.assertEquals(13, toDateTime.getDayOfMonth());
        Assertions.assertEquals(10, toDateTime.getMonthValue());
        Assertions.assertEquals(1999, toDateTime.getYear());
        Assertions.assertEquals(13, toDateTime.getHour());
        Assertions.assertEquals(0, toDateTime.getMinute());
    }
}
