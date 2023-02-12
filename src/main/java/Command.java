abstract public class Command {
    private final String inputArr;
    public Command(String inputArr) {
        this.inputArr = inputArr;
    }

    public String getInputArr() {
        return inputArr;
    }
    public abstract void process(TaskList taskList, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}