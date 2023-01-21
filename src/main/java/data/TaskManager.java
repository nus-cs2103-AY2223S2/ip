package data;
import errors.DukeInvalidCommandException;
import formatters.Response;
import task.Task;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> taskList;

    public TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Prints out the users tasks
     *
     * Calls the toString() method of each task type and formats the lists of tasks
     * by printing to the console line by line
     *
     * @param showMessage description of the parameter
     */

    public String displayTasks(boolean showMessage) {
        StringBuilder response = new StringBuilder();

        if (this.taskList.size() == 0) {
            return Response.NO_TASK.toString();
        }

        if (showMessage) {
            response.append("Heww are your tasks UwU!\n");
        }

        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String lineItem = (i + 1) + ". " + task.toString() + "\n";
            response.append(lineItem);
        }
        return response.toString();
    }

    public void markTaskComplete(int index) throws DukeInvalidCommandException {
        Task taskToMark;
        try {
            taskToMark = this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.TASK_NOT_FOUND.toString());
        }
        taskToMark.changeStatus(true);
    }

    public void markTaskIncomplete(int index) throws DukeInvalidCommandException {
        Task taskToMark;
        try {
            taskToMark = this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.TASK_NOT_FOUND.toString());
        }
        taskToMark.changeStatus(false);
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    public void deleteTask(int index) throws DukeInvalidCommandException {
        try {
            this.taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.TASK_NOT_FOUND.toString());
        }
    }













}
