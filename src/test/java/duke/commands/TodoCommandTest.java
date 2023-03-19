package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;

public class TodoCommandTest {

    @Test
    void execute() {
        Storage storage = new Storage();
        TaskList tasks = new TaskList();
        try {
            String fullCommand = "todo go home";
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(tasks.size());
        Task test = tasks.get(0);
        assertEquals("[T][ ] go home", test.toString());
    }
}
