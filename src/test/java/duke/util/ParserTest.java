package duke.util;

import duke.command.Command;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void addTodoTest(@TempDir Path tempDir) throws DukeException, IOException {
        Path temp = Files.createTempFile(null,".txt");
        Ui ui = new Ui();
        Storage storage = new Storage(temp.toString(), ui);
        TaskList tasks = new TaskList(storage.loadData());
        Parser parser = new Parser(storage, tasks, ui);

        String[] testInput = "todo sleep".split(" ", 2);
        Command command = parser.parse(testInput);
        command.execute(storage, tasks, ui);

        assertEquals(new Todo("sleep").toString(), tasks.getTask(0).toString());
    }

    @Test
    void deleteTodoTest(@TempDir Path tempDir) throws DukeException, IOException {
        Path temp = Files.createTempFile(null,".txt");
        Ui ui = new Ui();
        Storage storage = new Storage(temp.toString(), ui);
        TaskList tasks = new TaskList(storage.loadData());
        Parser parser = new Parser(storage, tasks, ui);

        String[] testInput1 = "todo sleep".split(" ", 2);
        parser.parse(testInput1);
        Command command1 = parser.parse(testInput1);
        command1.execute(storage, tasks, ui);

        String[] testInput2 = "delete 1".split(" ", 2);
        Command command2 = parser.parse(testInput2);
        command2.execute(storage, tasks, ui);

        assertEquals(0, tasks.getSize());
    }
}
