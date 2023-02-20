package duke.tool;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

public class ParserTest {
    @Test
    public void switchInputTest() throws DukeException {
        Ui ui = new Ui();
        DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Task> taskList = new ArrayList<>(100);
        taskList.add(new Event("project meeting ", LocalDateTime.parse("2020-08-30 18:00", validFormat).toString(),
                LocalDateTime.parse("2020-08-30 20:00", validFormat).toString()));
        ArrayList<Task> tl = new ArrayList<>(100);
        Parser.switch_input(tl, "event project meeting /from 2020-08-30 18:00 /to 2020-08-30 20:00", ui);
        assertEquals(taskList.size(), tl.size());
    }
}
