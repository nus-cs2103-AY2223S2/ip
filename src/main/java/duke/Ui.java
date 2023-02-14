package duke;
import java.util.Scanner;

/**
 * Takes charge of the output and interactions with user
 *
 */
public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }


    String printGreetingMessage() {
        return "Hi, I'm Duke and I am an automated chat bot \n " +
                "What would you like to do?";
    }

    String printTasksMessage() {
        return "Here are all your tasks!";
    }

    /**
     * Standard Duke response when user enters "MARK" command
     * @param markedTask toString() of Task that was completed
     * @return Message congratulating user for completing the task
     */
    String printMarkedTaskMessage(String markedTask) {
        return "Good job on completing this task! \n" + markedTask;
    }

    /**
     * Standard Duke response when user enters "UNMARK" command
     * @param unmarkedTask toString() of Task that was remarked as uncompleted
     * @return Message reminding user to complete the task
     */
    String printUnmarkedTaskMessage(String unmarkedTask) {
        return "Remember to complete this task!! \n" + unmarkedTask;
    }

    /**
     * Standard Duke response when user enters "ADD" command
     * @param addedTask toString() of Task that was added
     * @param taskListSize size of current taskList after adding the task
     * @return Message informing users on the task added and number of tasks in the list
     */
    String printAddedTasks(String addedTask, int taskListSize) {
        return String.format("Got it! I've added this task to the list!"
                + "\n" + "%s" + "\n" + "Now you have %d tasks in the list!"
                + "\n", addedTask, taskListSize);
    }

    /**
     * Standard Duke response when user enters "DELETE" command
     * @param deletedTask toString() of task that was deleted
     * @param taskListSize size of current taskList after deleting task
     * @return Message informing users on the task deleted and number of tasks left in the list
     */
    String printDeletedTasks(String deletedTask, int taskListSize) {
        return String.format("Alright, let me remove this task..."
                + "\n" + "%s" + "\n" + "Now you have %d tasks in the list!"
                + "\n", deletedTask, taskListSize);
    }

    String printFileNotFound() {
        return "File not found :(((";
    }

    /**
     * Standard Duke response when user enters "FIND" command and matching tasks are found
     * @return Message telling users the tasks in list
     */
    String printMatchingTasks() {
        return "Here are the matching tasks in your list: ";
    }

    /**
     * Standard Duke response when user enters "FIND" command and no matching tasks are found
     * @return Message telling users there are no matching tasks
     */
    String printNoMatchingTasks() {
        return "Unfortunately there are no matching tasks :((";
    }

    /**
     * Standard Duke response when user enters "BYE" command
     * @return Goodbye message and exit instructions
     */
    String printExitInstructions() {
        return "Goodbye! Hope to see you again XOXO\n Click on the box on the top left to exit!";
    }

    public String printFailedReschedule() {
        return "Unable to reschedule ToDo as there is no deadline!!";
    }

    public String printSuccessfulReschedule(String changedTask) {
        return String.format("I have successfully changed this task \n %s", changedTask);
    }


    public String printCommandNotDetected() {
        return "Command not detected! Please retry";
    }
}
