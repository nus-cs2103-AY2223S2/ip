package seedu.duke;

import duke.tasks.ToDos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testFetchingDescription() {
        ToDos toDoTask = new ToDos("return book");
        assertEquals("[T][ ] return book", toDoTask.toString());
    }

    @Test
    public void testMarking(){
        ToDos toDoTask = new ToDos("return book");
        toDoTask.mark();
        assertEquals("[T][X] return book", toDoTask.toString());
    }

}
