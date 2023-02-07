package duke;

import duke.tasks.Task;

public class Ui {
    public Ui() {
    }

    /**
     * Returns Duke's default Welcome Message when chatbot is first launched.
     *
     * @return Duke's default Welcome Message
     */
    public String showWelcome() {
        return "Hello! I'm Duke.\nWhat can I do for you?";
    }

    /**
     * Returns Duke's Welcome Message with error message when chatbot is first launched.
     * Method is only called when an Exception is thrown when loading stored tasks from file.
     *
     * @return Duke's Welcome Message with error message
     */
    public String showWelcome(Exception e) {
        return e.toString() +
                "\nCreated new empty File.\n\nHello! I'm Duke.\nWhat can I do for you?";
    }

    /**
     * Returns error message to be displayed on Gui.
     *
     * @return exception's message
     */
    public String showError(Exception e) {
        return e.toString();
    }

    /**
     * Returns message to be displayed on Gui when a Task is added into TaskList.
     *
     * @return confirmation message that Task has been added
     */
    public String showAdded(TaskList l) {
        return "Got it. I've added this task:\n" + l.get(l.size()-1)
                + "\nNow you have " + l.size() + " tasks in the list.";
    }

    /**
     * Returns message to be displayed on Gui when a Task is deleted from TaskList.
     *
     * @return confirmation message that Task has been deleted
     */
    public String showDelete(Task t, TaskList l) {
        return "Noted. I've removed this task:\n" + t +
                "\nNow you have " + l.size() + " tasks in the list.";
    }

    /**
     * Returns message to be displayed on Gui when Duke is terminated.
     *
     * @return goodbye message
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    public String showList(TaskList l) {
        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks in your list:\n");
        for (int i = 0 ; i < l.size() ; i++) {
            s.append(i + 1).append(". ").append(l.get(i)).append("\n");
        }
        return s.toString();
    }

    public String showMatchingList(TaskList l) {
        StringBuilder s = new StringBuilder();
        s.append("Here are the matching tasks in your list:\n");
        for (int i = 0 ; i < l.size() ; i++) {
            s.append(i + 1).append(". ").append(l.get(i)).append("\n");
        }
        return s.toString();
    }

    public String showMark(int mark, Task t) {
        return mark == 1 ?
                "Nice! I've marked this task as done:\n" + t :
                "OK, I've marked this task as not done yet:\n" + t;
    }

}
