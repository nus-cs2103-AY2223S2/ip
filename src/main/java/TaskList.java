import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;
    private final Ui ui = new Ui();

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public TaskList() {
        this.listOfTasks = new ArrayList<>(100);
    }

    public void saveTask(String description) {
        Task task = new Todo(description);
        listOfTasks.add(task);
        ui.printSaveTask(task, listOfTasks);
    }

    public void saveTask(String description, LocalDateTime by) {
        Task task = new Deadline(description, by);
        listOfTasks.add(task);
        ui.printSaveTask(task, listOfTasks);
    }

    public void saveTask(String description, LocalDateTime from, LocalDateTime to) {
        Task task = new Event(description, from, to);
        listOfTasks.add(task);
        ui.printSaveTask(task, listOfTasks);
    }

    public void markTask(int index) throws DukeException {
        try {
            Task task = listOfTasks.get(index - 1);
            task.markAsDone();
            ui.printMarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    public void unmarkTask(int index) throws DukeException {
        try {
            Task task = listOfTasks.get(index - 1);
            task.markAsUndone();
            ui.printUnmarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    public void deleteTask(int index) throws DukeException {
        try {
            Task task = listOfTasks.get(index - 1);
            listOfTasks.remove(index - 1);
            ui.printDeleteTask(task, listOfTasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }
}
