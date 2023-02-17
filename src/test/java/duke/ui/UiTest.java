package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    private final Ui ui = new Ui();

    @Test
    public void indent_simpleString_indentationAdded() {
        String input = "Hello World!";
        String expected = "    Hello World!";
        assertEquals(expected, ui.indent(input));
    }

    @Test
    public void indent_stringWithMultipleLines_indentationAddedToEachLine() {
        String input = "Hello\nWorld!\nHow are you?";
        String expected = "    Hello\n    World!\n    How are you?";
        assertEquals(expected, ui.indent(input));
    }
    @Test
    public void getResponse_noAppendCalls_emptyString() {
        String expected = "";
        assertEquals(expected, ui.getResponse());
    }

    @Test
    public void getResponse_appendCalledOnce_appendedString() {
        ui.appendResponse("Hello World!");
        String expected = "Hello World!";
        assertEquals(expected, ui.getResponse());
    }

    @Test
    public void getResponse_appendCalledMultipleTimes_appendedStringsConcatenated() {
        ui.appendResponse("Hello ");
        ui.appendResponse("World!");
        String expected = "Hello World!";
        assertEquals(expected, ui.getResponse());
    }

    @Test
    public void reset_appendCalledMultipleTimes_responseStringBuilderCleared() {
        ui.appendResponse("Hello World!");
        ui.reset();
        String expected = "";
        assertEquals(expected, ui.getResponse());
    }
}
