package duke.taskType;

import duke.taskType.*;
import duke.*;
import duke.commands.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void Deadlinetest() {
        Deadline t = new Deadline("lash","2023-04-26 11:00");
        assertEquals("[D][ ]lash (by: 04 26 2023 11:00)", t.toString());
    }
}