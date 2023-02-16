package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class HelpCommand extends Command {

    /**
     * executes the purpose of the HelpCommand
     * @param taskList can be ignored but is required due to the abstract class
     * @param storage can be ignored but is required due to the abstract class
     * @param ui used to print out the purpose of all the command
     * @throws FileNotFoundException
     */
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) throws FileNotFoundException {
        int dummyIndex = 1;

        ArrayList<Command> commands = new ArrayList<>();

        commands.add(new AddCommand(new Task("dummy", "T")));
        commands.add(new DeleteCommand(dummyIndex));
        commands.add(new ListCommand());
        commands.add(new AddNoteCommand(dummyIndex, ""));
        commands.add(new DeleteNoteCommand(dummyIndex));
        commands.add(new ListNoteCommand());;
        commands.add(new MarkCommand(dummyIndex));
        commands.add(new UnmarkCommand(dummyIndex));

        String output = commands.get(0).toString();

        for (int i = 1; i < 8; i++) {
            output = output + "\n" + commands.get(i).toString() + "\n";
        }

        ui.printText(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
