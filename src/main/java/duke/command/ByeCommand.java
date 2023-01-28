package duke.command;

import duke.interfaces.Command;
import duke.interfaces.View;

public class ByeCommand implements Command {
    private final View taskView;
    private static final String exitMessage = "See you on the other side, human. Don't forget to bring a spacesuit!";
    ByeCommand(View taskView) {
        this.taskView = taskView;
    }
    @Override
    public void execute() {
        taskView.showMessage(exitMessage);
    }
}
