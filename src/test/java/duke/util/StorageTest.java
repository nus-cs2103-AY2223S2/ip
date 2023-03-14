package duke.util;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import duke.command.Command;
import duke.task.Event;

public class StorageTest {
    @Test
    void storageTest(@TempDir Path tempDir) throws DukeException, IOException {
        Path temp = Files.createTempFile(null, ".txt");
        Ui ui = new Ui();
        Storage storage = new Storage(temp.toString(), ui);
        TaskList tasks = new TaskList(storage.loadData());
        Parser parser = new Parser(storage, tasks, ui);

        String[] testInput1 = "event blackpink concert /from 2023-05-13T19:30 /to 2023-05-13T22:30".split(" ", 2);
        Command command1 = parser.parse(testInput1);
        command1.execute(storage, tasks, ui);

        String[] testInput2 = "bye".split(" ", 2);
        Command command2 = parser.parse(testInput2);
        command2.execute(storage, tasks, ui);

        assertEquals(Files.readAllLines(temp), singletonList(new Event("blackpink concert",
                new String[] {"2023-05-13T19:30", "2023-05-13T22:30"}).toData()));
    }
}
