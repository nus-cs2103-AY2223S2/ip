import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ui {

    protected final String BOT_NAME = "JeoBot";
    protected final String DATE_TO_PRINT = "d MMM yyyy";

    public void showStartingDivider() {
        System.out.println("-----------------------");
    }

    public void showBodyDivider() {
        System.out.println("_________________________________________________________________________________________");
    }

    public void showGreetingMessage() {
        showStartingDivider();
        System.out.println("Greetings from " + BOT_NAME + "!\n" + "How may I help you?");
        showStartingDivider();
    }

    public void showExitMessage() {
        System.out.println("Thank you for using JeoBot. Hope to see you again soon!");
    }

    public void showInvalidCommand() {
        System.out.println("[Error] Sorry, I don't understand what you're saying :(");
    }

    public void showIndexingError() {
        System.out.println("[Error] Task number cannot be negative, zero, or exceed the total number of tasks.");
    }

    public void showDateTimeParsingError() {
        System.out.println("[Error] Unable to parse date-time. " +
                "Please input date in the format: \"yyyy-MM-dd HH:mm\".");
    }

    public void showJeoErrorMessage(String msg) {
        System.out.println(msg);
    }

    public void showLoadingError() {
        System.out.println("Error encountered when loading tasks :(\n");
        System.out.println("Ignore this message if it is your first time using my service :>");
    }

    public void showSavingError() {
        System.out.println("Error encountered when saving tasks :(");
    }

    public void showTaskAdded(Task task, int taskListSize) {
        String statement = "Got it! I've added this task:\n" + task + "\nNow you have ";
        System.out.println(statement + taskListSize + " task(s) in the list.");
    }

    public void showTaskDeleted(Task task, int taskListSize) {
        String statement = "Got it! I've removed this task:\n" +task + "\nNow you have ";
        System.out.println(statement + taskListSize + " task(s) in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Good job! I've marked this task as done:\n" + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("Okay, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Prints out the list of current tasks
     */
    public void showAllTasks(TaskList list) {
        System.out.println("Here are all the tasks in your list:");
        for (int i = 0; i < list.getNumberOfTasks(); i++) {
            StringBuilder sb = new StringBuilder()
                    .append(i+1)
                    .append(".")
                    .append(list.taskList.get(i));
            System.out.println(sb);
        }
    }

    public void showTasksDue(LocalDate byDate, TaskList list) {
        DateTimeFormatter formatterPrint = DateTimeFormatter.ofPattern(DATE_TO_PRINT);
        String formattedDueDate = byDate.format(formatterPrint);
        int j = 1;
        System.out.println("Here are the task(s) due on " + formattedDueDate + " :");
        for (int i = 0; i < list.getNumberOfTasks(); i++) {
            Task currTask = list.taskList.get(i);
            StringBuilder sb = new StringBuilder()
                    .append(j)
                    .append(".");
            if (currTask instanceof Deadline) {
                if (((Deadline) currTask).getDateTimeBy().toLocalDate().compareTo(byDate) == 0) {
                    sb.append(currTask);
                    System.out.println(sb);
                    j++;
                }
            } else if (currTask instanceof Event) {
                if (((Event) currTask).getDateTimeTo().toLocalDate().compareTo(byDate) == 0) {
                    sb.append(currTask);
                    System.out.println(sb);
                    j++;
                }
            }
        }
    }
}
