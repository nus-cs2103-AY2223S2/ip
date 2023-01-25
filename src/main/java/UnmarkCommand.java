public class UnmarkCommand extends Command {
    private int listNum;

    public UnmarkCommand(String input) {
        int tasknumbermark = Integer.valueOf(input) - 1;
        this.listNum = tasknumbermark;
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        tasks.unmarkTask(listNum);
        return true;
    }
}