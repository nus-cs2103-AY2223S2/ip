package Duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeTest {
    @Test
    public void testCommandParsing() {
        assertEquals("TODO", new Duke().getCommand("todo homework").toString());
    }
    @Test
    public void testParameterParsing() {
        String[] parameters = {"1","2","3"};
        assertEquals(parameters[0], new Duke().getParameters("todo 1 2 3")[0]);
        assertEquals(parameters[1], new Duke().getParameters("todo 1 2 3")[1]);
        assertEquals(parameters[2], new Duke().getParameters("todo 1 2 3")[2]);
    }

}