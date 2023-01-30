package duke;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Event e = new Event("go to school", true,
                LocalDate.parse("2019-01-05"), LocalDate.parse("2019-01-08"));
        assertEquals(e.toString(), "[E][X] go to school (from: Jan 5 2019 to: Jan 8 2019)");
    }
}

