package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void deadline_marked() {
        Deadline deadLine = new Deadline("Do homework", "2022-01-29 23:59");
        deadLine.toggleDone();
        assertEquals("deadline Do homework /by 29 January 2022 23:59\nmark 6",
                deadLine.getRecreateCommand(6));
    }
}
