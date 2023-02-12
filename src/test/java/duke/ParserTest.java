package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.IncorrectNoOfArgumentException;

/**
 * Represents the test class for Parser.java.
 *
 * @author MrTwit99
 * @since 2023-02-13
 */
public class ParserTest {

    private ArrayList<String> testCmd = new ArrayList<>();
    private IncorrectNoOfArgumentException e;
    private String testErrorMessage;

    /**
     * Tests the methods parse(), parse2() and eventParser() in Parser.java for events without time.
     */
    @Test
    public void eventParserTest1() {
        testCmd.add("event");
        testCmd.add("Birthday ");
        testCmd.add("2012-12-13");
        testCmd.add("");
        testCmd.add("2013-01-13");
        testCmd.add("");
        assertEquals(testCmd, Parser.parse("event Birthday /from 2012-12-13 /to 2013-01-13"));
    }

    /**
     * Tests the methods parse(), parse2() and eventParser() in Parser.java for events with time.
     */
    @Test
    public void eventParserTest2() {
        testCmd.add("event");
        testCmd.add("Birthday ");
        testCmd.add("2012-12-13");
        testCmd.add("15:00");
        testCmd.add("2013-01-13");
        testCmd.add("16:00");
        assertEquals(testCmd, Parser.parse("event Birthday /from 2012-12-13 15:00 /to 2013-01-13 16:00"));
    }

    /**
     * Tests the methods parse(), parse2() and eventParser() in Parser.java for events with startTime.
     */
    @Test
    public void eventParserTest3() {
        testCmd.add("event");
        testCmd.add("Birthday ");
        testCmd.add("2012-12-13");
        testCmd.add("15:00");
        testCmd.add("2013-01-13");
        testCmd.add("");
        assertEquals(testCmd, Parser.parse("event Birthday /from 2012-12-13 15:00 /to 2013-01-13"));
    }

    /**
     * Tests the methods parse(), parse2() and eventParser() in Parser.java for events with endTime.
     */
    @Test
    public void eventParserTest4() {
        testCmd.add("event");
        testCmd.add("Birthday ");
        testCmd.add("2012-12-13");
        testCmd.add("");
        testCmd.add("2013-01-13");
        testCmd.add("16:00");
        assertEquals(testCmd, Parser.parse("event Birthday /from 2012-12-13 /to 2013-01-13 16:00"));
    }

    /**
     * Tests the methods parse(), parse2() and eventParser() in Parser.java when there's insufficent arguments given for
     * event commands.
     */
    @Test
    public void eventParserTest5() {
        testErrorMessage = "\n    ____________________________________________________________________________________"
                + "\n     ☹ OOPS!!! You have provided incorrect number of arguments for the command 'event'"
                + ".\n     Please try again after checking!\n"
                + "    ____________________________________________________________________________________\n";
        e = new IncorrectNoOfArgumentException(testErrorMessage);
        testCmd.add(e.getMessage());
        assertEquals(testCmd, Parser.parse("event"));
        assertEquals(testCmd, Parser.parse("event "));
        assertEquals(testCmd, Parser.parse("event Birthday"));
        assertEquals(testCmd, Parser.parse("event Birthday "));
        assertEquals(testCmd, Parser.parse("event Birthday /from"));
        assertEquals(testCmd, Parser.parse("event Birthday /from "));
        assertEquals(testCmd, Parser.parse("event Birthday /from /to"));
        assertEquals(testCmd, Parser.parse("event Birthday /from /to "));
        assertEquals(testCmd, Parser.parse("event Birthday /from 2012-12-13 /to"));
        assertEquals(testCmd, Parser.parse("event Birthday /from 2012-12-13 /to"));
        assertEquals(testCmd, Parser.parse("event Birthday /from 2012-12-13 /to "));
        assertEquals(testCmd, Parser.parse("event Birthday /from /to 2012-12-14"));
    }

    /**
     * Tests the methods parse(), parse2() and deadlineParser() in Parser.java for events without time.
     */
    @Test
    public void deadlineTest() {
        testCmd.add("deadline");
        testCmd.add("Assignment1 ");
        testCmd.add("2012-12-13");
        testCmd.add("");
        assertEquals(testCmd, Parser.parse("deadline Assignment1 /by 2012-12-13"));
    }

    /**
     * Tests the methods parse(), parse2() and deadlineParser() in Parser.java for deadlines with time.
     */
    @Test
    public void deadlineTest2() {
        testCmd.add("deadline");
        testCmd.add("Assignment1 ");
        testCmd.add("2012-12-13");
        testCmd.add("15:00");
        assertEquals(testCmd, Parser.parse("deadline Assignment1 /by 2012-12-13 15:00"));
    }

