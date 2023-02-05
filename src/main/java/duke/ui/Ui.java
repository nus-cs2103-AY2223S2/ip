package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    /** Scanner for UI */
    private String output;

    /**
     * Constructs Ui class.
     */
    public Ui() {
        this.output = "";
    }

    /**
     * Store output.
     */
    public void storeOutput(String output){
        if (this.output.isEmpty()) {
            this.output += output;
        } else {
            this.output += "\n" + output;

        }
    }

    /**
     * Displays output.
     */
    public String displayOutput(){
        String temp = output;
        output = "";
        return temp;
    }

    /**
     * Displays BorzAI logo.
     */
    public void showStartUp() {
        String logo = "  /\\_/\\\n"
                + " /\u25DE   \u25DF\\\n"
                + "( \u25d5   \u25d5 )\n"
                + " \\     /\n"
                + "  \\   /\n"
                + "   \\ /\n"
                + "    \u25CF\n";
        System.out.println(logo + "BorzAI\n");
    }

    /**
     * Displays welcome message.
     */
    public void showWelcome() {
        storeOutput("When all I do is for you, Kermie \u2665\n\tWhat can I do for you?\n");
    }

    /**
     * Displays loading error when tasks cannot be loaded.
     */
    public void showLoadingError() {
        storeOutput("Error: Unable to load tasks from file.");
    }

    /**
     * Displays Ui line.
     */
    public void showLine() {
        storeOutput("__________________________________________________________");
    }

    /**
     * Displays message to acknowledge task added.
     */
    public void showAdded(Task t) {
        storeOutput("Got it. I've added this task:\n\t  " + t);
    }

    /**
     * Displays message including size of list.
     */
    public void showListSize(TaskList tasks) {
        if (tasks.getSize() == 1) {
            storeOutput("Now you have " + tasks.getSize() + " task in the list.");
        } else {
            storeOutput("Now you have " + tasks.getSize() + " tasks in the list.");
        }
    }

    /**
     * Displays all tasks in the list.
     */
    public void showList(TaskList tasks) {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task t = tasks.getTask(i);
            int index = i + 1;
            output += "\n" + index + ". " + t;
        }
        storeOutput(output);
    }

    /**
     * Displays message to acknowledge task marked.
     */
    public void showMarked(Task t) {
        storeOutput("Nice! I've marked this task as done:\n\t  " + t);
    }

    /**
     * Displays message to acknowledge task unmarked.
     */
    public void showUnmarked(Task t) {
        storeOutput("OK, I've marked this task as not done yet:\n\t  " + t);
    }

    /**
     * Displays message to acknowledge task deleted.
     */
    public void showDeleted(Task t) {
        storeOutput("Noted. I've removed this task:\n\t  " + t);
    }

    /**
     * Displays error message if any error occurs.
     */
    public void showError(String errorMessage) {
        storeOutput("\u2639 OOPS!!! " + errorMessage);
    }

    /**
     * Displays exit message.
     */
    public void showExit() {
        storeOutput("Woof!!");
    }
}
