package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DeadlineCommandTest {

    @Test
    public void executeTest() {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList tasks = new TaskList();
        try {
            String fullCommand = "deadline return book /by 2023-01-25 18:59";
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, storage, ui);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
        System.out.println(tasks.size());
        Task test = tasks.get(0);
        assertEquals(test.toString(), "[D][ ] return book (by: Jan 25 2023 18:59)");
    }

}
