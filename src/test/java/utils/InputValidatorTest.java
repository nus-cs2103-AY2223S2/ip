package utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import membot.utils.InputValidator;
import membot.utils.InvalidCommandException;

public class InputValidatorTest {
    @Test
    public void isSingleInputValidTest() {
        String input1 = "done 1";
        String input2 = "done one";
        String input3 = "done 1 2";
        String input4 = "done (1)";
        String input5 = "i have done task 1";
        String input6 = "undone 1";
        String input7 = "undone two";
        String input8 = "undone 6 7";
        String input9 = "delete 9";
        String input10 = "delete 0";
        String input11 = "somerandomcommand 0";
        String input12 = "find something";
        String input13 = "find something i havent do";
        String input14 = "find";

        assertTrue(InputValidator.isSingleInputValid(input1, false, true));
        assertFalse(InputValidator.isSingleInputValid(input2, false, true));
        assertFalse(InputValidator.isSingleInputValid(input3, false, true));
        assertFalse(InputValidator.isSingleInputValid(input4, false, true));
        assertFalse(InputValidator.isSingleInputValid(input5, false, true));
        assertTrue(InputValidator.isSingleInputValid(input6, false, true));
        assertFalse(InputValidator.isSingleInputValid(input7, false, true));
        assertFalse(InputValidator.isSingleInputValid(input8, false, true));
        assertTrue(InputValidator.isSingleInputValid(input9, false, true));
        assertTrue(InputValidator.isSingleInputValid(input10, false, true));
        assertTrue(InputValidator.isSingleInputValid(input11, false, true));
        assertTrue(InputValidator.isSingleInputValid(input12, true, false));
        assertTrue(InputValidator.isSingleInputValid(input13, true, false));
        assertFalse(InputValidator.isSingleInputValid(input14, true, false));
    }

    @Test
    public void normaliseTodoInputTest() {
        String input1 = "todo test";
        String input2 = "todo test /by tmr";
        String input3 = "todo test /from tmr /to 2 days later";
        String input4 = "todo";
        String input5 = "todoo test";
        String input6 = "todo test item one two three       ";
        String input7 = "tdo";

        assertDoesNotThrow(() -> {
            InputValidator.normaliseTodoInput(input1);
            assertArrayEquals(new String[]{"todo", "test"}, InputValidator.normaliseTodoInput(input1));
        });

        InvalidCommandException e2 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseTodoInput(input2));
        assertEquals("[Invalid command] There should not be any reserved keywords", e2.getMessage());

