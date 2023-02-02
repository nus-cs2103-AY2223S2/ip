package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_commandBye() {
        assertEquals(new Command("bye"),
                new Parser().parseCommand("bye"));
    }
    @Test
    public void parseCommand_commandList() {
        assertEquals(new Command("list"),
                new Parser().parseCommand("list"));
    }
    @Test
    public void parseCommand_markValidInput() {
        assertEquals(new Command("mark", "12"),
                new Parser().parseCommand("mark 12"));
    }
    @Test
    public void parseCommand_markInvalidArgument() {
        assertEquals(new Command("invalid",
                        "Please enter one task which you would like to mark as done.\n"),
                new Parser().parseCommand("mark cxvx"));
    }
    @Test
    public void parseCommand_markNoArgument() {
        assertEquals(new Command("invalid",
                        "Please enter one task which you would like to mark as done.\n"),
                new Parser().parseCommand("mark"));
    }
    @Test
    public void parseCommand_unmarkValidInput() {
        assertEquals(new Command("unmark", "12"),
                new Parser().parseCommand("unmark 12"));
    }
    @Test
    public void parseCommand_unmarkInvalidArgument() {
        assertEquals(new Command("invalid",
                        "Please enter one task which you would like to mark as undone.\n"),
                new Parser().parseCommand("unmark 345f"));
    }
    @Test
    public void parseCommand_unmarkNoArgument() {
        assertEquals(new Command("invalid",
                        "Please enter one task which you would like to mark as undone.\n"),
                new Parser().parseCommand("unmark"));
    }
    @Test
    public void parseCommand_todoValidInput() {
        assertEquals(new Command("todo", "Get some sleep"),
                new Parser().parseCommand("todo Get some sleep"));
    }
    @Test
    public void parseCommand_todoNoInput() {
        assertEquals(new Command("invalid",
                        "Please enter the task you would like to do in the format \n>> todo [task]\n"),
                new Parser().parseCommand("todo"));
    }
    @Test
    public void parseCommand_todoNoSpaceInput() {
        assertEquals(new Command("invalid",
                        "Please enter the task you would like to do in the format \n>> todo [task]\n"),
                new Parser().parseCommand("todoget some sleep"));
    }
    @Test
    public void parseCommand_deadlineValidInput() {
        assertEquals(new Command("deadline", "homework", "4pm"),
                new Parser().parseCommand("deadline homework /by 4pm"));
    }
    @Test
    public void parseCommand_deadlineNoInput() {
        assertEquals(new Command("invalid",
                        "Sorry, that command is invalid. Specify a deadline task with \n" +
                                ">> deadline [description] /by [time]\n"),
                new Parser().parseCommand("deadline"));
    }
    @Test
    public void parseCommand_deadlineNoArgumentBy() {
        assertEquals(new Command("invalid",
                        "Sorry, that command is invalid. Specify a deadline task with \n" +
                                ">> deadline [description] /by [time]\n"),
                new Parser().parseCommand("deadline do homework"));
    }
    @Test
    public void parseCommand_deadlineNoTask() {
        assertEquals(new Command("invalid",
                        "Sorry, that command is invalid. Specify a deadline task with \n" +
                                ">> deadline [description] /by [time]\n"),
                new Parser().parseCommand("deadline /by tomorrow"));
    }
    @Test
    public void parseCommand_eventValidInput() {
        assertEquals(new Command("event", "AnimeFest2023", "June", "July"),
                new Parser().parseCommand("event AnimeFest2023 /from June /to July"));
    }
    @Test
    public void parseCommand_eventNoInput() {
        assertEquals(new Command("invalid",
                        "Sorry, that command is invalid. Specify an event task with \n" +
                                ">> event [description] /from [start time] /to [end time]\n"),
                new Parser().parseCommand("event"));
    }
    @Test
    public void parseCommand_eventNoArgumentTo() {
        assertEquals(new Command("invalid",
                        "Sorry, that command is invalid. Specify an event task with \n" +
                                ">> event [description] /from [start time] /to [end time]\n"),
                new Parser().parseCommand("event AnimeFest2023 /from today"));
    }
    @Test
    public void parseCommand_eventNoArgumentFrom() {
        assertEquals(new Command("invalid",
                        "Sorry, that command is invalid. Specify an event task with \n" +
                        ">> event [description] /from [start time] /to [end time]\n"),
                new Parser().parseCommand("event AnimeFest2023 /to next year"));
    }
    @Test
    public void parseCommand_eventNoTask() {
        assertEquals(new Command("invalid",
                        "Sorry, that command is invalid. Specify an event task with \n" +
                                ">> event [description] /from [start time] /to [end time]\n"),
                new Parser().parseCommand("event /from today /to next year"));
    }

    @Test
    public void parseCommand_findValidInput() {
        assertEquals(new Command("find", "stuff"),
                new Parser().parseCommand("find stuff"));
    }
    @Test
    public void parseCommand_findValidInputWithSpaces() {
        assertEquals(new Command("find", "stuff turkey"),
                new Parser().parseCommand("find stuff turkey"));
    }
    @Test
    public void parseCommand_findInvalidInput() {
        assertEquals(new Command("invalid", "What task are you looking for?\n"),
                new Parser().parseCommand("find"));
    }
    @Test
    public void parseCommand_findInvalidInputWithSpace() {
        assertEquals(new Command("invalid", "What task are you looking for?\n"),
                new Parser().parseCommand("find "));
    }

}
