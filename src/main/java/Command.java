public class Command {
    protected String commandName;
    public Command(String command) {
        this.commandName = command;
    }
    public void execute(TaskList tasks, Ui ui) {
        if (this.commandName.equals("list")) {
            ui.showList(tasks);
        }
    }

    public boolean isExit(Ui ui) {
        if (this.commandName.equals("bye")) {
            ui.showExit();
            return true;
        }
        return false;
    }
}
