package duke.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DateTimeTest {
    @Test
    public void dateTime() {
        DateTime dateTime = new DateTime("2022-01-02 0800");
        assertEquals("02 01 2022 08:00", dateTime.toString());
    }
}
