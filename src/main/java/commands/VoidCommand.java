package commands;

import java.io.IOException;
import java.util.ArrayList;

import dukeexceptions.DukeException;
import dukeexceptions.IllegalCommandException;
import elems.Storage;
import elems.TaskList;
import elems.Ui;

public class VoidCommand extends Command {
    enum VoidType {
        LIST,
        BYE,
        FORCEQUIT
    }

    private VoidType voidType;

    public VoidCommand(String keyword, ArrayList<String> params) throws IllegalCommandException {
        super(keyword, params);

        switch (keyword) {
        case "list":
            this.voidType = VoidType.LIST;
            break;
        case "bye":
            this.voidType = VoidType.BYE;
            break;
        case "forcequit":
            this.voidType = VoidType.FORCEQUIT;
            break;
        default:
            throw new IllegalCommandException("Invalid keyword for VoidCommand");
        }

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!this.params.get(0).isEmpty()) {
            throw new IllegalCommandException("Not sure what you're trying to say?");
        }
        switch (voidType) {
        case LIST:
            String[] taskStringList = tasks.enumerate();
            int listSize = taskStringList.length;
            for (int i = 0; i < listSize; i++) {
                ui.display(i + 1 + ":" + taskStringList[i]);
            }
            break;
        case BYE:
            try {
                storage.refreshStorage(tasks);
                ui.display("Tasks saved successfully! Goodbye!");
                System.exit(0);
            } catch (IOException e) {
                ui.errorDisplay(e);
                e.printStackTrace();
                ui.display("Tasks were unable to be saved, if you still wish to quit without"
                        + "saving, try using \"forcequit\" ");
            }
            break;
        case FORCEQUIT:
            try {
                storage.refreshStorage(tasks);
                ui.display("Tasks saved successfully! Exiting now!");
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
                ui.errorDisplay(e);
                ui.display("Tasks not saved, exiting now!");
                System.exit(0);
            }
            break;
        }
    }
}
