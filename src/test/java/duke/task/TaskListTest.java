package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.ui.Ui;

public class TaskListTest {
    private TaskList taskList = new TaskList();
    @BeforeEach
    public void init() {
        taskList.initAdd(new ToDo("Thing"));
        taskList.initAdd(new Deadline("Next Thing",
                "02/12/2019 1730", true));
        taskList.initAdd(new Event("Last thing",
                "02/12/2019 1730", "03/12/2019 1730"));

    }

    @Test
    public void addTasks() {
        String expected = "\t 1. [T][ ] Thing\n"
                + "\t 2. [D][X] Next Thing (by: 1730 Dec 2 2019)\n"
                + "\t 3. [E][ ] Last thing (from: 1730 Dec 2 2019 to: 1730 Dec 3 2019)\n";
        assertEquals(taskList.toString(), expected);
    }

    @Test
    public void toData() {
        String expected = "[T] | 0 | Thing\n"
                + "[D] | 1 | Next Thing | 02/12/2019 1730\n"
                + "[E] | 0 | Last thing | 02/12/2019 1730-03/12/2019 1730\n";
        assertEquals(taskList.itemsToData(), expected);
    }

    @Test
    public void markAndUnmark() throws DukeException {
        Ui ui = new Ui();
        taskList.mark(new String[]{"mark", "3"}, ui);
        taskList.unmark(new String[]{"unmark", "2"}, ui);

        String expected = "[T] | 0 | Thing\n"
                + "[D] | 0 | Next Thing | 02/12/2019 1730\n"
                + "[E] | 1 | Last thing | 02/12/2019 1730-03/12/2019 1730\n";

        assertEquals(taskList.itemsToData(), expected);
    }
}
