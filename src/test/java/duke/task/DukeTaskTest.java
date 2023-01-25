package duke.task;

import duke.DukeUtils;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DukeTaskTest {
    static class TaskStub extends DukeTask {
        public TaskStub() {
            super("val");
        }
        @Override
        public boolean isOnDate(LocalDate dt) {
            return false;
        }
    }

    @Test
    public void fromDB() {
        String event = "E|0|task one|2/2/23 0000|2/2/23 0001";
        String deadline = "D|0|task two|2/2/23 0001";
        String todo = "T|0|task three";
        String doneTodo = "T|1|task four";
        DukeTask todoTask = new TodoTask("task four");
        todoTask.setDone();
        assertAll(
                () -> assertEquals(
                   new TodoTask("task three").toString(),
                   DukeTask.fromDBSchema(todo).toString()
                ),
                () -> assertEquals(
                    new DeadlineTask("task two", DukeUtils.parseDateTime("2/2/23 0001")).toString(),
                    DukeTask.fromDBSchema(deadline).toString()
                ),
                () -> assertEquals(
                    new EventTask(
                            "task one",
                            DukeUtils.parseDateTime("2/2/23 0000"),
                            DukeUtils.parseDateTime("2/2/23 0001")
                            ).toString(),
                    DukeTask.fromDBSchema(event).toString()
                ),
                () -> assertEquals(
                        todoTask.toString(),
                        DukeTask.fromDBSchema(doneTodo).toString()
                )
        );
    }

    @Test
    public void testBaseData() {

        DukeTask stub = new TaskStub();
        DukeTask stub2 = new TaskStub();
        stub2.setDone();
        assertAll(
                () -> assertEquals(
                        "[ ] val",
                        stub.toString()
                ),
                () -> assertFalse(
                        stub.isOnDate(DukeUtils.parseDate("2/2/23"))
                ),
                () -> assertEquals(
                        "0|val",
                        stub.toDBSchema()
                ),
                () -> assertEquals(
                        "[X] val",
                        stub2.toString()
                )
        );
    }
}
