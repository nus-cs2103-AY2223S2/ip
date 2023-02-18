package duke.tool;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import duke.task.Event;
import duke.task.Task;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parserTest() throws DukeException {
        DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Task> taskList = new ArrayList<>(100);
        taskList.add(new Event("project meeting ", LocalDateTime.parse("2020-08-30 18:00", validFormat).toString(),
                LocalDateTime.parse("2020-08-30 18:00", validFormat).toString()));
        ArrayList<Task> tl = new ArrayList<>(taskList);
        Parser.switch_input(taskList, );
        assertEquals(0, tl.size());
    }
}