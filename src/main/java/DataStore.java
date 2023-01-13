import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private final List<Task> tasks;

    public DataStore(List<Task> tasks) {
        this.tasks = tasks;
    }

    public DataStore() {
        this(new ArrayList<>());
    }

    public String[] stringify() {
        if (this.tasks.size() == 0) return new String[]{"No tasks found."};
        String[] outputs = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            outputs[i] = (i + 1) + ". " + this.tasks.get(i).toString() + "\n";
        }
        return outputs;
    }

    public String[] add(String[] argument) {
        Task newTask = new Task(argument[0]);
        this.tasks.add(newTask);
        return new String[]{"added: " + newTask};
    }
}
