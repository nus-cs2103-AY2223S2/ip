package kuromi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import kuromi.exception.KuromiException;

public class DeadlineTest {
    @Test
    public void string() throws KuromiException {
        Deadline deadline = new Deadline("return book", "2024-01-30 15:30");
        assertEquals("[D][ ] return book (by: 2024-01-30 15:30)", deadline.toString());
    }

    @Test
    public void markTodo() throws KuromiException {
        Deadline deadline = new Deadline("return book", "2024-01-30 15:30");
        deadline.mark();
        assertTrue(deadline.isDone);
    }

    @Test
    public void unmarkTodo() throws KuromiException {
        Deadline deadline = new Deadline("return book", "2024-01-30 15:30");
        deadline.unmark();
        assertFalse(deadline.isDone);
    }

    @Test
    public void getSymbol() throws KuromiException {
        Deadline deadline = new Deadline("return book", "2024-01-30 15:30");
        assertEquals(deadline.getSymbol(), "D");
    }
}
