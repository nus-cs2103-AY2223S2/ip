/**
 * Project name: Duke
 * @author Tan Jun Da A023489eU
 */

package seedu.duke;

import java.util.ArrayList;
import java.util.List;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

/**
 * Represents a taskList in an ArrayList format.
 * A <code>TaskList</code> object corresponds to
 * an ArrayList.
 */
public class TaskList {

    protected List<Task> tasksList;
    protected int tasksCounter;

    /**
     * Constructor for the TaskList class.
     *
     * @param tasksList The List of tasks.
     */
    public TaskList(List<Task> tasksList) {
        this.tasksList = tasksList;
        this.tasksCounter = tasksList.size();
    }

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        this.tasksList = new ArrayList<>();
        this.tasksCounter = 0;
    }

    /**
     * Constructor for the Copy of the TaskList.
     *
     * @param original The original TaskList object.
     */
    public TaskList(TaskList original) {
        this.tasksList = new ArrayList<>(original.tasksList);
        this.tasksCounter = original.tasksCounter;
    }

    /**
     * Returns a TaskList after marking a specified task as done.
     *
     * @param userParse The input from the user.
     * @return The TaskList with the specified marked task.
     * @throws DukeException if user index is more than the list or command index is not a number.
     */
    public TaskList mark(Parser userParse) throws DukeException {
        try {
            int userIndex = userParse.checkValidIndex(userParse.inputArr[1]) - 1;

            if (userIndex >= tasksCounter || userIndex < 0) {
                String message = "There is no such index.\n Please try again with the correct indexing.\n";
                throw new DukeException(message);
            } else {
                tasksList.get(userIndex).mark();
                return this;
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Returns a TaskList after marking a specified task as not done.
     *
     * @param userParse The input from the user.
     * @return The TaskList with the specified unmarked task.
     * @throws DukeException if user index is more than the list or command index is not a number.
     */
    public TaskList unmark(Parser userParse) throws DukeException {
        try {
            int userIndex = userParse.checkValidIndex(userParse.inputArr[1]) - 1;

            if (userIndex >= tasksCounter || userIndex < 0) {
                String message = "There is no such index.\n Please try again with the correct indexing.\n";
                throw new DukeException(message);
            } else {
                tasksList.get(userIndex).unmark();
                return this;
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Add a Todo task to the TaskList.
     *
     * @param description The description of the Todo task.
     * @param userParse The input from the user.
     * @throws DukeException If there is an empty description.
     */
    public void addTodo(String description, Parser userParse) throws DukeException {
        int inputArrLength = userParse.inputArr.length;
        String[] inputArr = userParse.inputArr;
        for (int i = 1; i < inputArrLength; i++) {
            description = description + inputArr[i];
            if (i != inputArrLength - 1) {
                description += " ";
            }
        }
        try {
            tasksList.add(new Todo(userParse.checkDescription(description, "todo")));
            tasksCounter++;
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Add a Deadline task to the TaskList.
     *
     * @param description The description of the Deadline task.
     * @param userParse The input from the user.
     * @throws DukeException If there is an empty description or time.
     */
    public void addDeadline(String description, Parser userParse) throws DukeException {
        String deadline = "";
        int inputArrLength = userParse.inputArr.length;
        String[] inputArr = userParse.inputArr;
        for (int i = 1; i < inputArrLength; i++) {
            if (inputArr[i].charAt(0) == '/') {
                i++;
                while (i < inputArrLength) {
                    deadline += inputArr[i];
                    if (i != inputArrLength - 1) {
                        deadline += " ";
                    }
                    i++;
                }
                break;
            }
            description = description + inputArr[i] + " ";
        }
        try {
            tasksList.add(new Deadline(userParse.checkDescription(description, "deadline"),
                    userParse.checkTime(deadline, "deadline", "by")));
            tasksCounter++;
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Add an Event task to the TaskList.
     *
     * @param description The description of the Event task.
     * @param userParse The input from the user.
     * @throws DukeException If there is an empty description or time.
     */
    public void addEvent(String description, Parser userParse) throws DukeException {
        String from = "";
        String to = "";
        int inputArrLength = userParse.inputArr.length;
        String[] inputArr = userParse.inputArr;
        for (int i = 1; i < inputArrLength; i++) {
            if (inputArr[i].charAt(0) == '/') {
                i++;
                while (i < inputArrLength) {
                    if (inputArr[i].charAt(0) == '/') {
                        i++;
                        while (i < inputArr.length) {
                            to += inputArr[i];
                            if (i != inputArr.length - 1) {
                                to += " ";
                            }
                            i++;
                        }
                        break;
                    }
                    from += inputArr[i] + " ";
                    i++;
                }
                break;
            }
            description = description + inputArr[i] + " ";
        }
        try {
            tasksList.add(new Event(userParse.checkDescription(description, "event"),
                    userParse.checkTime(from, "event", "from"),
                    userParse.checkTime(to, "event", "to")));
            tasksCounter++;
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Returns the Task that has been deleted from the TaskList.
     *
     * @param userParse The input from the user.
     * @return The Task deleted.
     * @throws DukeException if user index is more than the list or command index is not a number.
     */
    public Task delete(Parser userParse) throws DukeException {
        try {
            int userIndex = userParse.checkValidIndex(userParse.inputArr[1]) - 1;

            if (userIndex >= tasksCounter || userIndex < 0) {
                String message = "There is no such index.\n Please try again with the correct indexing.\n";
                throw new DukeException(message);
            } else {
                String[] inputArr = userParse.inputArr;
                Task deleted = tasksList.remove(Integer.parseInt(inputArr[1]) - 1);
                tasksCounter--;
                return deleted;
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Returns the TaskList for all possible match.
     *
     * @param userParse The input from the user.
     * @return The TaskList for all possible match.
     */
    public TaskList find(Parser userParse) {
        TaskList tempFind = new TaskList();
        String toMatch = userParse.findMatchDescription();
        for (Task curr : this.tasksList) {
            if (curr.getDescription().contains(toMatch)) {
                tempFind.tasksList.add(curr);
                tempFind.tasksCounter++;
            }
        }
        return tempFind;
    }

    /**
     * Tag a tasks in the list with the tag description.
     *
     * @param userParse The user input.
     * @return The tasks of tag items.
     * @throws DukeException if the command index is out of bounds or invalid number.
     */
    public TaskList tag(Parser userParse) throws DukeException {
        try {
            int userIndex = userParse.checkValidIndex(userParse.inputArr[1]) - 1;

            if (userIndex >= tasksCounter || userIndex < 0) {
                String message = "There is no such index.\n Please try again with the correct indexing.\n";
                throw new DukeException(message);
            } else {
                String tagMessage = "";
                for (int i = 2; i < userParse.inputArr.length; i++) {
                    tagMessage += userParse.inputArr[i] + " ";
                }
                tasksList.get(userIndex).addTag(userParse.checkDescription(tagMessage.trim()));
                return this;
            }
        } catch (DukeException e) {
            throw e;
        }
    }
}
