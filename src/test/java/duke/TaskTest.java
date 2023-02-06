package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void createNewToDoTask(){
        try {
            String newTodoTask = new Task().createNewTask("todo",
                    "read book").toString();
            assertEquals(newTodoTask, "[T][ ] read book");
        } catch (DukeExceptions ex) {
            System.out.println(ex.getErrorMessage());
        }
    }

    @Test
    public void createNewDeadlineTask(){
        try {
            String newDeadlineTask = new Task().createNewTask("deadline",
                    "return book /by 2023-02-10 18:00").toString();
            assertEquals(newDeadlineTask, "[D][ ] return book (by: Feb 10 2023 06:00 PM)");
        } catch (DukeExceptions ex) {
            System.out.println(ex.getErrorMessage());
        }
    }
}
