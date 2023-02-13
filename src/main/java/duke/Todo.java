package duke;

import java.util.ArrayList;

import util.*;

/**
 * Class Todo extends the abstract class Task and represents a to-do task.
 *
 * @author @tricixg
 * @version 1.0
 */
public class Todo extends Task {

    static String divider = "    ═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══";

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description a string description of the task.
     */
    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    /**
     * Creates a new Todo task with the given description and completion status.
     *
     * @param isDone  a boolean indicating whether the task is completed or not.
     * @param description a string description of the task.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
        this.taskType = "T";
    }
    /**
     * Returns a string representation of the task
     * in the format "[T][status icon] task description".
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Adds a new Todo task to the list of tasks.
     *
     * @param array a list of tasks.
     * @param splitInput an array of strings containing the user input.
     */
    public static void createTodoTask(ArrayList<Task> array, String[] splitInput) {
        if (splitInput.length == 1 || splitInput[1].equals("")) {
            try {
                throw new DukeException("todo");
            } catch (Exception e) {
                System.out.println(divider);
                System.out.println(e.toString());
                System.out.println(divider);
            }
        } else {
            for (int j=2; j< splitInput.length; j++) {
                splitInput[1] = splitInput[1] + " " + splitInput[j];
            }
            String desc = splitInput[1];
            Todo t = new Todo(desc);
            array.add(t);

            Ui.addTask(array, t);
        }
    }

    /**
     * Returns a string representation of the task
     * in the format "T | completion status | task description".
     *
     * @return a string representation of the task in the save format.
     */
    @Override
    public String saveFormat() {
        String divider = " | ";
        int marked = this.getIsDone() ? 1 : 0;
        return "T" + divider + marked + divider + description;
    }
}
