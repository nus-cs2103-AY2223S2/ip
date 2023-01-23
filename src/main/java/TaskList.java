import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public TaskList(BufferedReader br) throws CommandException, IOException, DescriptionException {
        super();
        String str;
        while ((str = br.readLine()) != null) {
            this.add(Task.strToTask(str));
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (Task task : this) {
            result += task + "\n";
        }
        return result;
    }

    public void addingTask(Task task) throws DescriptionException {
        if (task.isEmpty()) {
            throw new DescriptionException();
        } else {
            this.add(task);
        }
    }
}
