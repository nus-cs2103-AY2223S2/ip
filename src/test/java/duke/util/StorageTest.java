package duke.util;

import duke.task.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    void addTodo_storage_write_success() throws DukeException, IOException {
        Path path = tempDir.resolve("text.txt");
        TaskList tasks = new TaskList();
        Storage storage = new Storage(path.toString());
        tasks.add(new Todo("return book"));
        storage.update(tasks);
        assertEquals("1|T| |return book" ,Files.readString(path));
    }
}
