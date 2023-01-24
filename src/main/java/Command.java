public interface Command {
    String getCommandName();

    String getCommandRegexPattern();

    String getCommandPattern();

    void run(String[] args, Ui ui, TaskList taskList, Storage storage) throws DukeException;
}
