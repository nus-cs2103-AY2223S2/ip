import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("Added task: " + task);
        System.out.println("You now have " + this.size() + " task(s) in your list.");
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void delete(int index) {
        Task removed = this.taskList.remove(index - 1);
        System.out.println("Removed task: " + removed);
        System.out.println("You now have " + this.size() + " task(s) in your list.");
    }

    public void markDone(int index) {
        Task task = this.taskList.get(index - 1);
        task.markAsDone();
        System.out.println("Good job! I have marked this task as done! \n" + "\t" + task);
    }

    public void markUndone(int index) {
        Task task = this.taskList.get(index - 1);
        task.markAsUndone();
        System.out.println("Oof! I have marked this task as undone for you! \n" + task);
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("List of Tasks: \n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1));
            result.append(". ");
            result.append(taskList.get(i).toString());
            result.append("\n");
        }
        return result.toString();
    }
}
