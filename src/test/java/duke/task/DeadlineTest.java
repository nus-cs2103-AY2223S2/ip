package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.tool.Parser;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void addDeadlineTest() {
        ArrayList<Task> taskList = new ArrayList<>(100);
        taskList.add(new Deadline("project meeting", Parser.parse_date("2020-08-30 18:00")));
        ArrayList<Task> tl = new ArrayList<>(taskList);
        String eventToWrite = "[D][ ] project meeting (by: Aug 30 2020, 06:00 PM)";
        String actual = tl.get(0).toString();
        assertEquals(eventToWrite, actual);
    }
}

