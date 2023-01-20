package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventsTest {
    @Test
    public void createEvent() {
        Events created = new Events("Aespa Concert", "2023-06-10T00:00", "2023-06-11T23:59");
        assertEquals("[E][ ] Aespa Concert (from: Jun 10 2023 0000 to: Jun 11 2023 2359)", created.toString());
    }

    @Test
    public void markEvent() {
        Events created = new Events("Aespa Concert", "2023-06-10T00:00", "2023-06-11T23:59");
        created.mark();
        assertEquals("[E][X] Aespa Concert (from: Jun 10 2023 0000 to: Jun 11 2023 2359)", created.toString());
    }

    @Test
    public void saveEvent() {
        Events created = new Events("Aespa Concert", "2023-06-10T00:00", "2023-06-11T23:59");
        assertEquals("E | 0 | Aespa Concert | 2023-06-10T00:00 | 2023-06-11T23:59\n", created.toWrite());
    }

    @Test
    public void isWithinTest() {
        Events created = new Events("Aespa Concert", "2023-06-10T00:00", "2023-06-11T23:59");
        assertEquals(false, created.isWithinDate(LocalDateTime.parse("2023-01-24T00:00")));
    }
}
