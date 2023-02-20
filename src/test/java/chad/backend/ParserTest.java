package chad.backend;

import chad.tasks.Task;
import chad.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    public void parser_welcome_printsWelcomeMsg() throws IOException {
        Parser parser = new Parser(new TaskList());
        assertEquals("Hello, Boss. I am Chad, your personal assistant.", parser.parse("welcome"));
    }

    @Test
    public void parser_list_printsList() throws IOException {
        TaskList list = new TaskList();
        list.getWholeList().removeAll(list.getWholeList());
        Parser parser = new Parser(list);
        assertEquals("You don't have any pending tasks, Boss.", parser.parse("list"));

        parser.parse("todo hello1");
        parser.parse("event hello /from 2pm /to 3pm");
        String expected = "Here are the tasks in your list:\n1. [T][ ] hello1\n2. [E][ ] hello (from: 2pm to: 3pm)\n";
        assertEquals(expected, parser.parse("list"));
    }

    @Test
    public void parser_bye_savesList() throws IOException {
        TaskList list = new TaskList();
        list.getWholeList().removeAll(list.getWholeList());
        Parser parser = new Parser(list);

        //  Populate list:
        parser.parse("todo hello1");
        parser.parse("event hello2 /from 2pm /to 3pm");
        parser.parse("event hello3 /from 2pm /to 3pm");
        parser.parse("event hello4 /from 2pm /to 3pm");

        // Save current list:
        String expectedSaves = parser.parse("list");
        assertEquals("Cheers, Boss, I'll see you soon!\n", parser.parse("bye"));

        //  Create new List and Parser:
        TaskList newList = new TaskList();
        Parser newParser = new Parser(newList);

        //Check if list retrieved successfully:
        assertEquals(expectedSaves, newParser.parse("list"));
    }

    @Test
    public void parser_duplicateTasksYes_confirmsDuplicates() throws IOException {
        TaskList list = new TaskList();
        list.getWholeList().removeAll(list.getWholeList());
        Parser parser = new Parser(list);

        //  Populate list:
        parser.parse("todo hello1");
        parser.parse("event hello2 /from 2pm /to 3pm");
        parser.parse("event hello3 /from 2pm /to 3pm");
        parser.parse("event hello4 /from 2pm /to 3pm");

        //  Insert duplicate:
        String expected = "Boss, I've found a an existing task with the same name:\n"
                    + new Todo("hello1").toString()
                    + "\nDo you still want to add this new task?\n"
                    + new Todo("hello1").toString();
        assertEquals(expected, parser.parse("todo hello1"));

        //  Choose to save duplicate:
        parser.parse("yes");

        //  Check if latest task is equal to the newly added task:
        ArrayList<Task> newList = list.getWholeList();
        assertEquals(new Todo("hello1").toString(),
                     newList.get(newList.size() - 1).toString());
    }

    @Test
    public void parser_duplicateTasksNo_confirmsDuplicates() throws IOException {
        TaskList list = new TaskList();
        list.getWholeList().removeAll(list.getWholeList());
        Parser parser = new Parser(list);

        //  Populate list:
        parser.parse("todo hello1");
        parser.parse("event hello2 /from 2pm /to 3pm");
        parser.parse("event hello3 /from 2pm /to 3pm");
        parser.parse("event hello4 /from 2pm /to 3pm");

        //  Note down the current last task:
        Task previousLastTask = list.get(3);

        //  Insert duplicate:
        String expected = "Boss, I've found a an existing task with the same name:\n"
                + new Todo("hello1").toString()
                + "\nDo you still want to add this new task?\n"
                + new Todo("hello1").toString();
        assertEquals(expected, parser.parse("todo hello1"));

        //  Choose to save duplicate:
        parser.parse("no");

        //  Check if last task is unchanged:
        assertEquals(previousLastTask,
                     list.get(3));
    }
}