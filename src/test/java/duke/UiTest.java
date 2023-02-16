package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UiTest {
    private final Ui ui = new Ui();

    @Test
    public void testWelcomeMessage() {
        String output = ui.printWelcomeMessage();
        assertEquals("\t____________________________________________________________\n"
                + "\tHello! I'm NoDuKo\n"
                + "\tWhat can I not do for you?\n"
                + "\t____________________________________________________________",
                output);
    }

    @Test
    public void testByeMessage() {
        String output = ui.printByeMessage();
        assertEquals("\t____________________________________________________________\n"
                + "\tBye. Hope to not see you again soon!\n"
                + "\t____________________________________________________________",
                output);
    }

    @Test
    public void testTaskAddedMessage() {
        String output = ui.printTaskAdded(new Task("test"), 1);
        assertEquals("\tGot it. I've added this task:\n"
                + "\t\t[ ] test\n"
                + "\tNow you have 1 tasks in the list.\n \tThat's way too many.",
                output);
    }

    @Test
    public void testTaskMarkedMessage() {
        Task task = new Task("test");
        task.markAsDone();
        String output = ui.printTaskMarked(task);
        assertEquals("\tNice! I've marked this task as done:\n"
                + "\t\t[X] test",
                output);
    }

    @Test
    public void testTaskListMessage() {
        String output = ui.printTaskList(new TaskList());
        assertEquals("\tHere are the tasks in your list:",
                output);
    }

    @Test
    public void testTaskDeletedMessage() {
        String output = ui.printTaskDeleted(new Task("test"), 0);
        assertEquals("\tWhy? I've removed this task:\n"
                + "\t\t[ ] test\n"
                + "\tNow you have 0 tasks in the list.",
                output);
    }
}
