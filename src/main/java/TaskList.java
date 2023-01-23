import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int taskIndex = 1;
        for (Task task: tasks) {
            System.out.println(taskIndex + ". " + task);
            taskIndex++;
        }
    }

    public int getTasksNum() {
        return tasks.size();
    }

    //Returns string to be printed when new task is added
    private String numTasksString(int numTasks) {
        return "Now you have " + numTasks + " tasks in the list";
    }

    //Returns string containing number of tasks added
    private static String taskAdded(Task task) {
        return "Got it. I've added this task:\n " + task + "\n";
    }

    public void deleteTask(int taskNum) {
        Task currentTask = tasks.get(taskNum);
        tasks.remove(currentTask);
        System.out.println("Noted. I've removed this task:\n " + currentTask + "\n"
                + numTasksString(tasks.size()));
    }

    public void setTaskAsDone(int taskNum) throws TaskDoneException {
        Task currentTask = tasks.get(taskNum);
        currentTask.setDone();
        System.out.println("Nice! Congrats for completing this task:\n " + currentTask);
    }

    public void setTaskAsUndone(int taskNum) throws TaskUndoneException {
        Task currentTask = tasks.get(taskNum);
        currentTask.setUndone();
        System.out.println("OK, I've marked this task as not done yet:\n " + currentTask);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println(taskAdded(task) + numTasksString(tasks.size()));
    }

    public void listTasksOnDate(LocalDate date) {
        int taskIndex = 1;
        System.out.println("Here are the tasks in your list due on "
                + date.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy")) + ":");
        for (Task task : tasks) {
            if (task.isToday(date)) {
                System.out.println(taskIndex + ". " + task);
                taskIndex++;
            }
        }
    }

    public String saveTasksString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task: tasks) {
            stringBuilder.append(task.getFileWriteString() + "\n");
        }
        return stringBuilder.toString();
    }
}
