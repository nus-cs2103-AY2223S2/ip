package duke.instruction;

import duke.customization.DisplayFormat;
import duke.exception.GeneralDukeException;
import duke.task.TaskList;

public class EchoInstruction extends GeneralDukeInstruction {
    private final String echoMessage;
    private static final DisplayFormat format = new DisplayFormat(50, 4);

    public EchoInstruction(String echoMessage) {
        this.echoMessage = echoMessage;
    }

    @Override
    public void run(TaskList list) throws GeneralDukeException {
        format.displayWithBar(this.echoMessage);
    }
}
