package duke.ui;

import duke.task.Task;

/**
 * Deals with interactions with the user
 */
public class Ui {
    public void sendGreetingsMessage() {
        System.out.println("Hello I'm duke.Duke\n" + "What can I do for you?");
    }

    public void sendGoodByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void sendTaskDetails(int taskNumber, Task task) {
        System.out.println(taskNumber + ". " + task);
    }
}
