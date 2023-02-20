package windycall.ui;

import java.util.List;

import windycall.task.Task;

/**
 * Deals with interactions with the user
 * Makes the background of chatBox more pretty
 */
public class Ui {

    public Ui() {

    }

    public static void space() {
        System.out.print("     ");
    }

    public static void line() {
        space();
        System.out.println("--------------------------------------------------");
    }

    /**
     * Displays all the tasks according to their String representation
     *
     * @param tasks tasks to be displayed
     */
    public String displayTasks(List<Task> tasks) {
        if (tasks.size() == 0) {
            return "Seems like there is no task in your list.\n";
        }
        String returnedMessage = "Here are all of your tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Ui.space();
            returnedMessage += (i + 1) + "." + tasks.get(i) + "\n";
        }
        return returnedMessage;
    }

    public String displayTasks(List<Task> tasks, String filterWord) {
        int cntValidTask = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).matchFilterWord(filterWord)) {
                cntValidTask++;
            }
        }
        if (cntValidTask == 0) {
            return "Oh! There is no matching task found. Try input another key word";
        } else {
            String returnedMessage = "Here are all matching tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).matchFilterWord(filterWord)) {
                    returnedMessage += (i + 1) + "." + tasks.get(i) + "\n";
                }
            }
            return returnedMessage;
        }
    }

    public void greeting() {
        line();
        space();
        System.out.println("Hello! I'm WindyCall");
        space();
        System.out.println("How can I help you?");
        line();
    }

    public void byeWords() {
        line();
        space();
        System.out.println("Bye. Always willing to provide my help for you!!!");
        line();
    }
}
