public abstract class Command {

    public Command() {}

    public abstract void execute(Ui ui, TaskList taskList, Storage storage);

}
