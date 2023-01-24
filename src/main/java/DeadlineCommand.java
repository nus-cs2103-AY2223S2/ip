public class DeadlineCommand extends Command{
    public DeadlineCommand(String input) {
        super(input);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        if (input.length() == 0) {
            throw new DukeException(
                    "You cant be doing nothing!! Please try again!");
        }

        // String desc = input.substring(9);
        String[] descSplit = input.split("/");
        Deadline taskDeadline = new Deadline(
                descSplit[0].substring(0,descSplit[0].length()-1),
                descSplit[1].substring(3));

        taskList.addTask(taskDeadline);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskAdded(taskDeadline);
        ui.showNumTasks(taskList);
    }
}
