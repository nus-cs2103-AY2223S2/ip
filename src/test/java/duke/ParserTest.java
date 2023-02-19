package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();
    private Parser p = new Parser(taskList, ui);

    @Test
    public void addTodoTest() {
        String task = "todo borrow book";
        String description = "borrow book";
        Todo todo = new Todo(description);
        try {
            p.parse(task);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        Task t = taskList.get(0);
        assertEquals(todo.toString(), t.toString());
    }
    @Test
    public void addDeadlineTest() {
        String task = "deadline read book /by 2023-01-28";
        String description = "read book";
        String by = "2023-01-28";
        Deadline d = new Deadline(description, by);
        try {
            p.parse(task);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        Task t = taskList.get(0);
        assertEquals(d.toString(), t.toString());
    }

    @Test
    public void addEventTest() {
        String task = "event drive car /from Monday /to Sunday";
        String description = "drive car";
        String from = "Monday";
        String to = "Sunday";
        Event event = new Event(description, from, to);
        try {
            p.parse(task);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        Task t = taskList.get(0);
        assertEquals(event.toString(), t.toString());
    }
}
