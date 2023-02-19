package commands;

import static commands.CommandType.BYE;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command is an abstract class that represents a command that Sirius can recognise and execute.
 */
public abstract class Command {
    private CommandType type;

    /**
     * Constructs a new Command with the specified CommandType.
     *
     * @param type the type of this Command
     */
    public Command(CommandType type) {
        this.type = type;
    }

    /**
     * Executes the operation that Sirius can perform based on the type of command
     *
     * @param tasks the existing TaskList that the command may need to update
     * @param ui the Ui object that helps display command execution results to the user
     * @param storage the Storage object that helps save tasks after the command has been executed
     *
     * @return The command execution result string.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if the command type is a bye command, false otherwise.
     *
     * @return check if the Command is a bye command
     */
    public boolean isBye() {
        return type.equals(BYE);
    }

    public CommandType getType() {
        return type;
    }
}
