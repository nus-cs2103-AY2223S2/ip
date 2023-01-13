import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ToDoList {
    private final List<ToDoTask> tasks;

    public ToDoList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task into the list.
     * @param task Task description.
     * @return Response line.
     */
    public String addTask(String task) {
        this.tasks.add(new ToDoTask(task));
        return String.format("added: %s", task);
    }

    /**
     * Marks a task as done or undone, based on the command.
     * @param command mark/unmark command.
     * @return List of response lines.
     */
    public List<String> setTaskDone(String command) {
        String[] parts = command.split(" ");
        int index;
        try {
            index = Integer.parseInt(parts[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return this.setTaskDone(-1, false);
        }

        if (parts[0].equalsIgnoreCase("mark")) {
            return this.setTaskDone(index, true);
        } else if (parts[0].equalsIgnoreCase("unmark")) {
            return this.setTaskDone(index, false);
        } else {
            return List.of("Sorry, I can't handle that right now.");
        }
    }

    /**
     * Marks a task as done or undone as given.
     * @param index 1-based index of the task.
     * @param isDone Whether the task is marked as done.
     * @return List of response lines.
     */
    public List<String> setTaskDone(int index, boolean isDone) {
        if (this.tasks.isEmpty()) {
            return List.of("There are no tasks to mark, please add a task first.");
        }
        if (index <= 0 || index > this.tasks.size()) {
            return List.of(
                    "Sorry, you didn't quite get the index right.",
                    String.format("The tasks in your list run from %d to %d.", 1, this.tasks.size())
            );
        }

        ToDoTask task = this.tasks.get(index - 1);
        task.setDone(isDone);
        return List.of(
                String.format("You have marked task %d as %s.", index, isDone ? "done" : "undone"),
                "\t" + task
        );
    }

    /**
     * Returns a list of response lines to display the list.
     * @return List of response lines.
     */
    public List<String> getTasksForPrint() {
        if (tasks.isEmpty()) {
            return List.of("No tasks, you're good for the day!");
        }

        List<String> res = new LinkedList<>();
        res.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            res.add(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        return res;
    }
}
