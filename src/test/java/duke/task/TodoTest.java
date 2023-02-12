package duke.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class TodoTest {
    @Test
    public void createNewTodo() {
        String expected = "[T][] Test Todo";
        Todo test = new Todo("Test Todo");
        Assertions.assertEquals(test.toString(), expected);
    }
    @Test
    public void todoToRecord() {
        String expected = "T|0|Do homework";
        Todo test = new Todo("Do homework");
        Assertions.assertEquals(test.toRecord(), expected);
    }
}
