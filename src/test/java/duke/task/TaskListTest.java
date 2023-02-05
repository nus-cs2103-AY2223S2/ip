package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import duke.command.Command;

public class TaskListTest {

    @Test
    public void taskList_findAll() {
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

        tasks.execute(command1);
        tasks.execute(command2);
        tasks.execute(command3);
        String[] results = tasks.findAll("kword").split("\n");
        assertEquals(2, results.length);
        assertEquals("0. [T][ ] task with kword", results[0]);
        assertEquals("2. [D][ ] another kword task (by: 01 01 2000 10:00)", results[1]);
    }
}
