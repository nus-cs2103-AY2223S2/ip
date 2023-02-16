package commands;

import dukeexceptions.DukeException;
import dukeexceptions.IllegalCommandException;
import dukeexceptions.IllegalInputException;
import elems.Storage;
import elems.TaskList;
import elems.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class VoidCommand extends Command{
    enum VoidType {
        BYE,
        FIND,
        FORCEQUIT,
        LIST
    }

    private VoidType voidType;

    public VoidCommand(String keyword, ArrayList<String> params) throws IllegalCommandException {
        super(keyword, params);

        switch (keyword) {
        case "list":
            this.voidType = VoidType.LIST;
            break;
        case "find":
            this.voidType = VoidType.FIND;
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
        switch (voidType) {
        case LIST:
            String[] taskStringList = tasks.enumerate();
            int listSize = taskStringList.length;
            for (int i = 0; i < listSize; i++) {
                ui.display(i + 1 + ":" + taskStringList[i]);
            }
            break;
        case FIND:
            if (this.params.size() > 2) {
                throw new IllegalInputException("Only 1 word can be searched at a time");
            }
            String[] foundTasks = tasks.searchTaskDescription(this.params.get(0));
            ui.display("I have found the following tasks!");
            for (int i = 0; i < foundTasks.length; i++) {
                String taskString = foundTasks[i];
                ui.display(i + 1 + ":" + taskString);
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
                ui.display("Tasks were unable to be saved, if you still wish to quit without" +
                        "saving, try using \"forcequit\" ");
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
