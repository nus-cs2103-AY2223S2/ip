package Commands;

import Week2.src.main.TaskList;
import Week2.src.main.Duke;

import java.io.IOException;

/**
 * A subclass of Task
 * It is a default class of tasks that only contains content information.
 * A subclass that extends Task
 * It is a default Task class that only contains the task context information
 */
public class Todo extends Task {

    /**
     * Todo constructor
     * It only contains the content of the task that user has entered.
     * It only contains task context
     * @param content
     */
    public Todo(String content) {
        super(content);
    }

    /**
     * Add the user input todo task to the task list
     * and shows it to the user.
     * @param doit the current task to do
     * @param tasklist current task list
     * @return Output line to show to user
     * @throws IOException to write on
     */
    public static String execute(String doit, TaskList tasklist) throws IOException {
        Task current = new Todo(doit);
        tasklist.add(current);
        String str1 = "Got it. I've added this task:";
        String str2 = current.toString();
        String str3 = "Now you have " + tasklist.length() + " tasks in the list";
        Duke.writeOn(current);
        return str1 + "\n" + str2 + "\n" + str3;
    }

    /**
     * It overrides toString() method to change information to a string format.
     * @return String format of task data
     * It overrides toString() method to change the information to a string format.
     * @return A string format of the given task information
     */
    @Override
    public String toString() {
        return "[T][" + this.getDone() + "] " + this.content;
    }
}
