package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskDeadlineTest {
    private static final String TEST_UNESCAPED_DESC = "VERTICAL | BAR";
    private static final String TEST_ESCAPED_DESC = "VERTICAL \\| BAR";
    private static final String TEST_END_DATE = "2023-01-27";
    private static final String TEST_ENCODED_TASK = String.format(
            "D | 1 | %s | %s",
            TaskDeadlineTest.TEST_ESCAPED_DESC,
            TaskDeadlineTest.TEST_END_DATE);

    private static TaskDeadline getTestTask() {
        TaskDeadline task = new TaskDeadline(
                TaskDeadlineTest.TEST_UNESCAPED_DESC,
                TaskDeadlineTest.TEST_END_DATE);
        task.markAsDone();
        return task;
    }

    @Test
    public void equals() {
        assertEquals(
                TaskDeadlineTest.getTestTask(),
                TaskDeadlineTest.getTestTask());
    }

    @Test
    public void encodeAsString() {
        TaskDeadline task = TaskDeadlineTest.getTestTask();
        assertEquals(task.encodeAsString(), TaskDeadlineTest.TEST_ENCODED_TASK);
    }

    @Test
    public void loadFromString() {
        TaskDeadline expectedTask = TaskDeadlineTest.getTestTask();
        TaskDeadline loadedTask = TaskDeadline.loadFromString(TaskDeadlineTest.TEST_ENCODED_TASK);
        assertEquals(loadedTask, expectedTask);
    }
}
