package task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void shouldMarkTaskAsCompleted() {
        ConcreteTask falsyTask = new ConcreteTask("demo", false);
        ConcreteTask truthyTask = new ConcreteTask("demo", true);

        falsyTask.markCompleted();
        truthyTask.markCompleted();

        boolean[] actual = {
            falsyTask.getIsCompleted(),
            truthyTask.getIsCompleted()
        };

        boolean[] expected = {
            true,
            true
        };

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMarkTaskAsUncompleted() {
        ConcreteTask falsyTask = new ConcreteTask("demo", false);
        ConcreteTask truthyTask = new ConcreteTask("demo", true);

        falsyTask.unmarkCompleted();
        truthyTask.unmarkCompleted();

        boolean[] actual = {
            falsyTask.getIsCompleted(),
            truthyTask.getIsCompleted()
        };

        boolean[] expected = {
            false,
            false
        };

        assertArrayEquals(expected, actual);
    }

    public class ConcreteTask extends Task {
        public ConcreteTask(String task, boolean isCompleted) {
            super(task, isCompleted);
        }

        @Override
        public String toDataString() {
            return "";
        }
    }
}
