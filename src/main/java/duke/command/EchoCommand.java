package duke.command;

import duke.interfaces.Command;
import duke.interfaces.View;

public class EchoCommand implements Command {
    private final View taskView;
    private final String echoString;
    EchoCommand(View taskView, String echoString) {
        this.echoString = echoString;
        this.taskView = taskView;
    }

    @Override
    public void execute() {
        taskView.showMessage("You said: " + echoString);
    }
}
