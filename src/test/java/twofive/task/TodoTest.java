package twofive.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toDoStringTest() {
        ToDo toDo = new ToDo("return book");
        assertEquals("[T][ ] return book", toDo.toString());
    }

    @Test
    public void toDoFileStringTest() {
        ToDo toDo = new ToDo("return book");
        assertEquals("T | 0 | return book", toDo.getFileWriteString());
    }

    @Test
    public void toDoIsTodayTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime deadline = LocalDateTime.parse("2023-06-06 06:06", formatter);
        ToDo toDo = new ToDo("return book");
        LocalDate differentDate = LocalDate.parse("2023-01-23", dateOnlyFormatter);
        assertEquals(false, toDo.isToday(differentDate));
    }
}
