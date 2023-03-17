package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.TaskList;

public class FindCommandTest {
    TaskList tasks;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList();
    }

    @Test
    public void shouldFindAllTypesOfTask() {
        this.tasks.createTodo("Demo");
        this.tasks.createDeadline("Demo", LocalDate.parse("2023-01-01"));
        this.tasks.createEvent("Demo", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-01"));

        String res = new FindCommand("Demo").run(this.tasks);
        int actual = res.split("Demo").length - 1;
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindRegardlessOfCapitalisation() {
        this.tasks.createTodo("Demo");
        this.tasks.createDeadline("demo", LocalDate.parse("2023-01-01"));
        this.tasks.createEvent("deMo", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-01"));

        String res = new FindCommand("Demo").run(this.tasks);
        int actual = res.toLowerCase().split("demo").length - 1;
        int expected = 3;
        assertEquals(expected, actual);
    }
}
