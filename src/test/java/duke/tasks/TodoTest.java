package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoTest() {
        assertEquals("[T][] bring books",
                new Todo("bring books").toString());
    }
}
