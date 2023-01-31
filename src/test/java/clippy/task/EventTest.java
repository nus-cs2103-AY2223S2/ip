package clippy.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event createTestEvent() {
        return new Event("Lunar New Year",
                LocalDate.of(2023, 1, 22),
                LocalDate.of(2023, 2, 6));
    }
    @Test
    void toStringFormatTest() {
        Event event = createTestEvent();
        assertEquals("[E][ ] Lunar New Year (from: Sun 22 Jan to: Mon 06 Feb)", event.toString());
    }

    @Test
    void getCsvStringTest() {
        Event event = createTestEvent();
        assertEquals("E,Lunar New Year,false,2023-01-22,2023-02-06", event.getCsvString());
    }
}
