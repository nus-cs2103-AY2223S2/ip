package jarvis.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jarvis.exception.command.MissingParameterException;

public class ToDoTaskTest {
    private static final String BODY = "a todo task";

    private ToDoTask task;

    public ToDoTaskTest() {
        try {
            this.task = new ToDoTask(BODY);
        } catch (MissingParameterException e) {
            this.task = null;
        }
    }

    @Test
    public void serializeTest() {
        assert task != null;
        assertEquals(
                String.format("T / %b / %s", task.isDone(), BODY),
                task.serialize()
        );
    }

    @Test
    public void toStringTest() {
        assert task != null;
        assertEquals(
                String.format("[T][%s] %s", task.isDone() ? "X" : " ", BODY),
                task.toString()
        );
    }
}
