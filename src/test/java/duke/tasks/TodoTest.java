package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest () {
        assertEquals("[T][] bring books",
                new Todo("bring books").toString());
    }
}