    /**
     * Tests the methods parse(), parse2() and deadlineParser() in Parser.java when there's insufficent arguments given
     * for deadline commands.
     */
    @Test
    public void deadlineTest3() {
        testErrorMessage = "\n    ____________________________________________________________________________________"
                + "\n     ☹ OOPS!!! You have provided incorrect number of arguments for the command 'deadline'"
                + ".\n     Please try again after checking!\n"
                + "    ____________________________________________________________________________________\n";
        e = new IncorrectNoOfArgumentException(testErrorMessage);
        testCmd.add(e.getMessage());
        assertEquals(testCmd, Parser.parse("deadline"));
        assertEquals(testCmd, Parser.parse("deadline "));
        assertEquals(testCmd, Parser.parse("deadline Assignment1"));
        assertEquals(testCmd, Parser.parse("deadline Assignment1 "));
        assertEquals(testCmd, Parser.parse("deadline Assignment1 /by"));
        assertEquals(testCmd, Parser.parse("deadline Assignment1 /by "));
    }

    /**
     * Tests the methods parse() and parse2() in Parser.java for ToDo commands.
     */
    @Test
    public void toDoTest() {
        testCmd.add("todo");
        testCmd.add("sleep");
        assertEquals(testCmd, Parser.parse("todo sleep"));
    }

    /**
     * Tests the methods parse() and parse2() in Parser.java when there's insufficent arguments given for ToDo commands.
     */
    @Test
    public void toDoTest2() {
        testErrorMessage = "\n    ____________________________________________________________________________________"
                + "\n     ☹ OOPS!!! You have provided incorrect number of arguments for the command 'todo'"
                + ".\n     Please try again after checking!\n"
                + "    ____________________________________________________________________________________\n";
        e = new IncorrectNoOfArgumentException(testErrorMessage);
        testCmd.add(e.getMessage());
        assertEquals(testCmd, Parser.parse("todo"));
        assertEquals(testCmd, Parser.parse("todo "));
    }

    /**
     * Tests the methods parse() and parse2() in Parser.java for parsing list command.
     */
    @Test
    public void listCmdTest() {
        testCmd.add("list");
        assertEquals(testCmd, Parser.parse("list"));
        testErrorMessage = "\n    ____________________________________________________________________________________"
                + "\n     ☹ OOPS!!! You have provided incorrect number of arguments for the command 'list'"
                + ".\n     Please try again after checking!\n"
                + "    ____________________________________________________________________________________\n";
        e = new IncorrectNoOfArgumentException(testErrorMessage);
        testCmd.clear();
        testCmd.add(e.getMessage());
        assertEquals(testCmd, Parser.parse("list 2"));
    }

    /**
     * Tests the methods parse() and parse2() in Parser.java for parsing reminder command.
     */
    @Test
    public void reminderCmdTest() {
        testCmd.add("reminder");
        assertEquals(testCmd, Parser.parse("reminder"));
        testErrorMessage = "\n    ____________________________________________________________________________________"
                + "\n     ☹ OOPS!!! You have provided incorrect number of arguments for the command 'reminder'"
                + ".\n     Please try again after checking!\n"
                + "    ____________________________________________________________________________________\n";
        e = new IncorrectNoOfArgumentException(testErrorMessage);
        testCmd.clear();
        testCmd.add(e.getMessage());
        assertEquals(testCmd, Parser.parse("reminder 2"));
    }

    /**
     * Tests the methods parse() and parse2() in Parser.java for parsing bye command.
     */
    @Test
    public void byeCmdTest() {
        testCmd.add("bye");
        assertEquals(testCmd, Parser.parse("bye"));
        testErrorMessage = "\n    ____________________________________________________________________________________"
                + "\n     ☹ OOPS!!! You have provided incorrect number of arguments for the command 'bye'"
                + ".\n     Please try again after checking!\n"
                + "    ____________________________________________________________________________________\n";
        e = new IncorrectNoOfArgumentException(testErrorMessage);
        testCmd.clear();
        testCmd.add(e.getMessage());
        assertEquals(testCmd, Parser.parse("bye 2"));
    }

    /**
     * Tests the methods parse() and parse2() in Parser.java for parsing find command.
     */
    @Test
    public void findCmdTest() {
        testCmd.add("find");
        testCmd.add("sleep");
        assertEquals(testCmd, Parser.parse("find sleep"));
        testErrorMessage = "\n    ____________________________________________________________________________________"
                + "\n     ☹ OOPS!!! You have provided incorrect number of arguments for the command 'find'"
                + ".\n     Please try again after checking!\n"
                + "    ____________________________________________________________________________________\n";
        e = new IncorrectNoOfArgumentException(testErrorMessage);
        testCmd.clear();
        testCmd.add(e.getMessage());
        assertEquals(testCmd, Parser.parse("find"));
    }
}
