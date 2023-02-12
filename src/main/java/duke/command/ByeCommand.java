package duke.command;

import duke.interfaces.Command;
import duke.interfaces.View;

public class ByeCommand implements Command {
    private static final String EXIT_MESSAGE = "See you on the other side, human. Don't forget to bring a spacesuit!";
    private final View taskView;
    ByeCommand(View taskView) {
        this.taskView = taskView;
    }
    @Override
    public void execute() {
        taskView.showMessage(EXIT_MESSAGE);
    }
}
