public class AddTaskCommand extends Command {
    private String response;
    public AddTaskCommand(String response) {
        super();
        this.response = response;
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = TaskInfoParser.parse(this.response);
            taskList.addTask(task);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
