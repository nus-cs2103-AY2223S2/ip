package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.NoTaskDescriptionException;


public class TodoTest {

    @Test
    public void initialisationTest() {
        String actual = "";
        try {
            Todo todo = new Todo("Something to do");
            actual = todo.toString();

        } catch (NoTaskDescriptionException e) {
            actual = e.getMessage();
        }

        String expected = "[T] [ ] Something to do";
        assertEquals(expected, actual);
    }

    @Test
    public void nullNameTest() {

        String actual = "";
        try {
            Todo todo = new Todo("");
            actual = todo.toString();
        } catch (NoTaskDescriptionException e) {
            actual = e.getMessage();
        }

        String expected = "OOPS!!! The description of a ToDo task cannot be empty!";
        assertEquals(expected, actual);
    }
}
