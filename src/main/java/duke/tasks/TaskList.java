package duke.tasks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import duke.Ui;
import duke.exception.DukeInvalidArgumentsException;
import duke.exception.DukeMissingArgumentException;
import duke.exception.DukeTaskArgumentException;



/**
 * Creates a list of tasks to be stored in the Duke program.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Ui ui = new Ui();

    /**
     * Creates a new TaskList with an empty list of tasks.
     * or loaded list of tasks from the hard disk.
     *
     * @param loadList Contains the loaded list of tasks from the hard disk
     */
    public TaskList(ArrayList<Task> loadList) {
        this.tasks.addAll(loadList);
    }

    /**
     * Returns the length of the TaskList.
     *
     * @return length of list
     */
    public int getListLength() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param taskIndices The index of the task
     */
    public void deleteTask(String[] taskIndices) {
        Arrays.sort(taskIndices, Collections.reverseOrder());
        for (int i = 0; i < taskIndices.length; i++) {
            int index = Integer.parseInt(taskIndices[i].trim());
            this.tasks.remove(index - 1);
        }
    }

    /**
     * Returns a task at a given index.
     * @param taskIndex
     * @return task object
     */
    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex - 1);
    }

    /**
     * Checks if the TaskList is empty
     *
     * @return the boolean representing whether the list is empty
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Marks the task as complete
     *
     * @param userInput
     * @param tasks
     * @return task string
     * @throws DukeInvalidArgumentsException
     * @throws DukeMissingArgumentException
     * @throws DukeTaskArgumentException
     */
    public String markComplete(String[] userInput, TaskList tasks) throws DukeInvalidArgumentsException,
            DukeMissingArgumentException, DukeTaskArgumentException {
        try {
            String indexString = userInput[1];
            String[] listIndices = indexString.split(",");
            ArrayList<Task> taskList = new ArrayList<>();
            for (int i = 0; i < listIndices.length; i++) {
                int taskIndex = Integer.parseInt(listIndices[i].trim());
                taskList.add(tasks.getTask(taskIndex));
                if (taskIndex > tasks.getListLength()) {
                    throw new DukeTaskArgumentException();
                }
                if (tasks.getTask(taskIndex).getStatus()) {
                    throw new DukeTaskArgumentException();
                }

                Task taskToBeMarked = tasks.getTask(taskIndex);
                taskToBeMarked.changeStatus();
            }

            return this.ui.markTaskDisplay(taskList);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        } catch (IndexOutOfBoundsException e) {
            String task = "mark";
            throw new DukeMissingArgumentException(task);
        }
    }

    /**
     * Marks the tasks as incomplete
     *
     * @param userInput
     * @param tasks
     * @return task string
     * @throws DukeMissingArgumentException
     * @throws DukeInvalidArgumentsException
     * @throws DukeTaskArgumentException
     */
    public String markIncomplete(String[] userInput, TaskList tasks) throws DukeMissingArgumentException,
            DukeInvalidArgumentsException, DukeTaskArgumentException {
        try {
            String indexString = userInput[1];
            String[] listIndices = indexString.split(",");
            ArrayList<Task> taskList = new ArrayList<>();
            for (int i = 0; i < listIndices.length; i++) {
                int taskIndex = Integer.parseInt(listIndices[i].trim());
                taskList.add(tasks.getTask(taskIndex));
                if (taskIndex > tasks.getListLength()) {
                    throw new DukeTaskArgumentException();
                }
                if (tasks.getTask(taskIndex).getStatus() == false) {
                    throw new DukeTaskArgumentException();
                }
                Task taskToBeUnmarked = tasks.getTask(taskIndex);
                taskToBeUnmarked.changeStatus();
            }
            return this.ui.unmarkTaskDisplay(taskList);
        } catch (IndexOutOfBoundsException e) {
            String task = "unmark";
            throw new DukeMissingArgumentException(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
    }

    /**
     * Filter the task list based on a word
     *
     * @param keyword
     * @return the filtered list
     */
    public ArrayList<Task> getFilteredTasks(String keyword) {
        return this.tasks.stream().filter(x -> x.toString()
                .contains(keyword)).collect(Collectors.toCollection(ArrayList::new));
    }
}
