public class UnMarkCommand implements Command{
    private int indexToMark;
    public UnMarkCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    public void execute(TaskList list) {
        list.unmark(indexToMark);
    }

    public boolean isExit() {
        return false;
    }
}