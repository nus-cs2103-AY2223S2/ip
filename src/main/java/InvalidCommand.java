public class InvalidCommand extends Command {


    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
//        throw new DukeException("Invalid command, please try again");
        System.out.println("Invalid command, please try again!");
    }

    public boolean isExit() {
        return false;
    }
}
