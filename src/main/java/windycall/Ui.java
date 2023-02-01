package windycall;

import java.util.List;

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
    public void displayTasks(List<Task> tasks) {
        if (tasks.size() == 0) {
            Ui.space();
            System.out.println("Seems like there is no task in your list.");
            return;
        }
        Ui.space();
        System.out.println("Here are all of your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Ui.space();
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void displayTasks(List<Task> tasks, String filterWord) {

        int cntValidTask = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).matchFilterWord(filterWord)) {
                cntValidTask++;
            }
        }
        if (cntValidTask == 0) {
            Ui.space();
            System.out.println("Oh! There is no matching task found. Try input another key word");
        } else {
            Ui.space();
            System.out.println("Here are all matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).matchFilterWord(filterWord)) {
                    Ui.space();
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            }
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
