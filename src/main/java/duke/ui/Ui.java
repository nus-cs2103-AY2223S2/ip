package duke.ui;

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

    /**
     * Say goodbye to the user before the application closes
     * @return String to say goodbye to user
     */
    public String sayBye() {
        return "Bye!! Do visit me again! I'll always be free for you :)";
    }

    /**
     * Shows error message when exception is being caught or unable to perform command
     * @param errorMessage String to inform user what should be done to correct this error
     * @return String to inform user about the error
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Informs user that the task has been added
     * @param task Task that has been added
     * @param totalTaskNumber Number of task in the task list currently
     * @return String to inform user that task has been added
     */
    public String showAddTask(String task, int totalTaskNumber) {
        return "Wow! I've added this task: \n  " + task
                + "\nNow you have " + totalTaskNumber + " tasks in the list.";
    }

    /**
     * Informs user that the task has been deleted
     * @param task Task that has been deleted
     * @param totalTaskNumber Number of task in the task list currently
     * @return String to inform user that task has been deleted
     */
    public String showDeleteTask(String task, int totalTaskNumber) {
        return "Okay!! I've removed this task: \n  " + task
                + "\nNow you have " + totalTaskNumber + " tasks in the list.";
    }

    /**
     * Inform user about the result of finding the target word in the list of tasks
     * @param isFound True if some matching tasks are found
     * @param result String of matching tasks if isFound is true
     * @return String to inform user the list of matching task found if isFound is true
     *     and inform user that list is not found if isFound is false
     */
    public String showFindResult(Boolean isFound, String result) {
        if (!isFound) {
            return "Oops! :( There are no matching tasks found. Try again?";
        }
        return result;
    }

    /**
     * Inform the user that a task has been marked
     * @param task task that has been marked
     * @return String to inform user that a task has been marked
     */
    public String showMarkTask(String task) {
        return "WOWWWWWW Good Job!!! \n I've marked this task as done: \n  " + task;
    }

    /**
     * Inform the user that a task has been unmarked
     * @param task task that has been unmarked
     * @return String to inform user that a task has been unmarked
     */
    public String showUnmarkTask(String task) {
        return "Ok... I've marked this task as not done yet: \n  " + task;
    }

    /**
     * Inform user about the list of tasks currently present in the task list
     * @param list
     * @return String of current tasks
     */
    public String showList(String list) {
        return list;
    }

    /**
     * Inform user that the file cannot be loaded and task list cannot be obtained from the file
     */
    public void showLoadError() {
        System.out.println("Oops! Unable to load file!");
    }


}
