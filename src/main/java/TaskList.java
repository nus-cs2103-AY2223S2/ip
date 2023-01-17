import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public String getStatus() {
        switch (this.size()) {
            case 0:
                return "Now you have no tasks in the list.";
            case 1:
                return "Now you have 1 task in the list.";
            default:
                return String.format("Now you have %d task in the list.", this.size());
        }
    }

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
