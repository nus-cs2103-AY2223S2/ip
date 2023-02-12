package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import duke.command.Command;

public class TaskListTest {

    @Test
    public void taskList_indexOutOfBounds_raisesException() {
        TaskList tasks = new TaskList();
        LinkedHashMap<Command.Argument, String> args1 = new LinkedHashMap<>();
        args1.put(Command.Argument.TODO, "task with kword");
        Command command1 = new Command(args1);
        LinkedHashMap<Command.Argument, String> args2 = new LinkedHashMap<>();
        args2.put(Command.Argument.DEADLINE, "another task");
        args2.put(Command.Argument.BY, "2000-01-01 1000");
        Command command2 = new Command(args2);

        LinkedHashMap<Command.Argument, String> args3 = new LinkedHashMap<>();
        args3.put(Command.Argument.MARK, "2");
        Command markCommand = new Command(args3);
        LinkedHashMap<Command.Argument, String> args4 = new LinkedHashMap<>();
        args4.put(Command.Argument.DELETE, "2");
        Command deleteCommand = new Command(args4);

        tasks.execute(command1);
        tasks.execute(command2);
        assertThrows(IllegalArgumentException.class, (() -> tasks.execute(markCommand)));
        assertThrows(IllegalArgumentException.class, (() -> tasks.execute(deleteCommand)));

    }

    @Test
    public void findAll() {
        TaskList tasks = new TaskList();
        LinkedHashMap<Command.Argument, String> args1 = new LinkedHashMap<>();
        args1.put(Command.Argument.TODO, "task with kword");
        Command command1 = new Command(args1);
        LinkedHashMap<Command.Argument, String> args2 = new LinkedHashMap<>();
        args2.put(Command.Argument.DEADLINE, "another task");
        args2.put(Command.Argument.BY, "2000-01-01 1000");
        Command command2 = new Command(args2);
        LinkedHashMap<Command.Argument, String> args3 = new LinkedHashMap<>();
        args3.put(Command.Argument.DEADLINE, "another kword task");
        args3.put(Command.Argument.BY, "2000-01-01 1000");
        Command command3 = new Command(args3);

        Task task1 = tasks.execute(command1);
        tasks.execute(command2);
        Task task3 = tasks.execute(command3);

        String[] results = tasks.findAll("kword").split("\n");
        assertEquals("0. " + task1, results[0]);
        assertEquals(2, results.length);
        assertEquals("2. " + task3, results[1]);
    }

    @Test
    public void addTask_givenDuplicate_rejectsCommand() {
        TaskList tasks = new TaskList();
        LinkedHashMap<Command.Argument, String> args1 = new LinkedHashMap<>();
        args1.put(Command.Argument.TODO, "todo task");
        Command command1 = new Command(args1);
        LinkedHashMap<Command.Argument, String> args2 = new LinkedHashMap<>();
        args2.put(Command.Argument.DEADLINE, "deadline task");
        args2.put(Command.Argument.BY, "2001-05-06 1200");
        Command command2 = new Command(args2);
        LinkedHashMap<Command.Argument, String> args3 = new LinkedHashMap<>();
        args3.put(Command.Argument.EVENT, "event task");
        args3.put(Command.Argument.FROM, "2003-12-30 2030");
        args3.put(Command.Argument.TO, "2003-12-31 2359");
        Command command3 = new Command(args3);

        Task task1 = tasks.execute(command1);
        Task task2 = tasks.execute(command2);
        Task task3 = tasks.execute(command3);

        Exception exception1 = assertThrows(
                IllegalStateException.class, () -> tasks.execute(command1));
        String expectedMessage1 = "Task " + task1 + " already exists";
        assertTrue(exception1.getMessage().contains(expectedMessage1));
        Exception exception2 = assertThrows(
                IllegalStateException.class, () -> tasks.execute(command2));
        String expectedMessage2 = "Task " + task2 + " already exists";
        assertTrue(exception2.getMessage().contains(expectedMessage2));
        Exception exception3 = assertThrows(
                IllegalStateException.class, () -> tasks.execute(command3));
        String expectedMessage3 = "Task " + task3 + " already exists";
        assertTrue(exception3.getMessage().contains(expectedMessage3));
    }

}
