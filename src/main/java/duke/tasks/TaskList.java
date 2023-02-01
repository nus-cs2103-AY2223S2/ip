package duke.tasks;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> getDeadlineBefore(LocalDate by) throws DukeException {
        ArrayList<Task> deadlines = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isDeadline() && tasks.get(i).getDeadline().isBefore(by)) {
                deadlines.add(tasks.get(i));
            }
        }
        return deadlines;
    }

    public String printDescription(int idx) {
        return tasks.get(idx).getDescription();
    }

    public void markTask(int idx) {
        tasks.get(idx).markTask();
    }

    public void unmarkTask(int idx) {
        tasks.get(idx).unmarkTask();
    }

    public String printTask(int idx) {
        return tasks.get(idx).toString();
    }

    public void deleteTask(int idx) {
        tasks.remove(idx);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
