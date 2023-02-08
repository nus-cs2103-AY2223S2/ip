package src.main.c4po;

public class HelpCommand extends Command {


    /**
     * Executes the command with actions specific to each extension of this
     * class Command
     *
     * @param tasks          are the list of tasks
     * @param ui             is the instance of UI
     * @param storage        the instance of Storage which holds and writes to the data file
     * @param isStringOutput
     * @throws BotException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, boolean isStringOutput) throws BotException {

        return Ui.showCommandList(true);
    }

    /**
     * Returns whether a command should cause bot to end interaction
     *
     * @return boolean to be used in the main loop
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
