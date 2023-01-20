package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void createToDo() {
        Todos created = new Todos("Sleep");
        assertEquals("[T][ ] Sleep", created.toString());
    }

    @Test
    public void markToDo() {
        Todos created = new Todos("Sleep");
        created.mark();
        assertEquals("[T][X] Sleep", created.toString());
    }

    @Test
    public void saveToDo() {
        Todos created = new Todos("Sleep");
        assertEquals("T | 0 | Sleep\n", created.toWrite());
    }

}
