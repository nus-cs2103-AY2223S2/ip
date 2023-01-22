package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class TaskTest {
    @Test
    public void testTodo(){
        Task task = new Todo("hello");
        assertEquals(task.toString(), "[T][ ] hello");
    }

    @Test
    public void testDeadline(){
        Task task = new Deadline("hello", LocalDate.of(2022,8,30));
        task.mark(true);
        assertEquals(task.toString(), "[D][X] hello (by: 2022-08-30)");
    }
    @Test
    public void testEvent(){
        Task task = new Event("hello", LocalDate.of(2022,8,30), LocalDate.of(2022,9,30));
        task.mark(true);
        assertEquals(task.toString(), "[E][X] hello (from: 2022-08-30 to 2022-09-30)");
    }
}