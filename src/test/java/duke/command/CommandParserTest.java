package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.StubStorage;
import duke.task.*;
import duke.ui.StubUi;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommandParserTest {
    @Test
    public void parseInputAndExecuteCommand_todoCommandCorrectInput_addTodoToTaskList() {
        String input = "todo description /place location";

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertDoesNotThrow(() -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });

        Assertions.assertEquals(1, taskList.getTotalTasks());
        Assertions.assertEquals(TaskType.TODO, taskList.getTask(1).getTypeOfTask());
        Assertions.assertEquals("description", taskList.getTask(1).getDescription());
    }

    @Test
    public void parseInputAndExecuteCommand_todoCommandIncorrectInput_dukeException() {
        String input = "todo";

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertThrows(DukeException.class,
                () -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });
    }

    @Test
    public void parseInputAndExecuteCommand_deadlineCommandCorrectInput_addDeadlineToTaskList() {
        String input = "deadline description /place location /by 13/10/1999 13:00";

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertDoesNotThrow(() -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });

        Assertions.assertEquals(1, taskList.getTotalTasks());
        Assertions.assertEquals(TaskType.DEADLINE, taskList.getTask(1).getTypeOfTask());
        Assertions.assertEquals("description", taskList.getTask(1).getDescription());

        LocalDateTime byDateTime = ((Deadline) taskList.getTask(1)).getBy();
        Assertions.assertEquals(13, byDateTime.getDayOfMonth());
        Assertions.assertEquals(10, byDateTime.getMonthValue());
        Assertions.assertEquals(1999, byDateTime.getYear());
        Assertions.assertEquals(13, byDateTime.getHour());
        Assertions.assertEquals(0, byDateTime.getMinute());
    }

    @Test
    public void parseInputAndExecuteCommand_deadlineCommandIncorrectInput_dukeException() {
        String input = "deadline description";

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertThrows(DukeException.class,
                () -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });
    }

    @Test
    public void parseInputAndExecuteCommand_todoCommandIncorrectDateTime_dukeException() {
        String input = "deadline description /place location /by 21/31/2023 13:00";

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertThrows(DukeException.class,
                () -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });
    }

    @Test
    public void parseInputAndExecuteCommand_eventCommandCorrectInput_addEventToTaskList() {
        String input = "event description /place location /from 13/10/1999 13:00 /to 13/10/1999 13:00";

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertDoesNotThrow(() -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });

        Assertions.assertEquals(1, taskList.getTotalTasks());
        Assertions.assertEquals(TaskType.EVENT, taskList.getTask(1).getTypeOfTask());
        Assertions.assertEquals("description", taskList.getTask(1).getDescription());

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

    @Test
    public void parseInputAndExecuteCommand_eventCommandIncorrectInput_dukeException() {
        String input = "event description";

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertThrows(DukeException.class,
                () -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });
    }

    @Test
    public void parseInputAndExecuteCommand_eventCommandIncorrectDateTime_dukeException() {
        String input = "event description /place location /from 21/31/2023 13:00 /to 21/31/2023 13:00";

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertThrows(DukeException.class,
                () -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });
    }

    @Test
    public void parseInputAndExecuteCommand_markCommandCorrectInput_markTaskDone() {
        String input = "mark 1";

        Ui ui = new StubUi();
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1", "place 1"));
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertDoesNotThrow(() -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });

        Assertions.assertEquals(1, taskList.getTotalTasks());
        Assertions.assertEquals(TaskType.TODO, taskList.getTask(1).getTypeOfTask());
        Assertions.assertEquals(true, taskList.getTask(1).isDone());
    }

    @Test
    public void parseInputAndExecuteCommand_markCommandIncorrectInput_dukeException() {
        String input = "mark two";

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertThrows(DukeException.class,
                () -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });
    }

    @Test
    public void parseInputAndExecuteCommand_unmarkCommandCorrectInput_unmarkTaskDone() {
        String input = "unmark 1";

        Ui ui = new StubUi();
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1", "place 1"));
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertDoesNotThrow(() -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });

        Assertions.assertEquals(1, taskList.getTotalTasks());
        Assertions.assertEquals(TaskType.TODO, taskList.getTask(1).getTypeOfTask());
        Assertions.assertEquals(false, taskList.getTask(1).isDone());
    }

    @Test
    public void parseInputAndExecuteCommand_unmarkCommandIncorrectInput_dukeException() {
        String input = "unmark two";

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertThrows(DukeException.class,
                () -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });
    }

    @Test
    public void parseInputAndExecuteCommand_deleteCommandCorrectInput_deleteTaskFromTaskList() {
        String input = "delete 1";

        Ui ui = new StubUi();
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task 1", "place 1"));
        TaskList taskList = new TaskList(tasks);
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertDoesNotThrow(() -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });

        Assertions.assertEquals(0, taskList.getTotalTasks());
    }

    @Test
    public void parseInputAndExecuteCommand_deleteCommandIncorrectInput_dukeException() {
        String input = "delete two";

        Ui ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new StubStorage(new ArrayList<>());
        CommandParser commandParser = new CommandParser();

        Assertions.assertThrows(DukeException.class,
                () -> { commandParser.parseInputAndExecuteCommand(input, ui, taskList, storage); });
    }
}
