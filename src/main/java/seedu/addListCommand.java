package seedu;

public class addListCommand extends Command {
    public addListCommand() {
    }

    public void assign(TaskList taskList, UI ui) {
        super.assign(taskList, ui);
    }

    @Override
    public void execute() throws JamesException {
        ui.printTaskList(taskList);
    }

}
