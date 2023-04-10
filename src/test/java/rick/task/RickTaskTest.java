package rick.task;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import rick.RickUtils;


/**
 * Represents a test suite that tests the static methods of the
 * {@code RickTask} class.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class RickTaskTest {
    /**
     * Represents a Stub to test the {@code RickTask} abstract class concrete
     * fields and methods.
     */
    static class TaskStub extends RickTask {
        /**
         * Constructs the test stub instance.
         */
        public TaskStub() {
            super("val");
        }

        /**
         * Generates and returns a boolean indicating if this task falls on
         * the given date.
         *
         * @param dt The given date.
         * @return The default value for a RickTask without a date.
         */
        @Override
        public boolean isOnDate(LocalDate dt) {
            return false;
        }
    }

    /**
     * Tests the {@code RickTask::fromDb} method.
     */
    @Test
    public void fromDb() {
        String event = "E|0|task one|2/2/23 0000|2/2/23 0001";
        String deadline = "D|0|task two|2/2/23 0001";
        String todo = "T|0|task three";
        String doneTodo = "T|1|task four";
        RickTask todoTask = new TodoTask("task four");
        todoTask.setDone();
        assertAll(() -> assertEquals(
                   new TodoTask("task three").toString(),
                   RickTask.fromDbSchema(todo).toString()
                ), () -> assertEquals(
                    new DeadlineTask("task two", RickUtils.parseDateTime("2/2/23 0001")).toString(),
                    RickTask.fromDbSchema(deadline).toString()
                ), () -> assertEquals(
                    new EventTask(
                            "task one",
                            RickUtils.parseDateTime("2/2/23 0000"),
                            RickUtils.parseDateTime("2/2/23 0001")
                            ).toString(),
                    RickTask.fromDbSchema(event).toString()
                ), () -> assertEquals(
                        todoTask.toString(),
                        RickTask.fromDbSchema(doneTodo).toString()
                )
        );
    }

    /**
     * Tests the {@code RickTask} class.
     */
    @Test
    public void testBaseData() {
        RickTask stub = new TaskStub();
        RickTask stubTwo = new TaskStub();
        stubTwo.setDone();
        assertAll(() -> assertEquals(
                        "[ ] val",
                        stub.toString()
                ), () -> assertFalse(
                        stub.isOnDate(RickUtils.parseDate("2/2/23"))
                ), () -> assertEquals(
                        "0|val",
                        stub.toDbSchema()
                ), () -> assertEquals(
                        "[X] val",
                        stubTwo.toString()
                )
        );
    }
}
