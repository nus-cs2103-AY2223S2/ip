import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "";
        }
        
        int listIndex = 1;
        StringBuilder output = new StringBuilder();
        for (Task task : this) {
            output.append(listIndex + ". " + task.toString() + "\n");
            listIndex++;
        }
        // Removes trailing newline.
        output.setLength(output.length() - 1);
        return output.toString();
    }
}
