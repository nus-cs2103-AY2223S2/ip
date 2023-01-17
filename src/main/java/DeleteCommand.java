public class DeleteCommand implements Command{
    private int inputIndex;
    public DeleteCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }
    public void execute(TaskList list) {
        list.delete(this.inputIndex);
    }

    public boolean isExit() {
        return false;
    }
}
