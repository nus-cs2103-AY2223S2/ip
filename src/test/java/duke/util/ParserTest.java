package duke.util;

import duke.task.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void addTodoTest(@TempDir Path tempDir) throws DukeException {
        Path file = tempDir.resolve("./test.txt");
        Storage storage = new Storage(file.toString());
        TaskList tasks = new TaskList(storage.loadData());
        Ui ui = new Ui();
        Parser parser = new Parser(storage, tasks, ui);

        String[] testInput = "todo sleep".split(" ", 2);
        parser.parse(testInput);
        assertEquals(new Todo("sleep").toString(), tasks.getTask(0).toString());
    }

    @Test
    void deleteTodoTest(@TempDir Path tempDir) throws DukeException {
        Path file = tempDir.resolve("./test.txt");
        Storage storage = new Storage(file.toString());
        TaskList tasks = new TaskList(storage.loadData());
        Ui ui = new Ui();
        Parser parser = new Parser(storage, tasks, ui);

        String[] testInput_1 = "todo sleep".split(" ", 2);
        parser.parse(testInput_1);
        String[] testInput_2 = "delete 1".split(" ", 2);
        parser.parse(testInput_2);
        assertEquals(0, tasks.size());
    }
}
