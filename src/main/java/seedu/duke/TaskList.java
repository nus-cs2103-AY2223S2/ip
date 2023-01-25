/**
 * Project name: Duke
 * @author Tan Jun Da
 * Student Number: A0234893U
 */

package seedu.duke;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a taskList in an ArrayList format.
 * A <code>TaskList</code> object corresponds to
 * an ArrayList.
 */
public class TaskList {

    List<Task> tasksList;
    int counter;

    /**
     * Constructor for the TaskList class.
     *
     * @param tasksList The List of tasks.
     */
    public TaskList(List<Task> tasksList) {
        this.tasksList = tasksList;
        this.counter = tasksList.size();
    }

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        this.tasksList = new ArrayList<>();
        this.counter = 0;
    }

    /**
     * Returns a TaskList after marking a specified task as done.
     *
     * @param userParse The input from the user.
     * @return The TaskList with the specified marked task.
     */
    public TaskList mark(Parser userParse) {
        tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).mark();
        return this;
    }

    /**
     * Returns a TaskList after marking a specified task as not done.
     *
     * @param userParse The input from the user.
     * @return The TaskList with the specified unmarked task.
     */
    public TaskList unmark(Parser userParse) {
        tasksList.get(Integer.parseInt(userParse.inputArr[1]) - 1).unmark();
        return this;
    }

    /**
     * Add a Todo task to the TaskList.
     *
     * @param description The description of the Todo task.
     * @param userParse The input from the user.
     * @throws DukeException If there is an empty description.
     */
    public void addTodo(String description, Parser userParse) throws DukeException{
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
            counter++;
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
            counter++;
        } catch(DukeException e) {
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
            counter++;
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Returns the Task that has been deleted from the TaskList.
     *
     * @param userParse The input from the user.
     * @return The Task deleted.
     */
    public Task delete(Parser userParse) {
        String[] inputArr = userParse.inputArr;
        Task deleted = tasksList.remove(Integer.parseInt(inputArr[1]) - 1);
        counter--;
        return deleted;
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
                tempFind.counter++;
            }
        }
        return tempFind;
    }
}
