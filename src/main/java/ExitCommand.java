public class ExitCommand extends Command {
    public ExitCommand(String commandString) {
        super(Commands.EXIT, commandString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String exitMsg = "Thank you for coming!\n" + "Hope to see you again soon!\n" + "~~Bye";
        ui.showMsg(exitMsg);

        storage.updateData(tasks);
        ui.close();
    }
}
