package duke;

import java.util.ArrayList;

import duke.exceptions.EmptyDescriptionException;
import duke.tasks.Task;

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

    public Task deleteTask(int taskNumber) throws EmptyDescriptionException {
        Task currentTask = this.get(taskNumber - 1);
        this.remove(taskNumber - 1);
        return currentTask;
    }
}
