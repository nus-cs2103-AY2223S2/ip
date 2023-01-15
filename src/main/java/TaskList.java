import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> tasks;

    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    public void addTask(String taskName) {
        Task task = new Task(taskName);
        tasks.add(task);
        Response.printMessage("I have added " + taskName + " to my memory");
    }

    public void deleteTask(String task) {
        // no implementation yet
    }

    public void markDone(int number) {
        tasks.get(number - 1).mark();
        Response.printMessage("Understood, I have marked the task as done: \n"
                + "        " + tasks.get(number - 1).toString());
    }
    public void markUndone(int number) {
        tasks.get(number - 1).unmark();
        Response.printMessage("Understood, I have marked the task as undone: \n"
                + "        " + tasks.get(number - 1).toString());
    }

    public void getList() {
        System.out.println("        ________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("        "
                    + (i + 1)
                    + "."
                    + tasks.get(i).toString());
        }
        System.out.println("        ________________________________________________________");
    }
}
