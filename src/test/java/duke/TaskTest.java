package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void notMarkTest() {
        Task checkTask = new Task("Eat");
        assertEquals(checkTask.toString(), "[ ] [Priority Level: LOW] Eat");
        assertEquals(checkTask.parse(), "[ ] Eat");
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

        assertEquals(checkTask.toString(), "[ ] [Priority Level: LOW] Eat");
        assertEquals(checkTask.parse(), "[ ] Eat");
    }

    @Test
    public void increasePriorityTest() {
        Task checkTask = new Task("Eat");
        checkTask.increasePriority();

        assertEquals(checkTask.toString(), "[ ] [Priority Level: MEDIUM] Eat");

        checkTask.increasePriority();
        assertEquals(checkTask.toString(), "[ ] [Priority Level: HIGH] Eat");

        checkTask.increasePriority();
        assertEquals(checkTask.toString(), "[ ] [Priority Level: HIGH] Eat");
    }

    @Test
    public void decreasePriorityTest() {
        Task checkTask = new Task("Eat");
        checkTask.increasePriority();
        checkTask.increasePriority();
        checkTask.increasePriority();

        assertEquals(checkTask.toString(), "[ ] [Priority Level: HIGH] Eat");

        checkTask.decreasePriority();
        assertEquals(checkTask.toString(), "[ ] [Priority Level: MEDIUM] Eat");

        checkTask.decreasePriority();
        assertEquals(checkTask.toString(), "[ ] [Priority Level: LOW] Eat");

        checkTask.decreasePriority();
        assertEquals(checkTask.toString(), "[ ] [Priority Level: LOW] Eat");
    }
    @Test
    public void containsTest() {
        Task checkTask = new Task("Eat");

        assertEquals(checkTask.contains("Eat"), true);
        assertEquals(checkTask.contains("at"), true);
        assertEquals(checkTask.contains("eat"), false);
        assertEquals(checkTask.contains("At"), false);
        assertEquals(checkTask.contains("E"), true);
        assertEquals(checkTask.contains("Et"), false);
        assertEquals(checkTask.contains(""), true);
        assertEquals(checkTask.contains("t"), true);
    }
}

