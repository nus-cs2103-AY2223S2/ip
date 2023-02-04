package duke.Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void addToDoTest() {
        try {
            ToDo newToDo = new ToDo("exercise");
            assertEquals("T |   | exercise", newToDo.toString());
        } catch (Exception ignored) {}
    }

    @Test
    public void markToDoTest() {
        try {
            ToDo newToDo = new ToDo("do work");
            newToDo.markDone();
            assertEquals("T | X | do work", newToDo.toString());
        } catch (Exception ignored) {}

    }
}