package duke;

import javafx.scene.control.Label;

import java.util.ArrayList;

public class Ui {

    /**
     * Prints the introduction message when the chatbot is first booted up.
     */
    public void introduce(StringBuilder allResponses) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        allResponses.append("Hello from\n" + logo);
        allResponses.append("Hello! I'm Duke, your friendly chatbot.\n");
        allResponses.append("What can I do for you?\n");
    }

    public Label introduce() {
        String introString = "Hello! I'm Pearl, your friendly chatbot.\n";
        introString += instruct();
        return new Label(introString);
    }

    public String instruct() {
        String instructString = "There are various commands you can instruct me to do.\n";
        instructString += ("A. If you want to see your list of tasks, type 'list'\n");
        instructString += ("B. If you want to add a task, there are three possible commands:\n");
        instructString += ("\t1. Type 'todo (name of task)' to add a todo task.\n");
        instructString += ("\t2. Type 'deadline (name of task) /by (date in YYYY-MM-DD)\n");
        instructString += (" to add a task that is to be done by a certain date.\n");
        instructString += ("\t3. Type 'event (name of task) /from (date and time) /to ");
        instructString += ("(date and time) to add in a time-limited event.\n");
        instructString += ("C. To delete a task, type 'delete X' where X is the task number\n");
        instructString += ("D. To mark a task as completed, type 'mark X'\n");
        instructString += ("E. To save and exit, type 'bye'\n");
        return instructString;
    }

    /**
     * Prints the goodbye message upon termination of the chatbot.
     */
    public String terminate() {
        return("Bye. Hope to see you again!\n");
    }
}
