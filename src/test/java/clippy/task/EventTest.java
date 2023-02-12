package clippy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    Event createTestEvent() {
        return new Event("Lunar New Year",
                LocalDate.of(2023, 1, 22),
                LocalDate.of(2023, 2, 6));
    }
    @Test
    void toStringFormatTest() {
        Event event = createTestEvent();
        assertEquals("[E][ ] Lunar New Year (Priority: NONE) "
                + "(from: Sun 22 Jan to: Mon 06 Feb)", event.toString());
    }

    @Test
    void getCsvStringTest() {
        Event event = createTestEvent();
        assertEquals("E,Lunar New Year,false,NONE,2023-01-22,2023-02-06", event.getCsvString());
    }
}
