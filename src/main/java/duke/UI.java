package duke;
import duke.task.*;

/**
 * Displays the command line message.
 */
public class UI {

    public static void removeUI(String task) {
        System.out.println("Noted. I've removed this task:\n" + task);
    }

    public static void addUI(String task, int number) {
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + number + " tasks in the list.");
    }

    public static void echoUI(String str){
        System.out.println(str);
    }

    public static void byeUI() {
        System.out.println("Duke.Command.Bye. Hope to see you again soon!");
    }

    public static void taskFoundUI(TaskList taskList) {
        System.out.println("Here are the matching tasks in your list:");
        taskList.listTasks();
    }

    public static void noTaskFoundUI() {
        System.out.println("Sorry, there is no such tasks.");
    }

    public static void listTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list: \n");
        taskList.listTasks();
    }

    public static void markTaskCompleted(TaskList taskList, int taskNumber) {
        System.out.println("Nice! I've marked this task as done:\n" + "[" + taskList.getTaskIcon(taskNumber)
                + "] " + taskList.getTaskContent(taskNumber));
    }

    public static void markTaskUndone(TaskList taskList, int taskNumber) {
        System.out.println("OK, I've marked this task as not done yet:\n" + "[" + taskList.getTaskIcon(taskNumber)
                + "] " + taskList.getTaskContent(taskNumber));
    }

    public static void successfulLoadTask() {
        System.out.println("Successfully retrieved past task list.");
    }

    public static void unsucessfulLoadTask() {
        System.out.println("No past task list found, created new task list.");
    }

    public static void successfulSaveTask() {
        System.out.println("Successfully saved your current tasks.");
    }

    public static void unsuccessulSaveTask(Exception e) {
        System.out.println("Sorry, unable to save your current tasks due to " + e.getMessage());
    }


}
