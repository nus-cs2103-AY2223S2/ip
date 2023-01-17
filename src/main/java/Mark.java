public class Mark implements Command{
    private int indexToMark;
    public Mark(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    public void execute(TaskList list) {
        list.mark(indexToMark);
    }

    public boolean isExit() {
        return false;
    }
}
