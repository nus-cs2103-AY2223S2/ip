package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void createFromStorage_validArgs_returnsToDo() throws DukeException {
        String expected = "[T][X] Hello world!";

        String[] args = new String[] {"T", "true", "Hello world!"};

        ToDo toDo = ToDo.createFromStorage(args);

        Assertions.assertEquals(toDo.toString(), expected);
    }

    @Test
    public void createFromStorage_missingArgs_throwsDukeException() {
        String[] args = new String[] {"T", "true"};

        Assertions.assertThrows(DukeException.class, () -> {
            ToDo.createFromStorage(args);
        });
    }

    @Test
    public void createFromStorage_invalidDoneStatus_throwsDukeException() {
        String[] args = new String[] {"T", "lorem ipsum", "Hello world!"};

        Assertions.assertThrows(DukeException.class, () -> {
            ToDo.createFromStorage(args);
        });
    }

    @Test
    public void getStorageString_descriptionDoesNotContainDelimiter_returnsStorageString() {
        String expected = "T | false | Hello world!";

        ToDo toDo = new ToDo(false, "Hello world!");
        String actual = toDo.getStorageStr();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getStorageString_descriptionContainsDelimiter_returnsStorageString() {
        String expected = "T | false | Hello \\| world!";

        ToDo toDo = new ToDo(false, "Hello | world!");
        String actual = toDo.getStorageStr();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void createCopy_normal_returnsACopy() {
        ToDo original = new ToDo(false, "Hello world!");
        Task copy = original.createCopy();

        Assertions.assertNotSame(original, copy);
        Assertions.assertEquals(original.toString(), copy.toString());
    }
}
