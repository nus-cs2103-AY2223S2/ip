package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static duke.utils.FormatHelper.INPUTFORMAT;
import static duke.utils.FormatHelper.PRINTFORMAT;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    @Test
    public void testT0SaveFormat() {
        LocalDateTime by = LocalDateTime.parse("22/02/2023 13:00", INPUTFORMAT);
        Deadline deadline = new Deadline("Clean the room", by);
        assertEquals("D||0||Clean the room||22/02/2023 13:00", deadline.toSaveFormat());
    }

    @Test
    public void testFromSaveFormat() {
        Deadline deadline = Deadline.fromSaveFormat("D||0||Clean the room||22/02/2023 13:00");
        assertEquals("[D][ ] Clean the room (by: 22 Feb 2023 13:00)", deadline.toString());
    }
}