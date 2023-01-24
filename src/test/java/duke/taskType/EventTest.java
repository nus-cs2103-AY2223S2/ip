package duke.taskType;

import duke.taskType.*;
import duke.*;
import duke.commands.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void Eventtest() {
        Event e = new Event("lash","lapapa","papala");
        assertEquals("[E][ ]lash (from: lapapa to: papala)", e.toString());
    }
}