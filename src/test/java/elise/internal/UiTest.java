package elise.internal;

import elise.tasks.Task;
import elise.tasks.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    // Border used for testing
    private String BORDER = "-----------------------------------------------";

    @Test
    public void test1() {
        Ui ui = new Ui();
        assertEquals(ui.wrapText("message here"), BORDER + "\n" + "message here\n" + BORDER);
    }
    @Test
    public void test2() {
        Task task = new ToDo(true, new String[] {"message"});
        Ui ui = new Ui();
        assertEquals(ui.markDoneMessage(task), BORDER + "\n"
                        + "Nice! I've marked this task as done:\n"
                + "[T][X] message\n" + BORDER);
    }


}
