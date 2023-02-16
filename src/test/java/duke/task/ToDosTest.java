package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ToDosTest {
    private ToDos todo;

    @BeforeEach
    public void setUp() {
        todo = new ToDos("Testing", false);
    }

    @Test
    @DisplayName("Ensure Todo object is created")
    public void testTodos() {
        assertNotNull(todo, "Deadline object should be created");
    }

    @Test
    @DisplayName("Ensure Todo toString method works correctly")
    public void testToString() {
        assertEquals(todo.toString(),
                "[T][ ] Testing");
    }

    @Test
    @DisplayName("Ensure Todo save method works correctly")
    public void testSave() {
        assertEquals(todo.save(), "todo Testing-false\n");
    }
}
