package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void notMarkTest() {
        Task checkTask = new Task("Eat");
        assertEquals(checkTask.toString(), "[  ] [Priority Level: LOW] Eat");
        assertEquals(checkTask.parse(), "[  ] Eat");
    }

    @Test
    public void markTest() {
        Task checkTask = new Task("Eat");
        checkTask.makeCompleted();

        assertEquals(checkTask.toString(), "[X] [Priority Level: LOW] Eat");
        assertEquals(checkTask.parse(), "[X] Eat");
    }

    @Test
    public void unmarkTest() {
        Task checkTask = new Task("Eat");
        checkTask.makeCompleted();
        checkTask.makeIncomplete();

        assertEquals(checkTask.toString(), "[  ] [Priority Level: LOW] Eat");
        assertEquals(checkTask.parse(), "[  ] Eat");
    }
}

