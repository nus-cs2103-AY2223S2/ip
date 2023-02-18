package leo.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import leo.leoexception.IncorrectMarkException;
import leo.storage.Task;
import leo.storage.TaskType;

public class UiTest {

    @Test
    public void testTypeOutput() {
        Task t = new Task("study!");
        assertEquals("[T]", Ui.type(t));
        t.setType(TaskType.DEADLINE);
        assertEquals("[D]", Ui.type(t));
        t.setType(TaskType.EVENT);
        assertEquals("[E]", Ui.type(t));
    }

    @Test
    public void testStatusOutput() throws IncorrectMarkException {
        Task t = new Task("study!");
        assertEquals("[ ] ", Ui.completion(t));
        t.mark();
        assertEquals("[X] ", Ui.completion(t));
    }
}
