import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String reply = "";

        printTextWithLines("Hello! I'm Duke\nWhat can I do for you?");

        while (true) {
            reply = input.nextLine();
            try {
                if (reply.equals("bye")) {
                    printTextWithLines("Bye. Hope to see you again soon!");
                    break;
                } else if (reply.equals("list")) {
                    printTextWithLines(taskList.toString());
                } else if (reply.startsWith("mark")) {
                    Pattern pattern = Pattern.compile("mark (.*)");
                    Matcher matcher = pattern.matcher(reply);
                    if (matcher.find() && matcher.group(1).length() > 0) {
                        int taskNumber = Integer.parseInt(matcher.group(1));
                        taskList.setDone(taskNumber, true);
                        printTextWithLines("Nice! I've marked this task as done:\n  " + taskList.getTask(taskNumber));
                    } else {
                        throw new InvalidCommandException("The task number of a mark command cannot be empty.");
                    }
                } else if (reply.startsWith("unmark")) {
                    Pattern pattern = Pattern.compile("unmark (.*)");
                    Matcher matcher = pattern.matcher(reply);
                    if (matcher.find() && matcher.group(1).length() > 0) {
                        int taskNumber = Integer.parseInt(matcher.group(1));
                        taskList.setDone(taskNumber, true);
                        printTextWithLines("OK, I've marked this task as not done yet:\n  " + taskList.getTask(taskNumber));
                    } else {
                        throw new InvalidCommandException("The task number of an unmark command cannot be empty.");
                    }
                } else if (reply.startsWith("delete")) {
                    Pattern pattern = Pattern.compile("delete (.*)");
                    Matcher matcher = pattern.matcher(reply);
                    if (matcher.find() && matcher.group(1).length() > 0) {
                        int taskNumber = Integer.parseInt(matcher.group(1));
                        String taskDescription = taskList.getTask(taskNumber).toString();
                        taskList.deleteTask(taskNumber);
                        printTextWithLines("Noted. I've removed this task:\n  " + taskDescription + "\n" + taskList.describeLength());
                    } else {
                        throw new InvalidCommandException("The task number to be deleted must be specified, and must be an integer.");
                    }
                } else if (reply.startsWith("todo")) {
                    Pattern pattern = Pattern.compile("todo (.*)");
                    Matcher matcher = pattern.matcher(reply);
                    if (matcher.find() && matcher.group(1).length() > 0) {
                        Task task = new ToDo(matcher.group(1));
                        taskList.addTask(task);
                        printTextWithLines("Got it. I've added this task:\n  " + task + "\n" + taskList.describeLength());
                    } else {
                        throw new InvalidCommandException("The description of a todo cannot be empty.");
                    }
                } else if (reply.startsWith("deadline")) {
                    Pattern pattern = Pattern.compile("deadline (.*) /by (.*)");
                    Matcher matcher = pattern.matcher(reply);
                    if (matcher.find() && matcher.group(1).length() > 0 && matcher.group(2).length() > 0) {
                        Task task = new Deadline(matcher.group(1), matcher.group(2));
                        taskList.addTask(task);
                        printTextWithLines("Got it. I've added this task:\n  " + task + "\n" + taskList.describeLength());
                    } else {
                        throw new InvalidCommandException("The end date of a deadline cannot be empty.");
                    }
                } else if (reply.startsWith("event")) {
                    Pattern pattern = Pattern.compile("event (.*) /from (.*) /to (.*)");
                    Matcher matcher = pattern.matcher(reply);
                    if (matcher.find() && matcher.group(1).length() > 0 && matcher.group(2).length() > 0 && matcher.group(3).length() > 0) {
                        Task task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
                        taskList.addTask(task);
                        printTextWithLines("Got it. I've added this task:\n  " + task + "\n" + taskList.describeLength());
                    } else {
                        throw new InvalidCommandException("An event must have a nonempty from date and a to date.");
                    }
                } else {
                    throw new UnknownCommandException();
                }
            } catch (Exception e) {
                printTextWithLines(e.getMessage());
            }
        }
    }

    static void printTextWithLines(String text) {
        printLineBreak();
        System.out.println(text);
        printLineBreak();
        System.out.println();
    }
    static void printLineBreak() {
        System.out.println("_________________________________________________________________");
    }
}
