public abstract class Command {
    public abstract boolean execute(TaskList tasks, Ui ui, StorageList storage);

    public boolean isExit(){
        return false;
    }
}