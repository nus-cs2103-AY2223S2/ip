package duke.commands.taskCommand;

import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskCommandTest {
    @Test
    public void testRegularGetTask() {
        Duke instance = new Duke();
        TaskCommand<Task> test = new TaskCommand<Task>("") {
            @Override
            public Task getTask(String[] tokens, Duke instance) {
                return new Task("") {
                    public String getType() { 
                        return "X";
                    }
                };
            }
        };

        try {
            test.accept(null, instance);
        } catch (Exception e) {
            fail();
        }

        assertEquals(1, instance.getTaskList().size());
        assertEquals("X", instance.getTaskList().get(0).getType());
    }

    @Test
    public void testFailGetTask() {
        TaskCommand<Task> test = new TaskCommand<Task>("") {
            @Override
            public Task getTask(String[] tokens, Duke instance) throws ValidationException {
                validate(false, "test message");
                return new Task("") {
                    public String getType() { 
                        return "X"; 
                    }
                };
            }
        };

        Duke instance = new Duke();
        try {
            test.accept(null, instance);
        } catch (Exception e) {
            fail();
        }

        assertEquals(0, instance.getTaskList().size(), "Tasks are not added when validate fails");
    }
}
