package classes;

import exceptions.IncorrectNoOfArgumentException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import java.io.IOException;

public class DukeTest {

    @Test
    public static void main(String[] args) throws IOException {
        new Duke("data/storage.txt", "data").run();
    }

    @Test
    public void run() throws IOException {
        ArrayList<String> actualCommandInfo = Parser.parse("event Birthday /from 2012-12-22 /to 2012-12-23");
        ArrayList<String> expectedCommandInfo = new ArrayList<>();
        expectedCommandInfo.add("event");
        expectedCommandInfo.add("Birthday ");
        expectedCommandInfo.add("2012-12-22");
        expectedCommandInfo.add("");
        expectedCommandInfo.add("2012-12-23");
        expectedCommandInfo.add("");
        assertEquals(expectedCommandInfo, actualCommandInfo);
        System.out.println("Passed 1/4 checks of parsing task 'event': Duke.run()");

        ArrayList<String> actualCommandInfo2 = Parser.parse("event Birthday /from 2012-12-22 15:00 /to 2012-12-23");
        ArrayList<String> expectedCommandInfo2 = new ArrayList<>();
        expectedCommandInfo2.add("event");
        expectedCommandInfo2.add("Birthday ");
        expectedCommandInfo2.add("2012-12-22");
        expectedCommandInfo2.add("15:00");
        expectedCommandInfo2.add("2012-12-23");
        expectedCommandInfo2.add("");
        assertEquals(expectedCommandInfo2, actualCommandInfo2);
        System.out.println("Passed 2/4 checks of parsing task 'event': Duke.run()");

        ArrayList<String> actualCommandInfo3 = Parser.parse("event Birthday /from 2012-12-22 /to 2012-12-23 16:00");
        ArrayList<String> expectedCommandInfo3 = new ArrayList<>();
        expectedCommandInfo3.add("event");
        expectedCommandInfo3.add("Birthday ");
        expectedCommandInfo3.add("2012-12-22");
        expectedCommandInfo3.add("");
        expectedCommandInfo3.add("2012-12-23");
        expectedCommandInfo3.add("16:00");
        assertEquals(expectedCommandInfo3, actualCommandInfo3);
        System.out.println("Passed 3/4 checks of parsing task 'event': Duke.run()");

        ArrayList<String> actualCommandInfo4 = Parser.parse("event Birthday /from 2012-12-22 15:00 /to 2012-12-23 16:00");
        ArrayList<String> expectedCommandInfo4 = new ArrayList<>();
        expectedCommandInfo4.add("event");
        expectedCommandInfo4.add("Birthday ");
        expectedCommandInfo4.add("2012-12-22");
        expectedCommandInfo4.add("15:00");
        expectedCommandInfo4.add("2012-12-23");
        expectedCommandInfo4.add("16:00");
        assertEquals(expectedCommandInfo4, actualCommandInfo4);
        System.out.println("Passed 4/4 checks of parsing task 'event': Duke.run()");

        Storage storage = new Storage("data/storageTest(delete before each test).txt", "data");
        TaskList tasks = new TaskList(storage.load());
        String actualCommandOutput = "    ____________________________________________________________\n" +
                "    Got it. I've added this task to the list:\n" +
                "      [E][ ] Birthday (from: December 22, 2012 | 3:00:00 PM to: December 23, 2012 | 4:00:00 PM)\n" +
                "    Now you have 1 tasks in the list.\n" +
                "    ____________________________________________________________\n";
        assertEquals(actualCommandOutput, tasks.addTask(actualCommandInfo4, storage));
        System.out.println("Passed 1/1 check of creating task 'event': Duke.run()");
    }

}