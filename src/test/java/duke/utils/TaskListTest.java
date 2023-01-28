package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    @DisplayName("Initialized TaskList should contain an ArrayList of size 0.")
    void testEmptyTaskListInitialization() {
        assertEquals(0, this.taskList.size(),
                "There is a problem with initialization of TaskList");
    }

}
