public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        ui.showLine();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return ExitCommand.COMMAND_WORD;
    }

}
