package rick.command;

import java.util.List;

import rick.TaskList;
import rick.Ui;


/**
 * A Command that executes MassOps
 *
 * @author SeeuSim
 *         AY22/23-S2 CS2103T
 */
public class MultiManipulateCommand extends Command {
    private final List<Command> executables;

    /**
     * Instantiate an instance of this command and populate it.
     *
     * @param commands The commands to execute.
     */
    public MultiManipulateCommand(List<Command> commands) {
        this.executables = commands;
    }

    /**
     * Executes all the commands in this list.
     *
     * @param ts The TaskList instance.
     * @param ui The UI output.
     * @return The UI to output from executing the command.
     */
    @Override
    public String execute(TaskList ts, Ui ui) {
        StringBuilder out = new StringBuilder("$$MULTI$$\n");
        for (Command c: this.executables) {
            out.append(c.execute(ts, ui) + "/$$SEP/$$");
        }
        return out.toString();
    }
}
