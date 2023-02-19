package treebot.utils;

import org.junit.jupiter.api.Test;
import treebot.commands.Command;
import treebot.exception.TreeBotException;
import treebot.interfaces.ITaskList;
import treebot.tasks.TaskListStubForSingleTask;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {


    @Test
    void todo_emptyTaskDescription_exceptionThrown() {
        String input = "todo";
        Exception e = assertThrows(TreeBotException.class,
                () -> new Parser().parse(input));
        assertEquals("Todo task description cannot be empty", e.getMessage());

    }
    @Test
    void todo_trailingWhiteSpaceExceptionThrown() {
        String input = "todo ";
        Exception e = assertThrows(TreeBotException.class,
                () -> new Parser().parse(input));
        assertEquals("Todo task description cannot be empty", e.getMessage());
    }

    @Test
    void deadline_emptyTaskDescription_exceptionThrown() {
        String input = "deadline";
        Exception e = assertThrows(TreeBotException.class,
                () -> new Parser().parse(input));
        assertEquals("Deadline task description cannot be empty", e.getMessage());
    }

    @Test
    void deadline_trailingWhiteSpace_exceptionThrown() {
        String input = "deadline ";
        Exception e = assertThrows(TreeBotException.class, () -> new Parser().parse(input));
        assertEquals("Deadline task description cannot be empty", e.getMessage());
    }

    @Test
    void deadline_deadlineNotSpecified_exceptionThrown() {
        String input = "deadline some task";
        Exception e = assertThrows(TreeBotException.class,
                () -> new Parser().parse(input));
        assertEquals("Deadline must be specified", e.getMessage());

        String input2 = "deadline some task /byt 05/07/1999 1800";
        Exception e2 = assertThrows(TreeBotException.class,
                () -> new Parser().parse(input));
        assertEquals("Deadline must be specified", e2.getMessage());

    }
    @Test
    void deadline_invalidDateTimeFormatGiven_exceptionThrown() {
        String input = "deadline some task /by 5 july";
        Exception e2 = assertThrows(TreeBotException.class,
                () -> new Parser().parse(input));
        assertEquals("Invalid date time format", e2.getMessage());
    }

    @Test
    void event_emptyTaskDescription_exceptionThrown() {
        String input = "event";
        Exception e = assertThrows(TreeBotException.class,
                () -> new Parser().parse(input));
        assertEquals("Event task description cannot be empty", e.getMessage());
    }

    @Test
    void event_trailingWhiteSpace_exceptionThrown() {
        String input = "event ";
        Exception e = assertThrows(TreeBotException.class, () -> new Parser().parse(input));
        assertEquals("Event task description cannot be empty", e.getMessage());
    }

    @Test
    void event_dateNotSpecified_exceptionThrown() {
        String input = "event some task";
        Exception e = assertThrows(TreeBotException.class,
                () -> new Parser().parse(input));
        assertEquals("Event task datetime range is not valid", e.getMessage());

    }
    @Test
    void event_startDateNotSpecified_exceptionThrown() {
        String input = "event some task /to 5/7/1999 1800";
        Exception e = assertThrows(TreeBotException.class,
                () -> new Parser().parse(input));
        assertEquals("Event task datetime range is not valid", e.getMessage());

    }

    @Test
    void event_endDateNotSpecified_exceptionThrown() {
        String input = "event some task /from 5/7/1999 1800";
        Exception e = assertThrows(TreeBotException.class,
                () -> new Parser().parse(input));
        assertEquals("Event task datetime range is not valid", e.getMessage());
    }


    @Test
    void event_invalidDateTimeFormatGiven_exceptionThrown() {
        String input = "event some task /from 5 July /to 6 July";
        Exception e2 = assertThrows(TreeBotException.class,
                () -> new Parser().parse(input));
        assertEquals("Invalid date time format", e2.getMessage());
    }

    @Test
    void todo_normalInput_writtenCorrectly() {
        String input = "todo homework";
        try {
            ITaskList testTaskList = new TaskListStubForSingleTask();
            Command c = new Parser().parse(input);
            c.injectContext(testTaskList, new StorageStub(), null);
            c.execute();
            assertEquals(testTaskList.getPrintableTasks(), "[T][] homework");

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void deadline_normalInput_writtenCorrectly() {
        String input = "deadline do homework /by 5/7/1999 1800";
        try {
            ITaskList testTaskList = new TaskListStubForSingleTask();
            Command c = new Parser().parse(input);
            c.injectContext(testTaskList, new StorageStub(), null);
            c.execute();
            assertEquals(testTaskList.getPrintableTasks(), "[D][] do homework (by: Jul 5 1999 1800)");

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void event_normalInput_writtenCorrectly() {
        String input = "event project meeting /from 5/7/1999 1800 /to 5/7/1999 1900";
        try {
            ITaskList testTaskList = new TaskListStubForSingleTask();
            Command c = new Parser().parse(input);
            c.injectContext(testTaskList, new StorageStub(), null);
            c.execute();
            assertEquals(testTaskList.getPrintableTasks(), "[E][] project meeting (from: Jul 5 1999 1800 to: Jul 5 1999 1900)");

        } catch (Exception e) {
            fail();
        }
    }




}
