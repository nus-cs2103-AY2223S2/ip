package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskEventTest {
    private static final String TEST_UNESCAPED_DESC = "VERTICAL | BAR";
    private static final String TEST_ESCAPED_DESC = "VERTICAL \\| BAR";
    private static final String TEST_FROM_DATE = "2023-01-23";
    private static final String TEST_TO_DATE = "2023-01-25";
    private static final String TEST_ENCODED_TASK = String.format(
            "E | 1 | %s | %s | %s",
            TaskEventTest.TEST_ESCAPED_DESC,
            TaskEventTest.TEST_FROM_DATE,
            TaskEventTest.TEST_TO_DATE);

    private static TaskEvent getTestTask() {
        TaskEvent task = new TaskEvent(
                TaskEventTest.TEST_UNESCAPED_DESC,
                TaskEventTest.TEST_FROM_DATE,
                TaskEventTest.TEST_TO_DATE);
        task.markAsDone();
        return task;
    }

    @Test
    public void equals() {
        assertEquals(
                TaskEventTest.getTestTask(),
                TaskEventTest.getTestTask());
    }

    @Test
    public void encodeAsString() {
        TaskEvent task = TaskEventTest.getTestTask();
        assertEquals(task.encodeAsString(), TaskEventTest.TEST_ENCODED_TASK);
    }

    @Test
    public void loadFromString() {
        TaskEvent expectedTask = TaskEventTest.getTestTask();
        TaskEvent loadedTask = TaskEvent.loadFromString(TaskEventTest.TEST_ENCODED_TASK);
        assertEquals(loadedTask, expectedTask);
    }
}
