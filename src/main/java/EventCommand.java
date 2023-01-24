public class EventCommand extends Command{
    public EventCommand(String input) {
        super(input);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        if (input.length() == 0) {
            throw new DukeException(
                    "You can't be doing nothing!! Please try again!");
        }

        String[] descSplit = input.split("/");
        String description = descSplit[0].substring(
                0,descSplit[0].length()-1);
        String start = descSplit[1].substring(
                5,descSplit[1].length()-1);
        String end = descSplit[2].substring(3);
        Event taskEvent = new Event(description, start, end);

        taskList.addTask(taskEvent);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskAdded(taskEvent);
        ui.showNumTasks(taskList);
    }
}
