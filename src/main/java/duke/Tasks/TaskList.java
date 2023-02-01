package duke.Tasks;

import duke.Exceptions.CommandException;
import duke.Exceptions.DescriptionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public TaskList(BufferedReader br) throws CommandException, IOException {
        super();
        String str;
        while ((str = br.readLine()) != null) {
            this.add(Task.strToTask(str));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Task task : this) {
            result.append(task).append("\n");
        }
        return result.toString();
    }

    public String toFormattedString() {
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (Task task : this) {
            result.append(count++)
                    .append(". ")
                    .append(task)
                    .append("\n");
        }
        return result.toString();
    }

    public void addingTask(Task task) throws DescriptionException {
        if (task.isEmpty()) {
            throw new DescriptionException();
        } else {
            this.add(task);
        }
    }

    public Task markTask(int index) throws IndexOutOfBoundsException {
        Task task = this.get(index);
        task.markDone();
        return task;
    }
}
