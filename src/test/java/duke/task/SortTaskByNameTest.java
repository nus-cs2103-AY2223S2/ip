package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTaskByNameTest {
    @Test
    public void compareDeadlineAndTodoTest() {
        Task a = new Deadline("return book ", "2019-06-15");
        Task b = new Todo("return book");
        int num = a.toString().compareTo(b.toString());
        num = num / Math.abs(num);
        assertEquals(-1, num);
    }

    @Test
    public void compareDeadlineAndEventTest() {
        Task a = new Deadline("return book ", "2019-06-15");
        Task b = new Event("project meeting ", "2019-06-15", "2019-06-19");
        int num = a.toString().compareTo(b.toString());
        num = num / Math.abs(num);
        assertEquals(-1, num);
    }

    @Test
    public void compareTodoAndEventTest() {
        Task a = new Todo("return book");
        Task b = new Event("project meeting ", "2019-06-15", "2019-06-19");
        int num = a.toString().compareTo(b.toString());
        num = num / Math.abs(num);
        assertEquals(1, num);
    }

    @Test
    public void compareTodoAndTodoTest() {
        Task a = new Todo("return book");
        Task b = new Todo("homework");
        int num = a.toString().compareTo(b.toString());
        num = num / Math.abs(num);
        assertEquals(1, num);
    }

    @Test
    public void compareDeadlineDifferentDatesTest() {
        Task a = new Deadline("return book ", "2019-06-15");
        Task b = new Deadline("return book ", "2019-06-14");
        int num = a.toString().compareTo(b.toString());
        num = num / Math.abs(num);
        assertEquals(1, num);
    }

}
