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
        assertEquals("Okay! I've marked this task as done!\n[D][X] Tutorial (by: 28 Feb 2024 11:59PM)\n",
                new CommandHandler().handleCommand(
                        new Command("mark", argument),
                        new TaskList().add(new Deadline("Tutorial", "28/02/2024 2359"))));
    }

    @Test
    public void markTask_taskNotPresent_failedResponse() {
        List<String> argument = new ArrayList<String>();
        argument.add("3");
        assertEquals("You don't have that many tasks!\n",
                new CommandHandler().handleCommand(
                        new Command("mark", argument),
                        new TaskList().add(new Deadline("Tutorial", "2359"))));
    }

    @Test
    public void unmarkTask_taskPresent_unmarkedResponse() {
        List<String> argument = new ArrayList<String>();
        argument.add("2");
        assertEquals("Okay! I've marked this task as not done yet!\n[T][ ] Find girlfriend\n",
                new CommandHandler().handleCommand(
                        new Command("unmark", argument),
                        new TaskList()
                                .add(new Deadline("Tutorial", "2222"))
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
                                .add(new Deadline("Tutorial", "2359"))
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
        argument.add("31/12/2022 1330");
        assertEquals("Added: [D][ ] Project (by: 31 Dec 2022 01:30PM)\n",
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
        argument.add("31/12/2022 1130");
        argument.add("31/12/2022 1230");
        assertEquals("Added: [E][ ] Project meeting (31 Dec 2022 11:30AM - 31 Dec 2022 12:30PM)\n",
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
        assertEquals("Okay! I deleted task [D][ ] Tutorial (by: 31 Jan 2023 11:59PM)\n",
                new CommandHandler().handleCommand(
                        new Command("delete", argument),
                        new TaskList().add(new Deadline("Tutorial", "31/01/2023 2359"))));
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

    @Test
    public void findTasks_tasksPresent_tasksFound() {
        List<String> argument = new ArrayList<String>();
        argument.add("stuff");
        assertEquals("I found these tasks in your task list!\n[T][ ] stuff turkey\n[T][ ] stuff chicken\n",
                new CommandHandler().handleCommand(
                        new Command("find", argument),
                        new TaskList().add(new Todo("stuff turkey"))
                                .add(new Todo("stuff chicken"))
                                .add(new Todo("don't fill birds"))
                )
        );
    }

    @Test
    public void findTasks_tasksNotPresent_noTasksFound() {
        List<String> argument = new ArrayList<String>();
        argument.add("cook");
        assertEquals("You don't have any tasks matching that description!\n",
                new CommandHandler().handleCommand(
                        new Command("find", argument),
                        new TaskList().add(new Todo("stuff turkey"))
                                .add(new Todo("stuff chicken"))
                                .add(new Todo("don't fill birds"))
                )
        );
    }
    
    
}
