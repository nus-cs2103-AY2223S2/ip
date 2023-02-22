package commands;

import java.io.IOException;
import java.util.ArrayList;

import dukeexceptions.DukeException;
import dukeexceptions.EmptyTaskListException;
import dukeexceptions.IllegalCommandException;
import dukeexceptions.IllegalInputException;
import elems.Storage;
import elems.TaskList;
import elems.Ui;


/**
 * Represents a <code>Command</code> that does not influence the state of the <code>TaskList</code>
 * @author clydelhui
 */
public class VoidCommand extends Command {
    enum VoidType {
        BYE,
        FIND,
        FORCEQUIT,
        LIST
    }

    private final VoidType voidType;

    /**
     * Constructor to produce a new <code>VoidCommand</code>
     * @param keyword The keyword for the given <code>VoidCommand</code>
     * @param params The parameters associated with the given <code>VoidCommand</code>
     * @throws IllegalCommandException When the given keyword is not valid
     */
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

    /**
     * {@inheritDoc}
     * @param tasks The <code>TaskList</code> to be acted on by the <code>Command</code>
     * @param ui The <code>Ui</code> to be acted on by the <code>Command</code>
     * @param storage The <code>Storage</code> to be acted on by the <code>Command</code>
     * @throws DukeException when unable to execute Command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (voidType) {
        case LIST:
            if (tasks.getSize() == 0) {
                throw new EmptyTaskListException("Trying to list an empty task list");
            }
            String[] taskStringList = tasks.enumerate();
            int listSize = taskStringList.length;
            StringBuilder listOutput = new StringBuilder();
            for (int i = 0; i < listSize; i++) {
                listOutput.append(i + 1).append(":").append(taskStringList[i]).append("\n");
            }
            ui.dukeDisplay(listOutput.toString());
            break;
        case FIND:
            if (this.params.size() > 2) {
                throw new IllegalInputException("Only 1 word can be searched at a time");
            }
            if (tasks.getSize() == 0) {
                throw new EmptyTaskListException("Trying to find something in an empty list");
            }
            String[] foundTasks = tasks.searchTaskDescription(this.params.get(0));
            if (foundTasks.length == 0) {
                ui.dukeDisplay("Sorry, no tasks match the given search term :(");
            } else {
                StringBuilder findOutput = new StringBuilder("I have found the following tasks!\n");
                for (int i = 0; i < foundTasks.length; i++) {
                    findOutput.append(i + 1).append(":").append(foundTasks[i]).append("\n");
                }
                ui.dukeDisplay(findOutput.toString());
            }
            break;
        case BYE:
            try {
                storage.refreshStorage(tasks);
                System.exit(0);
            } catch (IOException e) {
                ui.errorDisplay(e);
                e.printStackTrace();
                ui.dukeDisplay("Tasks were unable to be saved, if you still wish to quit without"
                        + "saving, try using \"forcequit\" ");
            }
            break;
        case FORCEQUIT:
            try {
                storage.refreshStorage(tasks);
                System.exit(0);
            } catch (IOException e) {
                ui.errorDisplay(e);
                e.printStackTrace();
                System.exit(0);
            }
            break;
        default:
            throw new IllegalCommandException("No defined action to execute");
        }
    }
}
