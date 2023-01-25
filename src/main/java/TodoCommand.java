public class TodoCommand extends Command {
    private String content;

    public TodoCommand(String content) {
        this.content = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        content = content.trim();
        tasks.add(content, true);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
