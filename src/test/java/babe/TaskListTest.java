package babe;


import babe.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    // @@author ShanHng-reused
    // Code snippet taken from https://www.baeldung.com/java-testing-system-out-println.
    // Article title: Unit Testing of System.out.println() with JUni

    private final PrintStream standardOutput = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // @@author

    @Test
    /**
     * A unit test for TaskList#addToDo(String, boolean) method
     */
    public void addToDoTest_toNotifyIsFalse_outputNotPrinted() {
        Task task = taskList.addToDo("test", false);
        assertEquals("T|0|test", task.toSaveFormat());
        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    /**
     * A unit test for TaskList#addToDo(String, boolean) method
     */
    public void addToDoTest_toNotifyIsTrue_outputPrinted() {
        Task task = taskList.addToDo("test", true);
        Storage.deleteSaveFile();
        assertEquals("T|0|test", task.toSaveFormat());
        assertEquals("(ɔ˘ ³˘)ɔ: Got it, babe. Added this for you:" + "[ ] test" +
                "Now you have 1 task in the list.",
                outputStreamCaptor.toString().trim().replace("\r\n", ""));
    }

}
