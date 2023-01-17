public class UnMark implements Command{
    private int indexToMark;
    public UnMark(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    public void execute(TaskList list) {
        list.unmark(indexToMark);
    }

    public boolean isExit() {
        return false;
    }
}
