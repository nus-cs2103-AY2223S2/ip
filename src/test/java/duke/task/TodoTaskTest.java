package duke.task;

import duke.DukeUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {
    @Test
    public void mark() {
        DukeTask todo = new TodoTask("task one");
        todo.setDone();
        assertEquals(
                "[T][X] task one",
                todo.toString()
        );
    }

    @Test
    public void unmark() {
        DukeTask todo = new TodoTask("task one");

        assertEquals(
                "[T][ ] task one",
                todo.toString()
        );
    }

    @Test
    public void isOnDate() {
        DukeTask todo = new TodoTask("task one");
        assertEquals(false,
                todo.isOnDate(DukeUtils.parseDate("2/2/23")));
    }


    @Test
    public void toDBSchema() {
        DukeTask todo = new TodoTask("task one");
        assertEquals(
                "T|0|task one",
                todo.toDBSchema()
        );
    }
}
