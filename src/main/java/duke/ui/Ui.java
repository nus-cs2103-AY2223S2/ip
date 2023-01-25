package duke.ui;

import duke.task.Task;

/**
 * Deals with interactions with the user
 */
public class Ui {
    /**
     * Sends greeting message to user
     */
    public void sendGreetingsMessage() {
        System.out.println("Hello I'm duke.Duke\n" + "What can I do for you?");
    }

    /**
     * Sends goodbye message to user
     */
    public void sendGoodByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Sends task details, formatted to user
     * @param taskNumber
     * @param task
     */
    public void sendTaskDetails(int taskNumber, Task task) {
        System.out.println(taskNumber + ". " + task);
    }
}
