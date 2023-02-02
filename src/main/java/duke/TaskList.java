package duke;

import java.util.ArrayList;
public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public void addTask(Task newTask, boolean onLoadIn) {
        this.add(newTask);
        if (!onLoadIn) {
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + this.toString());
            System.out.println(String.format("Now you have %d tasks in the list.", this.size()));
        }
    }

    public void deleteTask(int taskNumber) throws EmptyDescriptionException {
        Task currentTask = this.get(taskNumber - 1);
        this.remove(taskNumber - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + currentTask.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", this.size()));
    }
}
