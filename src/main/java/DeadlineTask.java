import java.util.ArrayList;
import java.util.Arrays;

public class DeadlineTask extends Task {
    protected String deadline;

    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
        this.taskType = "D";
    }

    public static void createDeadlineTask(String command, TaskTracker t) throws DukeInputError {
        ArrayList<String> input = new ArrayList(Arrays.asList(command.split(" ")));
        if (input.size() <= 1) throw new DukeInputError("deadline");
        int byIndex = input.indexOf("/by");
        String taskName = "";
        String deadline = "";
        for (int i = 1; i < input.size(); i++) {
            if (i < byIndex) {
                taskName += input.get(i);
                if (i < byIndex - 1) {
                    taskName += " ";
                }
            } else if (i > byIndex) {
                deadline += input.get(i);
                if (i < input.size() - 1) {
                    deadline += " ";
                }
            }
        }
        t.addTask(new DeadlineTask(taskName, deadline));
    }

    @Override
    public String toString() {
        return String.format("  %s%s %s (by: %s)", displayType(), displayMark(), this.taskName, this.deadline);
    }
}
