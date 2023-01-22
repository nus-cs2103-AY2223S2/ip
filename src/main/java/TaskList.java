import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;
    private Ui ui;

    public TaskList(Ui ui) {
        this.list = new ArrayList<Task>();
        Task t = new ToDo("something");
        this.list.add(t);

        this.ui = ui;
    }

    public void markTaskAsDone(int index) {
        Task task = this.list.get(index);
        task.markAsDone();
        this.ui.addToMessage("Nice! I've marked this task as done:");
        this.ui.addToMessage(task.toString());
    }

    public void markTaskAsUndone(int index) {
        Task task = this.list.get(index);
        task.unmarkAsDone();
        this.ui.addToMessage("Ok, I've marked this task as not done yet:");
        this.ui.addToMessage(task.toString());
    }

    public void displayTasks() {
        if (list.size() == 0) {
            this.ui.addToMessage("You have no tasks! Try adding some.");
        } else {
            this.ui.addToMessage("Here are the tasks in your list:");
            for (int i = 0; i < this.list.size(); i++) {
                Task currentTask = this.list.get(i);
                String s = String.format(
                    "%d: %s",
                    i + 1,
                    currentTask.toString()
                );
                this.ui.addToMessage(s);
            }
        }
    }
}
