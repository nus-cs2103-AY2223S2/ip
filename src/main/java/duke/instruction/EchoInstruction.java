package duke.instruction;

import duke.task.TaskList;

/*
 * A DukeInstruction which simple repeat the user's input
 * Obsolete after level-2
 */

public class EchoInstruction extends GeneralDukeInstruction {
    private final String echoMessage;

    /**
     * Constructor for EchoInstruction that takes in the message to be echoed.
     *
     * @param echoMessage The message to be echoed.
     */
    public EchoInstruction(String echoMessage) {
        this.echoMessage = echoMessage;
    }

    /**
     * Displays the given message in the customized way.
     *
     * @param list The user TaskList that contains all the task to be manipulated
     */
    @Override
    public void run(TaskList list) {
        format.displayWithBar(this.echoMessage);
    }
}
