package duke.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exceptions.DukeInvalidDeadlineCommandException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeadlineCommandTest {

    TaskList taskList;
    Ui ui;
    Storage storage;

    @BeforeEach
    public void beforeEach() {
        File file = new File("");

        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage(file.toString());
    }

    @Test
    public void execute_noTaskNameNoByNoDeadline_DukeInvalidDeadlineCommandExceptionThrown() {
        assertThrows(DukeInvalidDeadlineCommandException.class, () -> {
            String[] tokens = {"deadline"};
            DeadlineCommand deadlineCommand = new DeadlineCommand(tokens);
            deadlineCommand.execute(taskList, ui, storage);
        });
    }

    @Test
    public void execute_noTaskNameNoBy_DukeInvalidDeadlineCommandExceptionThrown() {
        assertThrows(DukeInvalidDeadlineCommandException.class, () -> {
            String[] tokens = {"deadline", "2021-04-06"};
            DeadlineCommand deadlineCommand = new DeadlineCommand(tokens);
            deadlineCommand.execute(taskList, ui, storage);
        });
    }

    @Test
    public void execute_noTaskNameNoDeadline_DukeInvalidDeadlineCommandExceptionThrown() {
        assertThrows(DukeInvalidDeadlineCommandException.class, () -> {
            String[] tokens = {"deadline", "/by"};
            DeadlineCommand deadlineCommand = new DeadlineCommand(tokens);
            deadlineCommand.execute(taskList, ui, storage);
        });
    }

    @Test
    public void execute_noTaskName_DukeInvalidDeadlineCommandExceptionThrown() {
        assertThrows(DukeInvalidDeadlineCommandException.class, () -> {
            String[] tokens = {"deadline", "/by", "2019-07-03"};
            DeadlineCommand deadlineCommand = new DeadlineCommand(tokens);
            deadlineCommand.execute(taskList, ui, storage);
        });
    }

    @Test
    public void execute_noByNoDeadline_DukeInvalidDeadlineCommandExceptionThrown() {
        assertThrows(DukeInvalidDeadlineCommandException.class, () -> {
            String[] tokens = {"deadline", "abc", "123"};
            DeadlineCommand deadlineCommand = new DeadlineCommand(tokens);
            deadlineCommand.execute(taskList, ui, storage);
        });
    }

    @Test
    public void execute_noBy_DukeInvalidDeadlineCommandExceptionThrown() {
        assertThrows(DukeInvalidDeadlineCommandException.class, () -> {
            String[] tokens = {"deadline", "abc", "123", "2013-09-12"};
            DeadlineCommand deadlineCommand = new DeadlineCommand(tokens);
            deadlineCommand.execute(taskList, ui, storage);
        });
    }

    @Test
    public void execute_noDeadline_DukeInvalidDeadlineCommandExceptionThrown() {
        assertThrows(DukeInvalidDeadlineCommandException.class, () -> {
            String[] tokens = {"deadline", "abc", "123", "/by"};
            DeadlineCommand deadlineCommand = new DeadlineCommand(tokens);
            deadlineCommand.execute(taskList, ui, storage);
        });
    }

    @Test
    public void execute_correctFormat_DukeInvalidDeadlineCommandExceptionNotThrown() {
        assertDoesNotThrow(() -> {
            String[] tokens = {"deadline", "abc", "123", "/by", "2010-03-07"};
            DeadlineCommand deadlineCommand = new DeadlineCommand(tokens);
            deadlineCommand.execute(taskList, ui, storage);
        });
    }
}
