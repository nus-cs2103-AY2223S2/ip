package fideline.user;

/**
 * Handles display messages to the user.
 *
 * @author Fun Leon
 */
public class Ui {

    /**
     * Greets the user at the start of the program.
     */
    public String getHello() {
        String greetingMsg = "hello! I'm fideline,\nwhat do you want today?";
        return greetingMsg;
    }

    /**
     * Bids the user goodbye.
     */
    public String getGoodbyeMsg() {
        String goodbyeMsg = "get out of my sight!";
        return goodbyeMsg;
    }

    /**
     * Tells the user that the list of tasks is currently empty.
     */
    public String getEmptyListMsg() {
        String emptyListMsg = "eh are you stupid?\nyour list is currently empty!";
        return emptyListMsg;
    }

    /**
     * Displays list of tasks to the user.
     *
     * @param list String representation of the list of existing tasks.
     */
    public String getListMsg(String list) {
        assert !list.isEmpty() : "task list cannot be empty here";

        String listMsg = "here! your list:" + list;
        return listMsg;
    }

    /**
     * Informs the user that Fideline was successful in adding the
     * new task as instructed.
     *
     * @param newTask String representation of the task added.
     * @param taskCount The number of existing tasks currently.
     */
    public String getAddTaskMsg(String newTask, int taskCount) {
        assert taskCount >= 1 : "cannot have less than 1 task after adding";
        assert !newTask.isEmpty() : "task string cannot be empty";

        String addTaskMsg = "ok! i've added to your list:\n  "
                + newTask + "\nwow! there "
                + (taskCount == 1 ? "is " : "are ") + taskCount
                + (taskCount == 1 ? " task " : " tasks ")
                + "in the list now! :0";
        return addTaskMsg;
    }

    /**
     * Informs the user that Fideline was successful in marking
     * the specified task.
     *
     * @param task String representation of the marked task.
     */
    public String getMarkMsg(String task) {
        assert !task.isEmpty() : "task string cannot be empty";

        String markMsg = "nice work! i've taken note!:\n  " + task;
        return markMsg;
    }

    /**
     * Informs the user that Fideline was successful in unmarking
     * the specified task.
     *
     * @param task
     */
    public String getUnmarkMsg(String task) {
        assert !task.isEmpty() : "task string cannot be empty";

        String unmarkMsg = "uhh okay... i've unmarked your task:\n  " + task;
        return unmarkMsg;
    }

    /**
     * Informs the user that Fideline was successful in deleting
     * the specified task.
     *
     * @param task
     */
    public String getDeleteMsg(String task, int taskCount) {
        assert taskCount >= 0 : "there cannot be negative tasks after deletion";

        String deleteMsg = "okay i've deleted this task:\n  " + task
                + "\nnow there " + (taskCount == 1 ? "is " : "are ") + taskCount
                + (taskCount == 1 ? " task " : " tasks ") + "in the list now!";
        return deleteMsg;
    }

    /**
     * Informs the user that Fideline was unable to find an existing
     * data file and will make a new one to use instead.
     *
     * @param error String explaining the cause of the issue.
     */
    public void showLoadError(String error) {
        String loadErrorMsg = error + " i'll just start from scratch";
        System.out.println(loadErrorMsg);
    }

    /**
     * Informs the user that Fideline ran into an error.
     *
     * @param error String explaining the issue.
     */
    public String getErrorMsg(String error) {
        assert !error.isEmpty() : "error cannot be empty";

        String errorMsg = "hold up! " + error;
        return errorMsg;
    }

    /**
     * Informs the user that the find command could not find
     * any tasks that contained the given keyword.
     */
    public String getEmptyFindMsg() {
        String emptyFindMsg = "hmm i couldnt find any matching tasks...";
        return emptyFindMsg;
    }

    /**
     * Displays all tasks that were found with the given keyword.
     *
     * @param list Formatted list of all tasks found.
     */
    public String getFindMsg(String list) {
        assert !list.isEmpty() : "find message cannot be empty";

        String findMsg = "here is everything that matched:" + list;
        return findMsg;
    }
}
