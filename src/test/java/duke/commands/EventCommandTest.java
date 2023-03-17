package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;

class EventCommandTest {

    @Test
    void execute() {
        Storage storage = new Storage();
        TaskList tasks = new TaskList();
        try {
            String fullCommand = "event finish homework /from 2023-01-25 18:59 /to 2023-02-11";
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(tasks.size());
        Task test = tasks.get(0);
        assertEquals("[E][ ] finish homework (from: 2023-01-25 18:59 to: 2023-02-11)", test.toString());
    }
}
