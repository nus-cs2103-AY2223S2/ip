package dudu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dudu.exception.InvalidCommandException;


public class DeadlineTest {
    private static final String name = "deadline task";
    private static final String by = "2022-10-12";
    private Deadline task;
    public DeadlineTest() throws InvalidCommandException {
        this.task = new Deadline(name, by);
    }

    @Test
    void getStatusTest() {
        assertEquals("0", task.getStatus());
    }
    @Test
    void toStringTest() {
        assertEquals(
                String.format("[D][%s] %s (by: Oct 12 2022)", task.isDone() ? "X" : " ", name),
                task.toString()
        );
    }
}
