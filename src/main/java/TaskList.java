import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void handleListCommand() {
        if (this.tasks.size() == 0) {
            System.out.println("The list is empty.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task task = this.tasks.get(i);
            String indexString = Integer.toString(i + 1);
            System.out.println(indexString + ". " + task.toString());
        }
    }

    public void handleMarkUnmarkCommand(String userInput) {
        String[] strings = userInput.split(" ");
        boolean isMark = strings[0].equals("mark");

        if (strings.length != 2) {
            if (isMark) {
                System.out.println("Usage: mark <task no.>");
            } else {
                System.out.println("Usage: unmark <task no.>");
            }
            return;
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(strings[1]);
        } catch (NumberFormatException e) {
            if (isMark) {
                System.out.println("Usage: mark <task no.>");
            } else {
                System.out.println("Usage: unmark <task no.>");
            }
            return;
        }

        if (taskNumber < 1 || taskNumber > this.tasks.size()) {
            if (isMark) {
                System.out.println("Usage: mark <task no.>");
            } else {
                System.out.println("Usage: unmark <task no.>");
            }
            return;
        }

        // need to convert back to 0-indexed
        Task task = tasks.get(taskNumber - 1);

        if (isMark) {
            task.markDone();
        } else {
            task.unmarkDone();
        }
    }

    public void handleByeCommand() {
        System.out.println("Exiting...");
    }

    public void createNewTask(String newTaskName) {
        Task newTask = new Task(newTaskName);
        this.tasks.add(newTask);
        System.out.println("Added: " + newTaskName);
    }

}
