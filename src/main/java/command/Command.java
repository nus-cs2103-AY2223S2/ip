public class Command {
    protected final String command;
    public Command() {
        this("");
    }
    public Command(String command) {
        this.command = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // do the commands purpose
        // ui.print() the correct output
        //
    }
    // -1 is the standard if the command does not have min length
//    public static String correctFormat() {
//        return "this is the correct format of a command";
//    }
    public boolean isExit() {
        return false;
    }
}
