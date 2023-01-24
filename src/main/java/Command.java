public abstract class Command {
    private String regex;

    Command (String regex) {
        this.regex = regex;
    }

    public abstract void execute(TaskList tl, Ui ui, Storage storage) throws DukeException;

    public String stringify() {
        return regex;
    }
}
