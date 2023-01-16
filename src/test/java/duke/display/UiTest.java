package duke.display;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {

    @Test
    void indent() {
        Ui ui = new Ui();
        String actual = ui.indent("1\n2\n3");
        String expect = ("    1\n    2\n    3");
        assertEquals(actual, expect);
    }
}