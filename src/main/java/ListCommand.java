public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.tasks.size() == 0) {
            ui.showMessage("You have no tasks due!");
        } else {
            ui.showMessage("Here are the tasks you have due!");
            for (Task cur : taskList.tasks) {
                if (cur instanceof Todo) {
                    ui.showMessage(taskList.tasks.indexOf(cur) + 1 + ". [" + cur.symbol + "] " + "[" + cur.getStatusIcon() + "] " + cur.description);
                } else {
                    System.out.println(taskList.tasks.indexOf(cur) + 1 + ". [" + cur.symbol + "] " + "[" + cur.getStatusIcon() + "] " + cur.description + " (" + cur.duedateString + ")");
                }
            }
        }
    }

    public boolean isExit() {
        return false;
    }
}
