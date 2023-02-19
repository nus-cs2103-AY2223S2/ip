package duke.task;

import duke.tool.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void addEventTest() {
        ArrayList<Task> taskList = new ArrayList<>(100);
        taskList.add(new Event("project meeting", Parser.parse_date("2020-08-30 18:00"),
                Parser.parse_date("2020-08-30 18:00")));
        String eventToWrite = "[E][ ] project meeting (from: Aug 30 2020, 06:00 PM to Aug 30 2020, 06:00 PM)";
        String actual = taskList.get(0).toString();
        assertEquals(eventToWrite, actual);
    }
}
