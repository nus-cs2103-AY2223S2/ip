package pix;

import org.junit.jupiter.api.Test;
import pix.tasks.ToDo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] tostringtest", new ToDo("tostringtest").toString());
    }

    @Test
    public void toSaveTest() {
        assertEquals("T / 0 / tosavetest", new ToDo("tosavetest").toSave());
    }
}