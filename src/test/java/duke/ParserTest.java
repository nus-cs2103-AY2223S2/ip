package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParserTest {
    @Test
    void addTask_todo_success() throws DukeException {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        parser.parse("todo return book");

        assertEquals(tasks.size(), 1);
        assertEquals("[T][ ] return book", tasks.get(0).toString());
    }

    @Test
    void addTask_deadline_success() throws DukeException {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        parser.parse("deadline return book /by 31/01/2023 1600");

        assertEquals(tasks.size(), 1);
        assertEquals("[D][ ] return book by: Tue, 31 Jan 2023 04:00PM", tasks.get(0).toString());
    }

    @Test
    void addTask_event_success() throws DukeException {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        parser.parse("event rc party /from 31/01/2023 1600 /to 31/01/2023 2100");

        assertEquals(tasks.size(), 1);
        assertEquals("[E][ ] rc party from: Tue, 31 Jan 2023 04:00PM to: Tue, 31 Jan 2023 09:00PM", tasks.get(0).toString());
    }
}