        InvalidCommandException e3 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseTodoInput(input3));
        assertEquals("[Invalid command] There should not be any reserved keywords", e3.getMessage());

        InvalidCommandException e4 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseTodoInput(input4));
        assertEquals("[Invalid command] Unable to normalise input as a Todo input", e4.getMessage());

        assertDoesNotThrow(() -> {
            InputValidator.normaliseTodoInput(input5);
            assertArrayEquals(new String[]{"todo", " test"}, InputValidator.normaliseTodoInput(input5));
        });

        assertDoesNotThrow(() -> {
            InputValidator.normaliseTodoInput(input6);
            assertArrayEquals(new String[]{"todo", "test item one two three       "},
                    InputValidator.normaliseTodoInput(input6));
        });

        InvalidCommandException e7 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseTodoInput(input7));
        assertEquals("[Invalid command] Unable to normalise input as a Todo input", e7.getMessage());
    }

    @Test
    public void normaliseDeadlineInputTest() {
        String input1 = "deadline test deadline task /by tomorrow";
        String input2 = "deadline test deadline task by tomorrow";
        String input3 = "deadline";
        String input4 = "deadline /by tmr";
        String input5 = "deadline /by tmr 2pm";
        String input6 = "deadline   /by tmr";
        String input7 = "deadline  /by tmr";
        String input8 = "deadline test deadline task /by tomorrow /from 2pm /to 4pm";
        String input9 = "deadline test deadline task /from 2pm /to 4pm";
        String input10 = "deadline test deadline task /from 2pm";
        String input11 = "deadlin test deadline task /by tomorrow";

        assertDoesNotThrow(() -> {
            InputValidator.normaliseDeadlineInput(input1);
            assertArrayEquals(new String[]{"deadline", "test deadline task", "tomorrow"},
                    InputValidator.normaliseDeadlineInput(input1));
        });

        InvalidCommandException e2 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseDeadlineInput(input2));
        assertEquals("[Invalid command] Missing /by keyword in input!", e2.getMessage());

        InvalidCommandException e3 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseDeadlineInput(input3));
        assertEquals("[Invalid command] Invalid Syntax - "
                        + "\"deadline [title] /by [deadline]\" (e.g. \"deadline physics project /by tomorrow 3pm\"",
                e3.getMessage());

        InvalidCommandException e4 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseDeadlineInput(input4));
        assertEquals("[Invalid command] Invalid Syntax - "
                        + "\"deadline [title] /by [deadline]\" (e.g. \"deadline physics project /by tomorrow 3pm\"",
                e4.getMessage());

        InvalidCommandException e5 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseDeadlineInput(input5));
        assertEquals("[Invalid command] Empty title",
                e5.getMessage());

        assertDoesNotThrow(() -> {
            InputValidator.normaliseDeadlineInput(input6);
            assertArrayEquals(new String[]{"deadline", " ", "tmr"},
                    InputValidator.normaliseDeadlineInput(input6));
        });

        assertDoesNotThrow(() -> {
            InputValidator.normaliseDeadlineInput(input7);
            assertArrayEquals(new String[]{"deadline", "", "tmr"},
                    InputValidator.normaliseDeadlineInput(input7));
        });

        InvalidCommandException e8 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseDeadlineInput(input8));
        assertEquals("[Invalid command] Too many keywords in input!",
                e8.getMessage());

        InvalidCommandException e9 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseDeadlineInput(input9));
        assertEquals("[Invalid command] Too many keywords in input!",
                e9.getMessage());

        InvalidCommandException e10 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseDeadlineInput(input10));
        assertEquals("[Invalid command] Missing /by keyword in input!",
                e10.getMessage());

        assertDoesNotThrow(() -> {
            InputValidator.normaliseDeadlineInput(input11);
            assertArrayEquals(new String[]{"deadlin ", "est deadline task", "tomorrow"},
                    InputValidator.normaliseDeadlineInput(input11));
        });
    }

    @Test
    public void normaliseEventInputTest() {
        String input1 = "event piano concert /from 2pm /to 4pm";
        String input2 = "event piano concert /to 4pm /from 2pm";
        String input3 = "event piano concert /from 2pm";
        String input4 = "event piano concert /to 4pm";
        String input5 = "event piano concert /to tomorrow 4pm";
        String input6 = "event piano concert";
        String input7 = "event piano concert /by tmr /from 2pm /to 4pm";
        String input8 = "event /from 2pm /to 4pm";
        String input9 = "event /to 2pm /from 4pm";
        String input10 = "event /to 2pm /from tomrrow 4pm";
        String input11 = "event /from 2pm";
        String input12 = "event /to 4pm";
        String input13 = "even piano concert /from 2pm /to 4pm";
        String input14 = "event piano concert /by tmr";
        String input15 = "event   /from 2pm /to 4pm";

        assertDoesNotThrow(() -> {
            InputValidator.normaliseEventInput(input1);
            assertArrayEquals(new String[]{"event", "piano concert", "2pm", "4pm"},
                    InputValidator.normaliseEventInput(input1));
        });

        assertDoesNotThrow(() -> {
            InputValidator.normaliseEventInput(input2);
            assertArrayEquals(new String[]{"event", "piano concert", "2pm", "4pm"},
                    InputValidator.normaliseEventInput(input2));
        });

        InvalidCommandException e3 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseEventInput(input3));
        assertEquals("[Invalid command] Invalid syntax", e3.getMessage());

        InvalidCommandException e4 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseEventInput(input4));
        assertEquals("[Invalid command] Invalid syntax", e4.getMessage());

        InvalidCommandException e5 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseEventInput(input5));
        assertEquals("[Invalid command] There should only have /from and /to keyword", e5.getMessage());

        InvalidCommandException e6 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseEventInput(input6));
        assertEquals("[Invalid command] Invalid syntax", e6.getMessage());

        InvalidCommandException e7 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseEventInput(input7));
        assertEquals("[Invalid command] There should only have /from and /to keyword", e7.getMessage());

        InvalidCommandException e8 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseEventInput(input8));
        assertEquals("[Invalid command] Invalid syntax", e8.getMessage());

        InvalidCommandException e9 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseEventInput(input9));
        assertEquals("[Invalid command] Invalid syntax", e9.getMessage());

        InvalidCommandException e10 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseEventInput(input10));
        assertEquals("[Invalid command] Empty title", e10.getMessage());

        InvalidCommandException e11 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseEventInput(input11));
        assertEquals("[Invalid command] Invalid syntax", e11.getMessage());

        InvalidCommandException e12 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseEventInput(input12));
        assertEquals("[Invalid command] Invalid syntax", e12.getMessage());

        assertDoesNotThrow(() -> {
            InputValidator.normaliseEventInput(input13);
            assertArrayEquals(new String[]{"even ", "iano concert", "2pm", "4pm"},
                    InputValidator.normaliseEventInput(input13));
        });

        InvalidCommandException e14 = assertThrows(InvalidCommandException.class, () ->
                InputValidator.normaliseEventInput(input14));
        assertEquals("[Invalid command] Invalid syntax", e14.getMessage());

        assertDoesNotThrow(() -> {
            InputValidator.normaliseEventInput(input15);
            assertArrayEquals(new String[]{"event", " ", "2pm", "4pm"},
                    InputValidator.normaliseEventInput(input15));
        });
    }
}
