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
            outputs[i] = this.tasks.get(i).toString(i+1);
        }
        return outputs;
    }

    public String[] add(String[] argument) {
        Task newTask = new Task(argument[0]);
        this.tasks.add(newTask);
        return new String[]{"added: " + newTask};
    }

    public String[] mark(String[] argument) {
        try{
            String[] inds = argument[0].split("\\s");
            if (inds.length == 1) {
                int ind = Integer.parseInt(inds[0]) - 1;
                Task task = this.tasks.get(ind);
                return new String[]{(task.mark() ? "marked: " : "unmarked: ") + task};
            }
            String[] outputs = new String[inds.length + 1];
            outputs[0] = "marked:";
            for (int i = 0; i < inds.length; i++) {
                int ind = Integer.parseInt(inds[i]) - 1;
                Task task = this.tasks.get(ind);
                task.mark();
                outputs[i+1] = "\t" + task.toString(i+1);
            }
            return outputs;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid index.");
        }
    }
}
