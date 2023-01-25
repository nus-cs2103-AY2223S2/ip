public class DeleteCommand extends Command {
    private int listNum;

    public DeleteCommand(String input) {
        int tasknumbermark = Integer.valueOf(input) - 1;
        this.listNum = tasknumbermark;
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        tasks.deleteTask(listNum);
        return true;
    }
}