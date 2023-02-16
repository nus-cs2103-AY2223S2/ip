package duke;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates the messages seen in the User Interface.
 */
public class Ui {

    /**
     * Creates a new UI object.
     */
    Ui() {}

    /**
     * Prints a formatted message.
     * @param s The string input to be printed.
     * @return The formatted message in string format.
     */
    public String printMessage(String s) {
        return ("\t" + s);
    }

    /**
     * Prints a greeting message to the user.
     * @return the greeting message in String format.
     */
    public String greetingMessage() {
        return printMessage("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    /**
     * Prints a goodbye message to the user.
     * @return The goodbye message in String format.
     */
    public String goodbyeMessage() {
        return printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a formatted message showing that a task has been added to the list.
     * @param t The Task object to be added to the list.
     * @param size The size of the list after adding the new Task.
     * @return The message that tells user that a task has been added.
     */
    public String addedTaskMessage(Task t, int size) {
        String s1 = ("\tYeah boi... You have a new task:");
        String s2 = ("\n\t" + t);
        String s3 = ("\n\tNow you have " + size + " tasks in the list.");
        return s1 + s2 + s3;
    }

    /**
     * Prints a formatted message showing that a task has been deleted from the list.
     * @param t The Task object to be deleted from the list.
     * @param size The size of the list after the task has been deleted.
     * @return the message in string format that shows the deleted task and the number of tasks left.
     */
    public String deletedTaskMessage(Task t, int size) {
        String s1 = ("\tNoted. I've removed this task:");
        String s2 = ("\n\t" + t);
        String s3 = ("\n\tNow you have " + size + " tasks in the list");
        return s1 + s2 + s3;
    }

    /**
     * Prints a formatted message to aid users who are not sure how to use the bot.
     * @return the help message to the user.
     */
    public String helpMessage() {
        String intro = "\tI am here to help you manage your tasks!";
        String body = "\n\tIf you have any events, deadlines or tasks to do, \n\tadd them here!";
        String conclusion = "\n\tTo see the list of commands, type in: cmd";
        return intro + body + conclusion;
    }

    /**
     * Shows a list of commands for the user to use.
     * @return The formatted list of commands
     */
    public String commandsList() {
        String intro = "\tBelow is a list of commands for you to use!";
        String list = "\n\t1. To see your LIST, use command: 'list'";
        String task1 = "\n\n\t2. To add a general TODO task, use command:\n\t'todo <description>'";
        String task2 = "\n\n\t3. To add an EVENT, use command:" +
                "\n\t'event <description> /from <date/time> /to <date/time>'";
        String event = "\n\tNOTE: Your date/time should be in the format: \n\tDD/MM/YYYY HH:MM";
        String task3 = "\n\n\t4. To add a DEADLINE, use command:\n\t'deadline <description> /by <due date>'";
        String deadline = "\n\tNOTE: Your due date should be in the format: \n\tDD/MM/YYYY";
        String delete = "\n\n\t5. To DELETE a task, use command:\n\t'delete <Task number in the list>'";
        String mark = "\n\n\t6. To MARK a task as done, use command:\n\t'mark <Task number in the list>'";
        String unmark = "\n\n\t7. To UNMARK a task as not done, use command:\n\t'unmark <Task number in the list>'";
        String find = "\n\n\t8. To SEARCH for certain tasks in the list, use \n\tcommand:\n\t'find <keyword>'";
        String bye = "\n\n\t9. To CLOSE the application, use command: 'bye'";
        String totalList = intro + list + task1 + task2 + event + task3
                + deadline + delete + mark + unmark + find + bye;
        return totalList;
    }

    /**
     * Prints a message that is displayed if there are any matching tasks to the query.
     * @return the message that is seen before printing found tasks.
     */
    public String findTasksMessage() {
       return ("\tAre you looking for one of these?");
    }

    /**
     * Prints the tasks found using the query function.
     * @param taskList The TaskList object containing the ArrayList of tasks
     * @param taskNumbers The Integer ArrayList containing the task numbers to retrieve from the TaskList.
     * @return the String notation of the tasks in the StringBuilder
     */
    public String printFoundTasks(TaskList taskList, ArrayList<Integer> taskNumbers) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < taskNumbers.size(); j++) {
            int tempTaskNumber = taskNumbers.get(j);
            Task tempTask = taskList.getTask(tempTaskNumber);
            sb.append(String.format("\n\t%d. %s", tempTaskNumber, tempTask));
        }
        return sb.toString();
    }

    /**
     * Prints a message to confirm that the task has been marked as done.
     * @param t The Task object to be marked as done.
     * @return the message in String format that shows the task modified
     */
    public String markTaskAsDoneMessage(Task t) {
        return printMessage("Good job! You have completed this task:\n\t" + t);
    }

    /**
     * Prints a message to confirm that the task has been marked as incomplete.
     * @param t The Task object to be marked as incomplete.
     * @return the message in String format that shows the task modified
     */
    public String markTaskAsIncompleteMessage(Task t) {
        return printMessage("OK, I've marked this task as not done yet:\n\t" + t);
    }

    /**
     * Prints the content of ArrayList of Task objects.
     * @param tasks The ArrayList of Tasks to be printed.
     * @return the String format of the StringBuilder containing the tasks in the list.
     */
    public String printList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int taskNumber = i + 1;
           sb.append(String.format("%d. %s\n", taskNumber, tasks.get(i)));
        }
        return sb.toString();
    }
}