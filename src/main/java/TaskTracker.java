import java.util.ArrayList;

public class TaskTracker {
    protected ArrayList<Task> taskList = new ArrayList<>();

    public TaskTracker() {}

    // Add new taskList to the list
    // Outputs a String with the details of the task and the number of taskList in the list
    public void addTask(Task task) {
        taskList.add(task);
        System.out.println(String.format("     Got it. I've added this task:\n" +
                "       %s\n" + this.numTasks(), task));
    }

    public void listTasks() {
        int counter = 1;
        for (Task t : taskList) {
            System.out.println(counter + ". " + t.toString());
            counter++;
        }
    }

    public void manageTask(String command) throws DukeInputError{
        String[] input = command.split(" ");
        if ((input.length != 2)) {
            throw new DukeInputError(input[0]);
        }
        int taskNumber = Integer.parseInt(input[1]) - 1;
        if (taskNumber >= taskList.size() || (taskNumber < 0)) { //negative and out of range is invalid
            throw new DukeInputError("bounds");
        }
        if (input[0].equals("delete")) {
            Task task = taskList.remove(taskNumber);
            System.out.println(String.format("     Noted. I've removed this task:\n       %s\n%s",
                    task, numTasks()));
        } else {
            boolean completion = input[0].equals("mark");
            Task task = taskList.get(taskNumber);
            task.setCompletion(completion);
        }
    }

    public String numTasks() {
        return String.format("     Now you have %d taskList in the list", taskList.size());
    }
}
