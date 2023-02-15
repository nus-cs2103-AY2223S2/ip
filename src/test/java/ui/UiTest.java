package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

public class UiTest {

    private final Ui ui = new Ui();

    @Test
    public void getResponse() {
        String expectedResponse = "123";
        this.ui.setResponse("123");
        String actualResponse = this.ui.getResponse();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getTaskToDisplay_populatedList() {
        List<Task> expectedList = getPopulatedTaskList();
        this.ui.setTasksToDisplay(getPopulatedTaskList());
        List<Task> actualList = this.ui.getTasksToDisplay();
        assertEquals(expectedList.toString(), actualList.toString());
    }

    @Test
    public void greet() {
        String expectedMessage = "Hello I'm\n" + Duke.LOGO + "What can I do for you?\n\n" + expectedCommand();
        String actualMessage = this.ui.greet();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void showCommand() {
        String expectedMessage = expectedCommand();
        String actualMessage = this.ui.showCommands();
        assertEquals(expectedMessage, actualMessage);

    }

    private List<Task> getPopulatedTaskList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("1"));
        taskList.add(new Deadline("2",
                LocalDateTime.parse("2024-01-01 01:01", formatter)));
        taskList.add(new Event("3",
                LocalDateTime.parse("2024-01-01 01:01", formatter),
                LocalDateTime.parse("2024-02-02 02:02", formatter)));
        return taskList;
    }

    private String expectedCommand() {
        return "MY COMMANDS ARE:\n"
                + "01. ADD TODO TASK:       todo [descr]\n"
                + "02. ADD DEADLINE TASK:   deadline [descr] /by [YYYY-MM-DD HH:MM]\n"
                + "03. ADD EVENT TASK:      event [descr] /from [YYYY-MM-DD HH:MM] /to [YYYY-MM-DD HH:MM]\n"
                + "04. MARK A TASK:         mark [index]\n"
                + "05. UNMARK A TASK:       unmark [index]\n"
                + "06. DELETE A TASK:       delete [index]\n"
                + "07. DISPLAY COMMANDS:    help\n"
                + "08. LIST ALL TASKS:      list\n"
                + "09. DISPLAY COMMANDS:    help\n"
                + "10. EXIT PROGRAM:        bye";
    }
}
