package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_commandBye() {
        assertEquals(new Command("bye", new ArrayList<>()),
                new Parser().parseCommand("bye"));
    }
    @Test
    public void parseCommand_commandList() {
        assertEquals(new Command("list", new ArrayList<>()),
                new Parser().parseCommand("list"));
    }
    @Test
    public void parseCommand_markValidInput() {
        List<String> argument = new ArrayList<>();
        argument.add("12");
        assertEquals(new Command("mark", argument),
                new Parser().parseCommand("mark 12"));
    }
    @Test
    public void parseCommand_markInvalidArgument() {
        List<String> argument = new ArrayList<>();
        argument.add("Please enter one task which you would like to mark as done.\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("mark cxvx"));
    }
    @Test
    public void parseCommand_markNoArgument() {
        List<String> argument = new ArrayList<>();
        argument.add("Please enter one task which you would like to mark as done.\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("mark"));
    }
    @Test
    public void parseCommand_unmarkValidInput() {
        List<String> argument = new ArrayList<>();
        argument.add("12");
        assertEquals(new Command("unmark", argument),
                new Parser().parseCommand("unmark 12"));
    }
    @Test
    public void parseCommand_unmarkInvalidArgument() {
        List<String> argument = new ArrayList<>();
        argument.add("Please enter one task which you would like to mark as undone.\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("unmark 345f"));
    }
    @Test
    public void parseCommand_unmarkNoArgument() {
        List<String> argument = new ArrayList<>();
        argument.add("Please enter one task which you would like to mark as undone.\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("unmark"));
    }
    @Test
    public void parseCommand_todoValidInput() {
        List<String> argument = new ArrayList<>();
        argument.add("Get some sleep");
        assertEquals(new Command("todo", argument),
                new Parser().parseCommand("todo Get some sleep"));
    }
    @Test
    public void parseCommand_todoNoInput() {
        List<String> argument = new ArrayList<>();
        argument.add("Please enter the task you would like to do in the format \n>> todo [task]\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("todo"));
    }
    @Test
    public void parseCommand_todoNoSpaceInput() {
        List<String> argument = new ArrayList<>();
        argument.add("Please enter the task you would like to do in the format \n>> todo [task]\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("todoget some sleep"));
    }
    @Test
    public void parseCommand_deadlineValidInput() {
        List<String> argument = new ArrayList<>();
        argument.add("homework");
        argument.add("4pm");
        assertEquals(new Command("deadline", argument),
                new Parser().parseCommand("deadline homework /by 4pm"));
    }
    @Test
    public void parseCommand_deadlineNoInput() {
        List<String> argument = new ArrayList<>();
        argument.add("Sorry, that command is invalid. Specify a deadline task with \n >> deadline [description] /by [time]\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("deadline"));
    }
    @Test
    public void parseCommand_deadlineNoArgumentBy() {
        List<String> argument = new ArrayList<>();
        argument.add("Sorry, that command is invalid. Specify a deadline task with \n >> deadline [description] /by [time]\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("deadline do homework"));
    }
    @Test
    public void parseCommand_deadlineNoTask() {
        List<String> argument = new ArrayList<>();
        argument.add("Sorry, that command is invalid. Specify a deadline task with \n >> deadline [description] /by [time]\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("deadline /by tomorrow"));
    }
    @Test
    public void parseCommand_eventValidInput() {
        List<String> argument = new ArrayList<>();
        argument.add("AnimeFest2023");
        argument.add("June");
        argument.add("July");
        assertEquals(new Command("event", argument),
                new Parser().parseCommand("event AnimeFest2023 /from June /to July"));
    }
    @Test
    public void parseCommand_eventNoInput() {
        List<String> argument = new ArrayList<>();
        argument.add("Sorry, that command is invalid. Specify an event task with \n >> event [description] /from [start time] /to [end time]\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("event"));
    }
    @Test
    public void parseCommand_eventNoArgumentTo() {
        List<String> argument = new ArrayList<>();
        argument.add("Sorry, that command is invalid. Specify an event task with \n >> event [description] /from [start time] /to [end time]\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("event AnimeFest2023 /from today"));
    }
    @Test
    public void parseCommand_eventNoArgumentFrom() {
        List<String> argument = new ArrayList<>();
        argument.add("Sorry, that command is invalid. Specify an event task with \n >> event [description] /from [start time] /to [end time]\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("event AnimeFest2023 /to next year"));
    }
    @Test
    public void parseCommand_eventNoTask() {
        List<String> argument = new ArrayList<>();
        argument.add("Sorry, that command is invalid. Specify an event task with \n >> event [description] /from [start time] /to [end time]\n");
        assertEquals(new Command("invalid", argument),
                new Parser().parseCommand("event /from today /to next year"));
    }

}
