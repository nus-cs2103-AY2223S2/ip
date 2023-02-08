package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ui.Ui;

public class UiTest {
    private final Ui ui = new Ui();


    @Test
    public void testWelcomeMessage() {
        String output = ui.printWelcomeMessage();
        assertEquals("\t____________________________________________________________\n"
                        + "\tHello! I'm NoDuKo\n"
                        + "\tWhat can I do for you?\n"
                        + "\t____________________________________________________________",
                output);
    }

    @Test
    public void testByeMessage() {
        String output = ui.printByeMessage();
        assertEquals("\t____________________________________________________________\n"
                        + "\tBye. Hope to see you again soon!\n"
                        + "\t____________________________________________________________",
                output);
    }

}
