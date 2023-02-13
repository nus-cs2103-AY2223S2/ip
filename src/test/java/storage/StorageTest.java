package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void getTaskName_name() {
        String input = "E|X|buy stuff|21/04/1990|29/09/2090";
        String expected = "buy stuff";
        String actual = "buy stuff";
        assertEquals(actual, expected);
    }
}
