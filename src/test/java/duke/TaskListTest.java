package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testList() {
        TaskList list = new TaskList(new ArrayList<>());
        list.addTodo("eat");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse("2021-11-12 09:00", formatter);
        list.addDeadline("dance", date);

        System.out.println(list.toString());
        assertEquals(list.toString(), "1. [T][ ] eat\n2. [D][ ] dance (by: Nov 12 2021 09:00)\n");


    }
}
