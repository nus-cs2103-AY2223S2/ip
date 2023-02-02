package duke;

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

    public void instruct() {
        System.out.println("There are various commands you can instruct me to do.");
        System.out.println("A. If you want to see your list of tasks, type 'list'");
        System.out.println("B. If you want to add a task, there are three possible commands:");
        System.out.println("\t1. Type 'todo (name of task)' to add a todo task.");
        System.out.print("\t2. Type 'deadline (name of task) /by (date in YYYY-MM-DD)");
        System.out.println(" to add a task that is to be done by a certain date.");
        System.out.print("\t3. Type 'event (name of task) /from (date and time) /to ");
        System.out.println("(date and time) to add in a time-limited event.");
        System.out.println("C. To delete a task, type 'delete X' where X is the task number");
        System.out.println("D. To mark a task as completed, type 'mark X'");
        System.out.println("E. To save and exit, type 'bye'");
    }

    /**
     * Prints the goodbye message upon termination of the chatbot.
     */
    public void terminate() {
        System.out.println("Bye. Hope to see you again!");
    }
}
