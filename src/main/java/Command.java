abstract public class Command {
    String[] inputArr;
    public Command(String[] inputArr) {
        this.inputArr = inputArr;
    }
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    abstract public boolean isExit();
}
