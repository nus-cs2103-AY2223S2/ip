package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandHandlerTest {
    @Test
    public void testEndDuke() {
        assertEquals("Bye! Hope to see you again soon!\n", 
                new CommandHandler().handleCommand(
                        new Command("bye"),
                        new TaskList(),
                        new Storage()));
    }

    @Test
    public void testShowTasks() {
        assertEquals("1.[T][ ] CS2103\n",
                new CommandHandler().handleCommand(
                        new Command("list"),
                        new TaskList().add(new Todo("CS2103")),
                        new Storage()));
    }
    
    @Test
    public void showTasks_noTasks_noItemsResponse() {
        assertEquals("You currently have no items in your to-do list!\n",
                new CommandHandler().handleCommand(
                        new Command("list"),
                        new TaskList(),
                        new Storage()));
    }
    @Test
    public void markTask_taskPresent_markedResponse() {
        assertEquals("Okay! I've marked this task as done!\n[D][X] Tutorial (by: 28 Feb 2024 11:59PM)\n",
                new CommandHandler().handleCommand(
                        new Command("mark", "1"),
                        new TaskList().add(new Deadline("Tutorial", "28/02/2024 2359")),
                        new Storage()));
    }

    @Test
    public void markTask_taskNotPresent_failedResponse() {
        assertEquals("You don't have that many tasks!\n",
                new CommandHandler().handleCommand(
                        new Command("mark", "3"),
                        new TaskList().add(new Deadline("Tutorial", "2359")),
                        new Storage()));
    }

    @Test
    public void unmarkTask_taskPresent_unmarkedResponse() {
        assertEquals("Okay! I've marked this task as not done yet!\n[T][ ] Find girlfriend\n",
                new CommandHandler().handleCommand(
                        new Command("unmark", "2"),
                        new TaskList()
                                .add(new Deadline("Tutorial", "2222"))
                                .add(new Todo("Find girlfriend")),
                        new Storage()
                )
        );
    }
    
    @Test
    public void unmarkTask_taskNotPresent_failedResponse() {
        assertEquals("You don't have that many tasks!\n",
                new CommandHandler().handleCommand(
                        new Command("unmark", "5"),
                        new TaskList()
                                .add(new Deadline("Tutorial", "2359"))
                                .add(new Todo("Find girlfriend")),
                        new Storage()
                )
        );
    }
    
    @Test
    public void testAddTodo() {
        assertEquals("Added: [T][ ] Find girlfriend\n",
                new CommandHandler().handleCommand(
                        new Command("todo", "Find girlfriend"),
                        new TaskList(),
                        new Storage()
                )
        );
    }

    @Test
    public void testAddDeadline() {
        assertEquals("Added: [D][ ] Project (by: 31 Dec 2022 01:30PM)\n",
                new CommandHandler().handleCommand(
                        new Command("deadline", "Project", "31/12/2022 1330"),
                        new TaskList(),
                        new Storage()
                )
        );
    }

    @Test
    public void testAddEvent() {
        assertEquals("Added: [E][ ] Project meeting (31 Dec 2022 11:30AM - 31 Dec 2022 12:30PM)\n",
                new CommandHandler().handleCommand(
                        new Command("event",
                                "Project meeting",
                                "31/12/2022 1130",
                                "31/12/2022 1230"),
                        new TaskList(),
                        new Storage()
                )
        );
    }

    @Test
    public void testNoMatch() {
        assertEquals("Sorry, I didn't understand that, please ask again.\n",
                new CommandHandler().handleCommand(
                        new Command("noMatch"),
                        new TaskList(),
                        new Storage()
                )
        );
    }

    @Test
    public void deleteEvent_taskPresent_deletedResponse() {
        assertEquals("Okay! I deleted task [D][ ] Tutorial (by: 31 Jan 2023 11:59PM)\n",
                new CommandHandler().handleCommand(
                        new Command("delete", "1"),
                        new TaskList().add(new Deadline("Tutorial", "31/01/2023 2359")),
                        new Storage()));
    }
    
    @Test
    public void testInvalid() {
        assertEquals("Please enter one task which you would like to mark as done.\n",
                new CommandHandler().handleCommand(
                        new Command("invalid",
                                "Please enter one task which you would like to mark as done.\n"),
                        new TaskList(),
                        new Storage()
                )
        );
    }

    @Test
    public void findTasks_tasksPresent_tasksFound() {
        assertEquals("I found these tasks in your task list!\n[T][ ] stuff turkey\n[T][ ] stuff chicken\n",
                new CommandHandler().handleCommand(
                        new Command("find", "stuff"),
                        new TaskList().add(new Todo("stuff turkey"))
                                .add(new Todo("stuff chicken"))
                                .add(new Todo("don't fill birds")),
                        new Storage()
                )
        );
    }

    @Test
    public void findTasks_tasksNotPresent_noTasksFound() {
        assertEquals("You don't have any tasks matching that description!\n",
                new CommandHandler().handleCommand(
                        new Command("find", "cook"),
                        new TaskList().add(new Todo("stuff turkey"))
                                .add(new Todo("stuff chicken"))
                                .add(new Todo("don't fill birds")),
                        new Storage()
                )
        );
    }
    
    
}
