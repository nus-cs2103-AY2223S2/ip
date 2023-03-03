package duke.util;

import duke.task.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    void storageTest(@TempDir Path tempDir) throws DukeException, IOException {
        Path file = tempDir.resolve("./test.txt");
        Storage storage = new Storage(file.toString());
        TaskList tasks = new TaskList(storage.loadData());
        Ui ui = new Ui();
        Parser parser = new Parser(storage, tasks, ui);

        String[] testInput1 = "event blackpink concert /from 2023-05-13T19:30 /to 2023-05-13T22:30".split(" ", 2);
        parser.parse(testInput1);
        String[] testInput2 = "bye".split(" ", 2);
        parser.parse(testInput2);

        assertEquals(Files.readAllLines(file), singletonList(new Event("blackpink concert", new String[] {"2023-05-13T19:30", "2023-05-13T22:30"}).toData()));
    }
}
