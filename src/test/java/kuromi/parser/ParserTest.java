package kuromi.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import kuromi.command.Command;
import kuromi.exception.KuromiException;
import kuromi.task.Task;
import kuromi.task.TaskList;
import kuromi.view.Ui;

public class ParserTest {
    @Test
    public void byeCommandWrongInput() {
        try {
            Command res = Parser.parse("bye 1", new Ui(), new TaskList(new ArrayList<Task>()));
        } catch (KuromiException e) {
            assertEquals(e.getMessage(), "OOPS!!! I don't understand what you mean :(\nDo you mean 'bye'?");
        }
    }

    @Test
    public void mistakesCommandWrongInput() {
        try {
            Command res = Parser.parse("mistakes 1", new Ui(), new TaskList(new ArrayList<Task>()));
        } catch (KuromiException e) {
            assertEquals(e.getMessage(), "OOPS!!! I don't understand what you mean :(\nDo you mean 'mistakes'?");
        }
    }

    @Test
    public void listCommandWrongInput() {
        try {
            Command res = Parser.parse("list 1", new Ui(), new TaskList(new ArrayList<Task>()));
        } catch (KuromiException e) {
            assertEquals(e.getMessage(), "OOPS!!! I don't understand what you mean :(\nDo you mean 'list'?");
        }
    }

    @Test
    public void remindCommandZero() {
        try {
            Command res = Parser.parse("remind 0", new Ui(), new TaskList(new ArrayList<Task>()));
        } catch (KuromiException e) {
            assertEquals(e.getMessage(), "OOPS!!! I'm not reminding you anything!\n(Your command: 0 task)");
        }
    }

    @Test
    public void findCommandWrongInput() {
        try {
            Command res = Parser.parse("find bo ok", new Ui(), new TaskList(new ArrayList<Task>()));
        } catch (KuromiException e) {
            assertEquals(e.getMessage(), "OOPS!!! The keyword can only contain 1 word.");
        }
    }

    @Test
    public void catchNumberFormatException() {
        try {
            Command res = Parser.parse("mark a", new Ui(), new TaskList(new ArrayList<Task>()));
        } catch (KuromiException e) {
            assertEquals(e.getMessage(), "OOPS!!! The index should be an number.");
        }
    }

    @Test
    public void invalidCommand() {
        try {
            Command res = Parser.parse("afkhfa", new Ui(), new TaskList(new ArrayList<Task>()));
        } catch (KuromiException e) {
            assertEquals(e.getMessage(), "OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
