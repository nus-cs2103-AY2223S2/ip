package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandHandlerTest {
    @Test
    public void testEndDuke() {
        assertEquals("Bye! Hope to see you again soon!\n", 
                new CommandHandler().handleCommand(
                        new Command("bye", new ArrayList<String>()), 
                        new TaskList()));
    }
    
    @Test
    public void testShowTasks() {
        assertEquals("1.[T][ ] CS2103\n",
                new CommandHandler().handleCommand(
                        new Command("list", new ArrayList<String>()),
                        new TaskList().add(new Todo("CS2103"))));
    }
    
    @Test
    public void showTasks_noTasks_noItemsResponse() {
        assertEquals("You currently have no items in your to-do list!\n",
                new CommandHandler().handleCommand(
                        new Command("list", new ArrayList<String>()),
                        new TaskList()));
    }
    @Test
    public void markTask_taskPresent_markedResponse() {
        List<String> argument = new ArrayList<String>();
        argument.add("1");
        assertEquals("Okay! I've marked this task as done!\n[D][X] Tutorial (by: Tomorrow 3pm)\n",
                new CommandHandler().handleCommand(
                        new Command("mark", argument),
                        new TaskList().add(new Deadline("Tutorial", "Tomorrow 3pm"))));
    }

    @Test
    public void markTask_taskNotPresent_failedResponse() {
        List<String> argument = new ArrayList<String>();
        argument.add("3");
        assertEquals("You don't have that many tasks!\n",
                new CommandHandler().handleCommand(
                        new Command("mark", argument),
                        new TaskList().add(new Deadline("Tutorial", "Tomorrow 3pm"))));
    }

    @Test
    public void unmarkTask_taskPresent_unmarkedResponse() {
        List<String> argument = new ArrayList<String>();
        argument.add("2");
        assertEquals("Okay! I've marked this task as not done yet!\n[T][ ] Find girlfriend\n",
                new CommandHandler().handleCommand(
                        new Command("unmark", argument),
                        new TaskList()
                                .add(new Deadline("Tutorial", "Tomorrow 3pm"))
                                .add(new Todo("Find girlfriend"))
                )
        );
    }
    
    @Test
    public void unmarkTask_taskNotPresent_failedResponse() {
        List<String> argument = new ArrayList<String>();
        argument.add("5");
        assertEquals("You don't have that many tasks!\n",
                new CommandHandler().handleCommand(
                        new Command("unmark", argument),
                        new TaskList()
                                .add(new Deadline("Tutorial", "Tomorrow 3pm"))
                                .add(new Todo("Find girlfriend"))
                )
        );
    }
    
    @Test
    public void testAddTodo() {
        List<String> argument = new ArrayList<String>();
        argument.add("Find girlfriend");
        assertEquals("Added: [T][ ] Find girlfriend\n",
                new CommandHandler().handleCommand(
                        new Command("todo", argument),
                        new TaskList()
                )
        );
    }

    @Test
    public void testAddDeadline() {
        List<String> argument = new ArrayList<String>();
        argument.add("Project");
        argument.add("4pm");
        assertEquals("Added: [D][ ] Project (by: 4pm)\n",
                new CommandHandler().handleCommand(
                        new Command("deadline", argument),
                        new TaskList()
                )
        );
    }

    @Test
    public void testAddEvent() {
        List<String> argument = new ArrayList<String>();
        argument.add("Project meeting");
        argument.add("4pm");
        argument.add("5pm");
        assertEquals("Added: [E][ ] Project meeting (4pm - 5pm)\n",
                new CommandHandler().handleCommand(
                        new Command("event", argument),
                        new TaskList()
                )
        );
    }

    @Test
    public void testNoMatch() {
        assertEquals("Sorry, I didn't understand that, please ask again.\n",
                new CommandHandler().handleCommand(
                        new Command("noMatch", new ArrayList<String>()),
                        new TaskList()
                )
        );
    }

    @Test
    public void deleteEvent_taskPresent_deletedResponse() {
        List<String> argument = new ArrayList<String>();
        argument.add("1");
        assertEquals("Okay! I deleted task [D][ ] Tutorial (by: Tomorrow 3pm)\n",
                new CommandHandler().handleCommand(
                        new Command("delete", argument),
                        new TaskList().add(new Deadline("Tutorial", "Tomorrow 3pm"))));
    }
    
    @Test
    public void testInvalid() {
        List<String> argument = new ArrayList<String>();
        argument.add("Please enter one task which you would like to mark as done.\n");
        assertEquals("Please enter one task which you would like to mark as done.\n",
                new CommandHandler().handleCommand(
                        new Command("invalid", argument),
                        new TaskList()
                )
        );
    }
    
    
}
