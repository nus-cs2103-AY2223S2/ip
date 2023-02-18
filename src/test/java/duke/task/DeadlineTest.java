package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void addDeadlineTest() {
        DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Task> taskList = new ArrayList<>(100);
        taskList.add(new Deadline("project meeting ", LocalDateTime.parse("2020-08-30 18:00", validFormat).toString()));
        ArrayList<Task> tl = new ArrayList<>(taskList);
        String eventToWrite = "E | " + '0' + " | project meeting | 2020-08-30 18:00";
        String actual = tl.get(0).toString();
        assertEquals(eventToWrite, actual);
    }
}

