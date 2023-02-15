package duke.ui;

import duke.parser.Parser;

/**
 * Ui class to handle interactions with user
 */
public class Ui {
    private Parser parser;

    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Greet user when application is first launched
     * @return String to greet user
     */
    public String greet() {
        return "Hello! I'm Pingu \nWhat can I do for you?";
    }

    public String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showError(String errorMessage) {
        return errorMessage;
    }

    public String showAddTask(String task, int totalTaskNumber) {
        return "Got it. I've added this task: \n  " + task
                + "\nNow you have " + totalTaskNumber + " tasks in the list.";
    }

    public String showDeleteTask(String task, int totalTaskNumber) {
        return "Noted. I've removed this task: \n  " + task
                + "\nNow you have " + totalTaskNumber + " tasks in the list.";
    }

    public String showFindResult(Boolean isFound, String result) {
        if (!isFound) {
            return "Oops! :( There are no matching tasks found.";
        }
        return result;
    }

    public String showMarkTask(String task) {
        return "Nice! I've marked this task as done: \n  " + task;
    }

    public String showUnmarkTask(String task) {
        return "Ok, I've marked this task as not done yet: \n  " + task;
    }


    public String showList(String list) {
        return list;
    }

    public String generateReply(String input) {
        return this.parser.performCommand(input, this);
    }

}
