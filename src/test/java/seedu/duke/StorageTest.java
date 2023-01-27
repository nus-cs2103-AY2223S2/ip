package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    /**
     * Testing script for reading save file with empty input
     */
    @Test
    public void readFile_emptyInput_emptyTaskList(){
        Storage storage = new Storage("duke.txt");
        TaskList taskList = new TaskList();
        try {
            TaskList storageTaskList = storage.readFile();
            assertEquals(taskList, storageTaskList);
        } catch (DukeException err) {
            fail();
        }
    }

    /**
     * Testing script for reading save file with three inputs
     */
    @Test
    public void writeAndReadFile_threeInputs_taskListWithThreeTasks(){
        Storage storage = new Storage("duke.txt");
        TaskList taskList = new TaskList();
        try {
            LocalDate date = LocalDate.of(2023, 1, 23);
            LocalTime time = LocalTime.of(16,30);
            taskList = taskList.addTask(new Todo("borrow book"));
            taskList = taskList.addTask(new Deadline("borrow book", LocalDateTime.of(date, time)));
            taskList = taskList.addTask(new Event("borrow book",
                    LocalDateTime.of(date, time), LocalDateTime.of(date, time)));
            storage.writeFile(taskList);
            assertEquals(taskList, storage.readFile());
        } catch (DukeException err) {
            fail();
        }
    }
}
