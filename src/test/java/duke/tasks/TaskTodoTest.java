package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTodoTest {
    private static final String TEST_UNESCAPED_DESC = "VERTICAL | BAR";
    private static final String TEST_ESCAPED_DESC = "VERTICAL \\| BAR";
    private static final String TEST_ENCODED_TASK = String.format(
            "T | 1 | %s",
            TaskTodoTest.TEST_ESCAPED_DESC);

    private static TaskTodo getTestTask() {
        TaskTodo task = new TaskTodo(TaskTodoTest.TEST_UNESCAPED_DESC);
        task.markAsDone();
        return task;
    }

    @Test
    public void equals() {
        assertEquals(TaskTodoTest.getTestTask(), TaskTodoTest.getTestTask());
    }

    @Test
    public void encodeAsString() {
        TaskTodo task = TaskTodoTest.getTestTask();
        assertEquals(task.encodeAsString(), TaskTodoTest.TEST_ENCODED_TASK);
    }

    @Test
    public void loadFromString() {
        TaskTodo expectedTask = TaskTodoTest.getTestTask();
        TaskTodo loadedTask = TaskTodo.loadFromString(TaskTodoTest.TEST_ENCODED_TASK);
        assertEquals(loadedTask, expectedTask);
    }
}
