package duke.util;

import duke.command.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {

    @TempDir
    Path tempDir;

    @Test
    void addTask_todo_success() throws DukeException {
        Path path = tempDir.resolve("text.txt");
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        Ui ui = new Ui();
        Storage storage = new Storage(path.toString());

        String[] commandInput = new String[]{"todo", "return book"};

        Command command = parser.parse(commandInput);
        command.execute(tasks, ui, storage);

        assertEquals(tasks.size(), 1);
        assertEquals("[T][ ] return book", tasks.get(0).toString());
    }

    @Test
    void addTask_deadline_success() throws DukeException {
        Path path = tempDir.resolve("text.txt");
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        Ui ui = new Ui();
        Storage storage = new Storage(path.toString());

        String[] commandInput = new String[]{"deadline", "return book /by 15/12/2023 1600"};

        Command command = parser.parse(commandInput);
        command.execute(tasks, ui, storage);

        assertEquals(tasks.size(), 1);
        assertEquals("[D][ ] return book (by: Fri, 15 Dec 2023 04:00PM)", tasks.get(0).toString());
    }
}
