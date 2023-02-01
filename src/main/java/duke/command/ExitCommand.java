package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    public String execute(TaskList l, Ui ui, Storage s) {
        try {
            s.save(l);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
