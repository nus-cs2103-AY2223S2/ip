package duke.ui;

import duke.parser.Parser;

/**
 * Ui class to handle interactions with user
 */
public class Ui {

    public Ui() {
    }

    /**
     * Greet user when application is first launched
     * @return String to greet user
     */
    public String greet() {
        return "Hello! I'm Bear Bear!! \nNice to meet you! \nWhat can I do for you?";
    }

    public String sayBye() {
        return "Bye!! Do visit me again! I'll always be free for you :)";
    }

    public String showError(String errorMessage) {
        return errorMessage;
    }

    public String showAddTask(String task, int totalTaskNumber) {
        return "Wow! I've added this task: \n  " + task
                + "\nNow you have " + totalTaskNumber + " tasks in the list.";
    }

    public String showDeleteTask(String task, int totalTaskNumber) {
        return "Okay!! I've removed this task: \n  " + task
                + "\nNow you have " + totalTaskNumber + " tasks in the list.";
    }

    public String showFindResult(Boolean isFound, String result) {
        if (!isFound) {
            return "Oops! :( There are no matching tasks found. Try again?";
        }
        return result;
    }

    public String showMarkTask(String task) {
        return "WOWWWWWW Good Job!!! \n I've marked this task as done: \n  " + task;
    }

    public String showUnmarkTask(String task) {
        return "Ok... I've marked this task as not done yet: \n  " + task;
    }

    public String showList(String list) {
        return list;
    }

    public void showLoadError() {
        System.out.println("Oops! Unable to load file!");
    }


}
