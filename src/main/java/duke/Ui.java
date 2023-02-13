package duke;

import javafx.scene.control.Label;

public class Ui {

    /**
     * Creates a Label with the introductory message.
     *
     * @return the Label with the introductory message.
     */
    public Label introduce() {
        String introString = "Hello! I'm Pearl, your friendly chatbot.\n";
        introString += instruct();
        return new Label(introString);
    }

    /**
     * Provides instructions on what this chatbot can do.
     *
     * @return a Label with the instructions.
     */
    public String instruct() {
        String instructString = "There are various commands you can instruct me to do.\n";
        instructString += ("A. If you want to see your list of tasks, type 'list'\n");
        instructString += ("B. If you want to add a task, there are three possible commands:\n");
        instructString += ("\t1. Type 'todo (name of task)' to add a todo task.\n");
        instructString += ("\t2. Type 'deadline (name of task) /by (date in YYYY-MM-DD)\n");
        instructString += (" to add a task that is to be done by a certain date.\n");
        instructString += ("\t3. Type 'event (name of task) /from (date and time) /to ");
        instructString += ("(date and time) to add in a time-limited event.\n");
        instructString += ("C. For the following, let X be the task number.\n");
        instructString += ("    To increase priority of a task, type 'increase X'\n");
        instructString += ("    To decrease priority of a task, type 'decrease X'\n");
        instructString += ("    To delete a task, type 'delete X'\n");
        instructString += ("    To mark a task as completed, type 'mark X'\n");
        instructString += ("    To unmark a task, type 'unmark X'\n");
        instructString += ("D. To sort the tasks according to priority, type 'sort'\n");
        instructString += ("E. To find tasks with a certain keyword, type 'find Y' ");
        instructString += ("where Y is the keyword\n");
        instructString += ("F. To show these instructions again, type 'help'\n");
        instructString += ("G. To save and exit, type 'bye'\n");
        return instructString;
    }

    /**
     * Prints the goodbye message upon termination of the chatbot.
     *
     * @return a Label with the goodbye message.
     */
    public String terminate() {
        return("Bye. Hope to see you again!\n");
    }
}
