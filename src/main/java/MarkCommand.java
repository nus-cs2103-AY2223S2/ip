public class MarkCommand implements Command{
    private int indexToMark;
    public MarkCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    public void execute(TaskList list) {
        list.mark(indexToMark);
    }

    public boolean isExit() {
        return false;
    }
}
