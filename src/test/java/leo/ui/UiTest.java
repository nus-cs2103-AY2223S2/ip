package leo.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import leo.leoexception.IncorrectMarkException;
import leo.storage.Task;
import leo.storage.TaskType;

public class UiTest {

    @Test
    public void testLeoResponse() {
        assertEquals("Leo: hello", Ui.leoResponse("hello"));
        assertEquals("Leo: nice to meet you", Ui.leoResponse("nice to meet you"));
        assertEquals("Leo: cs2103t", Ui.leoResponse("cs2103t"));
    }

    @Test
    public void testNotFirstLineOutput() {
        assertEquals("     hello", Ui.notFirstLine("hello"));
        assertEquals("     nice to meet you", Ui.notFirstLine("nice to meet you"));
        assertEquals("     cs2103t", Ui.notFirstLine("cs2103t"));
    }

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
